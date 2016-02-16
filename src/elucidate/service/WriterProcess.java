package elucidate.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriterProcess {

	PrintWriter writer;
	boolean fileNew;

	private void setFileNew(boolean fileNew) {
		this.fileNew = fileNew;
	}

	private boolean getFileNew() {
		return this.fileNew;
	}

	private static WriterProcess writerProcess = new WriterProcess();

	private WriterProcess() {

	}

	public static WriterProcess getInstance() {
		return writerProcess;

	}

	public Hashtable<String, StringBuffer> hashTable = new Hashtable<>();

	public void setHashTable(Hashtable<String, StringBuffer> hashTable) {
		this.hashTable = hashTable;
	}

	protected void createWriter(String tableName) {
		// TODO Auto-generated constructor stub
		try {
			FolderHelper folderHelper = FolderHelper.getInstance();
			File fldr = folderHelper.getFolder();
			tableName = tableName.replace("/", "-");
			String destFileName = "/processed/" + tableName.replace(" ", "_") + ".csv";
			File processedFile = new File(fldr, destFileName);
			setFileNew(processedFile.createNewFile());
			writer = new PrintWriter(new BufferedWriter(new FileWriter(processedFile, true)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void closeWriter() {
		writer.close();
	}

	protected void writeToFile(String line, File file) {
		try {
			file.getName();
			if (fileNew) {
				printFileHeader(line);
				setFileNew(false);
			} else {
				printFileData(line, file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void writeToFile(StringBuffer strBuffer, File file) {
		try {
			// System.out.println(strBuffer);
			file.getName();
			if (fileNew) {
				printFileHeader(strBuffer);
				setFileNew(false);
				printFileData(strBuffer, file);
			} else {
				printFileData(strBuffer, file);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void printFileHeader(String line) {
		String str = line;
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_th = Pattern.compile("<th(.*?)>(.*?)</th>");
		Matcher matcher_th;
		boolean row_written = false;
		String str_tr;
		while (matcher_tr.find()) {
			str_tr = matcher_tr.group();
			matcher_th = pattern_th.matcher(str_tr);
			while (matcher_th.find()) {
				writer.print(unicodeAndQuotesProcessor(matcher_th.group(2)) + ",");
				row_written = true;
			}
		}
		if (row_written) {
			writer.println("File Name");
			row_written = false;
		}

	}

	private void printFileData(String line, File file) {
		String str = line;
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_td = Pattern.compile("<td(.*?)>(.*?)</td>");
		Matcher matcher_td;
		boolean row_written = false;
		String str_tr;
		while (matcher_tr.find()) {
			str_tr = matcher_tr.group();
			matcher_td = pattern_td.matcher(str_tr);
			while (matcher_td.find()) {
				writer.print(unicodeAndQuotesProcessor(matcher_td.group(2)) + ",");
				row_written = true;
			}
			if (row_written) {
				writer.println(file.getName());
				row_written = false;
			}
		}

	}

	private void printFileHeader(StringBuffer line) {
		StringBuffer str = line;
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_th = Pattern.compile("<th(.*?)>(.*?)</th>");
		Matcher matcher_th;
		boolean row_written = false;
		String str_tr;
		while (matcher_tr.find()) {
			str_tr = matcher_tr.group();
			matcher_th = pattern_th.matcher(str_tr);
			while (matcher_th.find()) {
				writer.print(unicodeAndQuotesProcessor(matcher_th.group(2)) + ",");
				row_written = true;
			}
		}
		if (row_written) {
			writer.println("File Name");
			row_written = false;
		}

	}

	private void printFileData(StringBuffer line, File file) {
		StringBuffer str = line;
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_td = Pattern.compile("<td(.*?)>(.*?)</td>");
		Matcher matcher_td;
		boolean row_written = false;
		String str_tr;
		while (matcher_tr.find()) {
			str_tr = matcher_tr.group();
			matcher_td = pattern_td.matcher(str_tr);
			while (matcher_td.find()) {
				writer.print(keyMapper(unicodeAndQuotesProcessor(matcher_td.group(2))) + ",");
				row_written = true;
			}
			if (row_written) {
				writer.println(file.getName());
				row_written = false;
			}
		}

	}

	private String unicodeAndQuotesProcessor(String arg) {
		String product = arg;
		if (arg.equals("&#160;")) {
			arg = "";
		}
		product = ("\"" + arg + "\"");

		return product;
	}

	private StringBuffer keyMapper(String arg) {
		Pattern pat = Pattern.compile("<a.*>(.*?)</a>", Pattern.DOTALL);
		Matcher match = pat.matcher("");
		match.reset(arg);
		if (match.find()) {
			return hashTable.get(unicodeAndQuotesProcessor(match.group(1)));
		}
		return new StringBuffer(arg);
	}
}
