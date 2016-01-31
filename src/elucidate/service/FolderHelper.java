package elucidate.service;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FolderHelper {

	private static FolderHelper folderHelper = new FolderHelper();

	private FolderHelper() {
	}

	public static FolderHelper getInstance() {
		return folderHelper;
	}

	protected File getFolder() {
		File fldr = null;
		try {
			fldr = new File(FolderHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.getParentFile();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fldr;
	}

	protected List<File> listRawFiles() {
		List<File> listFile = new ArrayList<>();

		File folder = new File(getFolder(), "/raw");
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				listFile.add(files[i]);
			}
		}
		// add throw for no files found
		return listFile;
	}

	protected File configFile() {

		File baseFolder = folderHelper.getFolder();
		String destFileName = "tablelist.txt";
		File configFile = new File(baseFolder, destFileName);
		if (configFile.exists()) {
			return configFile;
		}
		return null;
	}
}
