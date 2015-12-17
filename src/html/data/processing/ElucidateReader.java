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
		String pathname = "/D:/workspace/NBE/" + string;
		String pathname1 = "/D:/workspace/NBE/" + "test.html";

		int i = 0;
		// Pattern regexp = Pattern.compile("^\\\\N\\t(\\d)+\\t(\\w)+");
		Pattern regexp = Pattern.compile("Top 5 Timed Foreground Events");
		Matcher matcher = regexp.matcher("");
		try {
			File file = new File(pathname);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			LineNumberReader lineReader = new LineNumberReader(reader);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(pathname1)));
			String line = null;
			while ((line = lineReader.readLine()) != null) {
				matcher.reset(line); // reset the input
				if (matcher.find() || i >= 1) {
					// "Top 5 Timed Foreground Events"

					i++;
					bufferedWriter.write(line);
					if (i > 10) {
						reader.close();
						bufferedWriter.close();
						break;
					}
					// String msg = "Line " + lineReader.getLineNumber() + " is
					// bad: " + line;
					//
					// throw new IllegalStateException(msg);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {

		}
		// "Top 5 Timed Foreground Events"
		return null;
	}

}
