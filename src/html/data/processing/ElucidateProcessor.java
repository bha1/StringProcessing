package html.data.processing;

import java.io.BufferedReader;

public class ElucidateProcessor {

	public void process(String[] args) {
		for (String string : args) {
			ElucidateReader elucidateReader = new ElucidateReader();
			BufferedReader br = elucidateReader.read(string);

			ElucidateWrite elucidateWrite = new ElucidateWrite();
			elucidateWrite.write(string, br);
		}
	}

}
