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

		// ElucidateReader elucidateReader[] = new
		// ElucidateReader[tableNames.size()];
		// for (Iterator iterator = tableNames.iterator(); iterator.hasNext();)
		// {
		// tableName = (String) iterator.next();
		// for (int i = 0; i < tableNames.size(); i++)
		// elucidateReader[i] = new ElucidateReader(inputFiles, tableName);
		// }
		//
		// for (int i = 0; i < elucidateReader.length; i++)
		// try {
		// elucidateReader[i].join();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } finally {
		// System.out.println("Time Required : " + (System.currentTimeMillis() -
		// tim) + " miliseconds.");
		// }

		for (Iterator iterator = inputFiles.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();

			// elucidateReader.readTable(file, "Background Wait Events");
			ElucidateReader.readTable(file, "Top 5 Timed Foreground Events");
			ElucidateReader.readTable(file, "Top Foreground Wait Class");
			ElucidateReader.readTable(file, "Segments by Buffer Busy Waits");
			ElucidateReader.readTable(file, "Segments by Row Lock Waits");
			ElucidateReader.readTable(file, "Segments by ITL Waits");
			ElucidateReader.readTable(file, "Segments by Physical Reads");
			ElucidateReader.readTable(file, "Segments by UnOptimized Reads");
			ElucidateReader.readTable(file, "Segments by Logical Reads");
			ElucidateReader.readTable(file, "SQL ordered by Elapsed Time");
			ElucidateReader.readTable(file, "SQL ordered by CPU Time");
			ElucidateReader.readTable(file, "SQL ordered by User I/O Wait Time");
			ElucidateReader.readTable(file, "SQL ordered by Gets");
			ElucidateReader.readTable(file, "SQL ordered by Reads");
			ElucidateReader.readTable(file, "SQL ordered by Physical Reads (UnOptimized)");
			ElucidateReader.readTable(file, "SQL ordered by Executions");
			ElucidateReader.readTable(file, "SQL ordered by Parse Calls");
			ElucidateReader.readTable(file, "SQL ordered by Sharable Memory");
			ElucidateReader.readTable(file, "SQL ordered by Version Count");
			ElucidateReader.readTable(file, "SQL ordered by Cluster Wait Time");
			ElucidateReader.readTable(file, "Complete List of SQL Text");
		}
		System.out.println("Time Required : " + (System.currentTimeMillis() - tim) + " miliseconds.");

	}

}
