package html.data.processing;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class ElucidateProcessor {

	public void process() {

		// init properties
		ElucidatePropertyHelper properties = ElucidatePropertyHelper.instance();

		FolderHelper fldrHlpr = new FolderHelper();
		List<File> inputFiles = fldrHlpr.listFiles();

		ElucidateReader elucidateReader = new ElucidateReader();
		for (Iterator iterator = inputFiles.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();

			elucidateReader.readTable(file, "Top 5 Timed Foreground Events");
			//elucidateReader.readTable(file, "Latch Activity");
		}

	}

}
