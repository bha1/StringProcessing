package elucidate.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriterProcess {

	PrintWriter writer;

	private static WriterProcess writerProcess = new WriterProcess();

	private WriterProcess() {

	}

	public static WriterProcess getInstance() {
		return writerProcess;

	}

	protected void createWriter(String tableName) {
		// TODO Auto-generated constructor stub
		try {
			FolderHelper folderHelper = FolderHelper.getInstance();
			File fldr = folderHelper.getFolder();
			tableName = tableName.replace("/", "-");
			String destFileName = "/processed/" + tableName.replace(" ", "_") + ".csv";
			File processedFile = new File(fldr, destFileName);
			writer = new PrintWriter(new BufferedWriter(new FileWriter(processedFile, true)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void closeWriter() {
		writer.close();
	}

	protected void writeToFile(String line) {
		writer.println(line);
	}
}
