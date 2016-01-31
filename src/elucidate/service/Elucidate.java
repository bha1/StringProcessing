package elucidate.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Elucidate {
	private static Elucidate elucidate = new Elucidate();

	private Elucidate() {
	}

	public static Elucidate getInstance() {
		return elucidate;
	}

	public void processBegin() {

		FolderHelper folderHelper = FolderHelper.getInstance();

		List<File> rawList = new ArrayList<>();
		rawList = folderHelper.listRawFiles();
		ReaderProcess readerProcess = new ReaderProcess();
		readerProcess.process(rawList);

	}
}
