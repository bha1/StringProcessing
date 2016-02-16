package elucidate.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderProcess {

	public Hashtable<String, StringBuffer> hashTable = new Hashtable<>();

	public void process(List<File> rawList) {
		for (File file : rawList) {
			readSQLTextList(file);
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
						// System.out.println(matcherTableName.find());
						// System.out.println(line);
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
		if (tableNames.contains("Complete List of SQL Text")) {
			tableNames.remove("Complete List of SQL Text");
		}
		return tableNames;
	}

	private void readSQLTextList(File rawFile) {

		Pattern patternTableName = Pattern.compile("(" + "Complete List of SQL Text" + ")(?!</a>)");
		Matcher matcherTableName = patternTableName.matcher("");

		Pattern patternTableEmpty = Pattern.compile("(?i)(no)(.*)?(data)(.*)(exists for this section of the report.)");
		Matcher matcherTableEmpty = patternTableEmpty.matcher("");

		Pattern patternTableStart = Pattern.compile("<table");
		Matcher matcherTableStart = patternTableStart.matcher("");
		Pattern patternTableEnd = Pattern.compile("</table");
		Matcher matcherTableEnd = patternTableEnd.matcher("");
		StringBuffer strBuffer = new StringBuffer("");
		String line = null;
		Boolean tableHeadingFound = false;
		Boolean tableStart = false;
		Boolean tableEnd = false;
		Boolean tableIsEmpty = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(rawFile));
			LineNumberReader lineNumberReader = new LineNumberReader(reader);
			while ((line = lineNumberReader.readLine()) != null && tableEnd != true && tableIsEmpty != true) {
				matcherTableName.reset(line);
				if (matcherTableName.find() == true) {
					tableHeadingFound = true;
					strBuffer.append(line);
					while ((line = lineNumberReader.readLine()) != null && tableEnd != true) {
						matcherTableEmpty.reset(line);
						if (matcherTableEmpty.find() && tableStart != true) {
							tableIsEmpty = true;
							break;
						}
						matcherTableStart.reset(line);
						if (matcherTableStart.find()) {
							tableStart = true;
							strBuffer.append(line);
							while ((line = lineNumberReader.readLine()) != null) {
								matcherTableEnd.reset(line);
								if (matcherTableEnd.find()) {
									tableEnd = true;
									strBuffer.append(line);
									break;
								}
								strBuffer.append(line);
							}
						}
					}
				}
			}
			extractHashMap(strBuffer);
			WriterProcess writerProcess = WriterProcess.getInstance();
			writerProcess.setHashTable(hashTable);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void extractHashMap(StringBuffer line) {
		hashTable.clear();
		StringBuffer str = line;
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_td_key = Pattern.compile("<td(.*?)><a.*?/a>(.*?)</td>");
		Matcher matcher_td_key;
		Pattern pattern_td = Pattern.compile("<td(.*?)>(.*?)</td>");
		Matcher matcher_td;
		boolean keyWritten = false;
		boolean valueWritten = false;
		String str_tr;
		String key;
		StringBuffer value = new StringBuffer("");
		while (matcher_tr.find()) {
			keyWritten = false;
			valueWritten = false;
			key = null;

			str_tr = matcher_tr.group();
			matcher_td = pattern_td.matcher(str_tr);
			while (matcher_td.find()) {
				if (keyWritten == false && valueWritten == false) {
					matcher_td_key = pattern_td_key.matcher(str_tr);
					matcher_td_key.find();
					key = unicodeAndQuotesProcessor(matcher_td_key.group(2));

					keyWritten = true;
				} else if (keyWritten == true && valueWritten == false) {
					value = new StringBuffer(unicodeAndQuotesProcessor(matcher_td.group(2)));
					valueWritten = true;
				}
				if (keyWritten == true && valueWritten == true) {
					hashTable.put(key, value);
				}
			}
		}
		// System.out.println(hashTable);
	}

	private String unicodeAndQuotesProcessor(String arg) {
		String product = arg;
		if (arg.equals("&#160;")) {
			arg = "";
		}
		product = ("\"" + arg + "\"");

		return product;
	}

	public static void main(String[] args) {
		StringBuffer str = new StringBuffer("<a class=awr\" href=\"#a54b1ppyyhv9u\">a54b1ppyyhv9u</a>");
		// System.out.println(str);
		String tableName = new String("SQL ordered by Gets");
		Pattern pat = Pattern.compile("<a.*>(.*?)</a>", Pattern.DOTALL);
		Matcher match = pat.matcher("");

		match.reset(str);
		while (match.find()) {
			System.out.println(match.group(1));
			System.out.println("***********************************************************");
		}

	}
}
