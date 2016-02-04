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
			StringBuffer strBuffer = new StringBuffer("");
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
				strBuffer.setLength(0);
				patternTableName = Pattern.compile("(" + tableName + ")(?!</a>)");
				matcherTableName = patternTableName.matcher("");
				lineNumberReader = new LineNumberReader(new BufferedReader(new FileReader(rawFile)));
				while ((line = lineNumberReader.readLine()) != null && tableEnd != true && tableIsEmpty != true) {
					matcherTableName.reset(line);
					if (matcherTableName.find() == true) {
						System.out.println(matcherTableName.find());
						System.out.println(line);
						tableHeadingFound = true;
						strBuffer.append(line);
						// WriterProcess writerProcess =
						// WriterProcess.getInstance();
						// writerProcess.createWriter(tableName);
						while ((line = lineNumberReader.readLine()) != null && tableEnd != true) {
							matcherTableEmpty.reset(line);
							if (matcherTableEmpty.find() && tableStart != true) {
								tableIsEmpty = true;
								// writerProcess.closeWriter();
								break;
							}
							matcherTableStart.reset(line);
							if (matcherTableStart.find()) {
								tableStart = true;
								startTable = lineNumberReader.getLineNumber();
								WriterProcess writerProcess = WriterProcess.getInstance();
								writerProcess.createWriter(tableName);
								// writerProcess.writeToFile(line, rawFile);
								strBuffer.append(line);
								while ((line = lineNumberReader.readLine()) != null) {
									matcherTableEnd.reset(line);
									if (matcherTableEnd.find()) {
										tableEnd = true;
										endTable = lineNumberReader.getLineNumber();
										strBuffer.append(line);
										writerProcess.writeToFile(strBuffer, rawFile);
										writerProcess.closeWriter();
										break;
									}
									// writerProcess.writeToFile(line, rawFile);
									strBuffer.append(line);
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
		FolderHelper folderHelper = FolderHelper.getInstance();
		List<String> tableNames = new ArrayList<>();
		File config = null;
		Pattern emptyLine = Pattern.compile("[A-Za-z]");
		Matcher matcherLine = emptyLine.matcher("");
		if ((config = folderHelper.configFile()) == null) {

			tableNames.add("Background Wait Events");
			tableNames.add("Top 5 Timed Foreground Events");
			tableNames.add("Top Foreground Wait Class");
			tableNames.add("Segments by Buffer Busy Waits");
			tableNames.add("Segments by Row Lock Waits");
			tableNames.add("Segments by ITL Waits");
			tableNames.add("Segments by Physical Reads");
			tableNames.add("Segments by UnOptimized Reads");
			tableNames.add("Segments by Logical Reads");
			tableNames.add("SQL ordered by Elapsed Time");
			tableNames.add("SQL ordered by CPU Time");
			tableNames.add("SQL ordered by User I/O Wait Time");
			tableNames.add("SQL ordered by Gets");
			tableNames.add("SQL ordered by Reads");
			tableNames.add("SQL ordered by Physical Reads (UnOptimized)");
			tableNames.add("SQL ordered by Executions");
			tableNames.add("SQL ordered by Parse Calls");
			tableNames.add("SQL ordered by Sharable Memory");
			tableNames.add("SQL ordered by Version Count");
			tableNames.add("SQL ordered by Cluster Wait Time");
			tableNames.add("Complete List of SQL Text");

		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(config));
				LineNumberReader lineNumberReader = new LineNumberReader(reader);
				String line = null;
				while ((line = lineNumberReader.readLine()) != null) {
					matcherLine.reset(line);
					if (matcherLine.find()) {
						tableNames.add(line);

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
		return tableNames;
	}

	public static void main(String[] args) {
		StringBuffer str = new StringBuffer(
				"<table border=\"1\"><tr><th class=\"awrbg\">Service Name</th><th class=\"awrbg\">User I/O Total Wts</th><th class=\"awrbg\">User I/O Wt Time</th><th class=\"awrbg\">Concurcy Total Wts</th><th class=\"awrbg\">Concurcy Wt Time</th><th class=\"awrbg\">Admin Total Wts</th><th class=\"awrbg\">Admin Wt Time</th><th class=\"awrbg\">Network Total Wts</th><th class=\"awrbg\">Network Wt Time</th></tr>\n<tr><td class='awrc'>flxracdb</td>\n<td align=\"right\" class='awrc'>11945865</td><td align=\"right\" class='awrc'>82234</td><td align=\"right\" class='awrc'>1208992</td><td align=\"right\" class='awrc'>922</td><td align=\"right\" class='awrc'>0</td><td align=\"right\" class='awrc'>0</td><td align=\"right\" class='awrc'>5327091</td><td align=\"right\" class='awrc'>15</td></tr>\n<tr><td class='awrnc'>SYS$USERS</td>\n<td align=\"right\" class='awrnc'>1296889</td><td align=\"right\" class='awrnc'>4569</td><td align=\"right\" class='awrnc'>4995</td><td align=\"right\" class='awrnc'>4</td><td align=\"right\" class='awrnc'>0</td><td align=\"right\" class='awrnc'>0</td><td align=\"right\" class='awrnc'>13000</td><td align=\"right\" class='awrnc'>57</td></tr>\n<tr><td class='awrc'>SYS$BACKGROUND</td>\n<td align=\"right\" class='awrc'>50234</td><td align=\"right\" class='awrc'>242</td><td align=\"right\" class='awrc'>682696</td><td align=\"right\" class='awrc'>691</td><td align=\"right\" class='awrc'>0</td><td align=\"right\" class='awrc'>0</td><td align=\"right\" class='awrc'>0</td><td align=\"right\" class='awrc'>0</td></tr>\n</table>");
		// System.out.println(str);
		String tableName = new String("SQL ordered by Gets");
		Pattern pat = Pattern.compile("<tr(.*?)/tr>", Pattern.DOTALL);
		Matcher match = pat.matcher("");
		match.reset(str);
		while (match.find()) {
			System.out.println(match.group(1));
			System.out.println("***********************************************************");
		}

	}
}
