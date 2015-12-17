package html.data.processing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;

public class ElucidateWrite {

	public static void prepare(String string) {
		BufferedReader reader = null;
		File file = new File(string);
		try {
			if (file.exists()) {
				reader = new BufferedReader(new FileReader(file));
				LineNumberReader lineReader = new LineNumberReader(reader);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
