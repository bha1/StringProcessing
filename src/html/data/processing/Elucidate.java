package html.data.processing;

public class Elucidate {

	public static void main(String[] args) {
		System.out.println("Welcome to the world of Elucidate !!");
		System.out.println("It will help you understand things better.");
		System.out.println(
				"**************************************************************************************************");
		System.out.println(" In the directory where you have kept this .jar file.");
		System.out.println(" 1. Create a folder named 'raw'    . Put all the HTML files in this folder.");
		System.out
				.println(" 2. Create a folder named 'processed'   . All the CSV files will be created in this folder.");
		System.out.println(
				"**************************************************************************************************");

		ElucidateProcessor elucidateProcessor = new ElucidateProcessor();
		// String[] arg = { "/D:/workspace/NBE/read.properties" };

		elucidateProcessor.process();
	}

}
