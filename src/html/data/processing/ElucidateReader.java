package html.data.processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElucidateReader {

	public BufferedReader read(String string) {
		String pathname = ElucidatePropertyHelper.getProperty("filedepot") + string;
		String pathname1 = ElucidatePropertyHelper.getProperty("filedepot") + "test.html";

		int i = 0;
		Pattern regexp = Pattern.compile("Top 5 Timed Foreground Events");
		Matcher matcher = regexp.matcher("");
		try {
			File file = new File(pathname);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			LineNumberReader lineReader = new LineNumberReader(reader);

			ElucidateWrite.prepare(pathname1);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(pathname1)));

			String line = null;
			while ((line = lineReader.readLine()) != null) {
				matcher.reset(line); // reset the input
				if (matcher.find() || i >= 1) {
					i++;
					bufferedWriter.write(line);
					bufferedWriter.newLine();
					if (i > 10) {
						reader.close();
						bufferedWriter.close();
						break;
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
	}

}
