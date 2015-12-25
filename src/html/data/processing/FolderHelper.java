package html.data.processing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FolderHelper {

	public List<File> listFiles() {
		File folder = new File("/D:/workspace/NBE/raw");
		File[] listOfFiles = folder.listFiles();
		List<File> listFile = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				// System.out.println("File " + listOfFiles[i].getName());
				listFile.add(listOfFiles[i]);

			} else if (listOfFiles[i].isDirectory()) {
				// System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		return listFile;
	}

	public static void main(String[] args) {
		FolderHelper fldhpl = new FolderHelper();
		List<File> fl = fldhpl.listFiles();
		System.out.println("completed");
	}

}
