package html.data.processing;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FolderHelper {

	public List<File> listFiles() {
		List<File> listFile = new ArrayList<>();
		try {
			File fldr = new File(FolderHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.getParentFile();
			File folder = new File(fldr, "/raw");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					// System.out.println("File " + listOfFiles[i].getName());
					listFile.add(listOfFiles[i]);

				} else if (listOfFiles[i].isDirectory()) {
					// System.out.println("Directory " +
					// listOfFiles[i].getName());
				}
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// File folder = new File("/D:/workspace/NBE/raw");
		return listFile;
	}

	public void currentDir() {
		try {
			File fldr = new File(FolderHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.getParentFile();
			File fld = new File(fldr, "/raw");
			File[] listOfFiles = fld.listFiles();
			// System.out.println(fldr.getName());
			// System.out.println(fldr.isDirectory());

			List<File> listFile = new ArrayList<>();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					listFile.add(listOfFiles[i]);

				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("Directory " + listOfFiles[i].getName());
				}
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FolderHelper fldhpl = new FolderHelper();
		fldhpl.currentDir();

		List<File> fl = fldhpl.listFiles();
		System.out.println("completed");
	}

}
