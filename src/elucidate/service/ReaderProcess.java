package elucidate.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderProcess {

	public void process(List<File> rawList) {
		for (File file : rawList) {
			readTable(file);
		}
	}

	private void readTable(File rawFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(rawFile));
			LineNumberReader lineNumberReader = new LineNumberReader(reader);
			String line = null;
			List<String> tableNames = new ArrayList<>();
			tableNames = fetchTableNames();
			Pattern patternTableName;
			Matcher matcherTableName;
			Pattern patternTableEmpty = Pattern
					.compile("(?i)(no)(.*)?(data)(.*)(exists for this section of the report.)");
			Matcher matcherTableEmpty = patternTableEmpty.matcher("");

			Pattern patternTableStart = Pattern.compile("<table");
			Matcher matcherTableStart = patternTableStart.matcher("");
			Pattern patternTableEnd = Pattern.compile("</table");
			Matcher matcherTableEnd = patternTableEnd.matcher("");

			int startTable = 0, endTable = 0;
			boolean tableHeadingFound = false, tableStart = false, tableEnd = false, tableIsEmpty = false;
			for (String tableName : tableNames) {
				tableHeadingFound = false;
				tableStart = false;
				tableEnd = false;
				tableIsEmpty = false;
				startTable = 0;
				endTable = 0;
				patternTableName = Pattern.compile("(" + tableName + ")(?!</a>)");
				matcherTableName = patternTableName.matcher("");
				lineNumberReader = new LineNumberReader(new BufferedReader(new FileReader(rawFile)));
				while ((line = lineNumberReader.readLine()) != null && tableEnd != true && tableIsEmpty != true) {
					matcherTableName.reset(line);
					if (matcherTableName.find()) {
						tableHeadingFound = true;

						WriterProcess writerProcess = WriterProcess.getInstance();
						writerProcess.createWriter(tableName);
						while ((line = lineNumberReader.readLine()) != null && tableEnd != true) {
							matcherTableEmpty.reset(line);
							if (matcherTableEmpty.find() && tableStart != true) {
								tableIsEmpty = true;
								writerProcess.closeWriter();
								break;
							}
							matcherTableStart.reset(line);
							if (matcherTableStart.find()) {
								tableStart = true;
								startTable = lineNumberReader.getLineNumber();
								writerProcess.writeToFile(line, rawFile);
								while ((line = lineNumberReader.readLine()) != null) {
									matcherTableEnd.reset(line);
									if (matcherTableEnd.find()) {
										tableEnd = true;
										endTable = lineNumberReader.getLineNumber();
										writerProcess.writeToFile(line, rawFile);
										writerProcess.closeWriter();
										break;
									}
									writerProcess.writeToFile(line, rawFile);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> fetchTableNames() {
		List<String> tableNames = new ArrayList<>();
		// tableNames.add("Background Wait Events");
		// tableNames.add("Top 5 Timed Foreground Events");
		// tableNames.add("Top Foreground Wait Class");
		// tableNames.add("Segments by Buffer Busy Waits");
		// tableNames.add("Segments by Row Lock Waits");
		tableNames.add("Segments by ITL Waits");
		// tableNames.add("Segments by Physical Reads");
		// tableNames.add("Segments by UnOptimized Reads");
		// tableNames.add("Segments by Logical Reads");

		return tableNames;
	}
}
