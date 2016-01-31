package html.data.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import some.thing.RegexFriend;

public class ElucidateReader extends Thread {
	List<File> readFiles;
	String tableName;

	ElucidateReader(List<File> inputFiles, String tableName) {
		this.readFiles = inputFiles;
		this.tableName = tableName;
		this.start();
	}

	public void run() {
		// for (Iterator iterator = readFiles.iterator(); iterator.hasNext();) {
		// File file = (File) iterator.next();
		// readTable(file, tableName);
		// }
		readTableList(this.readFiles, this.tableName);
	}

	private void readTableList(List<File> list, String name) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			readTable(file, name);
		}
	}

	public static void readTable(File file, String tableName) {
		// System.out.println(file.getName());
		Pattern regexTableName = Pattern.compile("(" + tableName + ")(?!</a>)");
		Matcher matcherTableName = regexTableName.matcher("");
		Pattern regexTableEmpty = Pattern.compile("(?i)(no)(.*)?(data)(.*)(exists)");
		Matcher matcherTableEmpty = regexTableEmpty.matcher("");
		Pattern regexp_table_start = Pattern.compile("<table");
		Matcher matcher_table_start = regexp_table_start.matcher("");
		Pattern regexp_table_end = Pattern.compile("</table");
		Matcher matcher_table_end = regexp_table_end.matcher("");
		boolean table_found = false;
		boolean table_start = false;
		boolean table_end = false;
		int startTable = 0, endTable = 0;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			LineNumberReader lineReader = new LineNumberReader(reader);

			String line = null;
			while ((line = lineReader.readLine()) != null) {
				matcherTableName.reset(line);
				if (matcherTableName.find()) {
					// System.out.println("found table");
					table_found = true;
					while ((line = lineReader.readLine()) != null) {
						matcherTableEmpty.reset(line);
						if(matcherTableEmpty.find()){
							break;
						}
						matcher_table_start.reset(line);
						if (matcher_table_start.find()) {
							// System.out.println("table start" +
							// lineReader.getLineNumber());
							startTable = lineReader.getLineNumber();
							table_start = true;
							while ((line = lineReader.readLine()) != null) {
								matcher_table_end.reset(line);
								if (matcher_table_end.find()) {
									// System.out.println("table end" +
									// lineReader.getLineNumber());
									endTable = lineReader.getLineNumber();
									table_end = true;
									break;
								}
							}
							if (table_end) {
								break;
							}
						}
						if (table_end) {
							break;
						}
					}
				}
			}
			if (table_found && table_start && table_end) {
				readRow(file, tableName, startTable, endTable);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public static void readRow(File file, String tableName, int startTable, int endTable) {
		int lineNumber = startTable;
		boolean bool;
		BufferedReader reader;
		RegexFriend regfrnd = new RegexFriend();
		try {
			File fldr = new File(ElucidateReader.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.getParentFile();
			tableName = tableName.replace("/", "-");
			String destFileName = "/processed/" + tableName.replace(" ", "_") + ".csv";
			File processedFile = new File(fldr, destFileName);

			reader = new BufferedReader(new FileReader(file));
			LineNumberReader lineReader = new LineNumberReader(reader);
			String line = null;
			while ((line = lineReader.readLine()) != null) {
				if (lineReader.getLineNumber() >= startTable && lineReader.getLineNumber() <= endTable) {
					if (!processedFile.exists()) {
						bool = processedFile.createNewFile();
						regfrnd.printPatternHeader(line, file, processedFile);
					} else {

						regfrnd.printPatternData(line, file, processedFile);
					}
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public BufferedReader read(String string) {
	// ElucidatePropertyHelper properties = ElucidatePropertyHelper.instance();
	// String pathname = properties.srcFolder + string;
	// String pathname1 = properties.destFolder + "test.html";
	//
	// int i = 0;
	// Pattern regexp = Pattern.compile("Top 5 Timed Foreground Events");
	// Matcher matcher = regexp.matcher("");
	// Pattern regexp_table = Pattern.compile("<table");
	// Matcher matcher_table = regexp_table.matcher("");
	// try {
	// File file = new File(pathname);
	// BufferedReader reader = new BufferedReader(new FileReader(file));
	// LineNumberReader lineReader = new LineNumberReader(reader);
	//
	// ElucidateWrite.prepare(pathname1);
	// BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new
	// File(pathname1)));
	//
	// String line = null;
	// while ((line = lineReader.readLine()) != null) {
	// matcher.reset(line); // reset the input
	// if (matcher.find() || i >= 1) {
	// i++;
	// bufferedWriter.write(line);
	// bufferedWriter.newLine();
	// while ((line = lineReader.readLine()) != null) {
	// matcher_table.reset(line);
	// if (matcher_table.find()) {
	// System.out.println(lineReader.getLineNumber());
	// }
	// }
	// break;
	//
	// // if (i > 10) {
	// // reader.close();
	// // bufferedWriter.close();
	// // break;
	// // }
	// }
	// }
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// } finally {
	//
	// }
	// return null;
	// }

}
