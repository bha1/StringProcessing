package html.data.processing;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ElucidateProcessor {

	public void process() {

		// init properties
		ElucidatePropertyHelper properties = ElucidatePropertyHelper.instance();

		FolderHelper fldrHlpr = new FolderHelper();
		List<File> inputFiles = fldrHlpr.listFiles();
		long tim;
		tim = System.currentTimeMillis();
		List<String> tableNames = new ArrayList<>();
		tableNames.add("Background Wait Events");
		tableNames.add("Top 5 Timed Foreground Events");
		tableNames.add("Top Foreground Wait Class");
		tableNames.add("Segments by Buffer Busy Waits");
		tableNames.add("Segments by Row Lock Waits");
		tableNames.add("Segments by ITL Waits");
		tableNames.add("Segments by Physical Reads");
		tableNames.add("Segments by UnOptimized Reads");
		tableNames.add("Segments by Logical Reads");
		String tableName;
		ElucidateReader elucidateReader[] = new ElucidateReader[tableNames.size()];
		for (Iterator iterator = tableNames.iterator(); iterator.hasNext();) {
			tableName = (String) iterator.next();
			for (int i = 0; i < tableNames.size(); i++)
				elucidateReader[i] = new ElucidateReader(inputFiles, tableName);
		}

		for (int i = 0; i < elucidateReader.length; i++)
			try {
				elucidateReader[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				System.out.println("Time Required : " + (System.currentTimeMillis() - tim) + " miliseconds.");
			}
		// for (Iterator iterator = inputFiles.iterator(); iterator.hasNext();)
		// {
		// File file = (File) iterator.next();
		//
		// // elucidateReader.readTable(file, "Background Wait Events");
		// elucidateReader.readTable(file, "Top 5 Timed Foreground Events");
		// elucidateReader.readTable(file, "Top Foreground Wait Class");
		// elucidateReader.readTable(file, "Segments by Buffer Busy Waits");
		// elucidateReader.readTable(file, "Segments by Row Lock Waits");
		// elucidateReader.readTable(file, "Segments by ITL Waits");
		// elucidateReader.readTable(file, "Segments by Physical Reads");
		// elucidateReader.readTable(file, "Segments by UnOptimized Reads");
		// elucidateReader.readTable(file, "Segments by Logical Reads");
		//
		// }

	}

}
