package string.html.chief;

import elucidate.service.Elucidate;

public class ElucidateMain {

	public static void main(String[] args) {
		System.out.println("Welcome to AWR elucidate 2.0 !!");
		System.out.println("We will help you understand things better");
		System.out.println(
				"**************************************************************************************************");
		System.out.println(" In the directory where you have kept this .jar file.");
		System.out.println(" 1. Create a folder named 'raw'    . Put all the HTML files in this folder.");
		System.out
				.println(" 2. Create a folder named 'processed'   . All the CSV files will be created in this folder.");
		System.out.println(" 3. optional create file 'tablelist.txt' in the same folder as the JAR,");
		System.out.println("add the list of tables in the text file to customize the file processor.");
		System.out.println("One table name in one row of the txt file");
		System.out.println(
				"**************************************************************************************************");
		long tim;
		tim = System.currentTimeMillis();
		Elucidate elucidate = Elucidate.getInstance();
		elucidate.processBegin();
		System.out.println("Time Required : " + (System.currentTimeMillis() - tim) + " miliseconds.");
		// tablelist.txt
	}

}
