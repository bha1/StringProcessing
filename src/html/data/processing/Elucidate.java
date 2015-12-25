package html.data.processing;

public class Elucidate {

	public static void main(String[] args) {
		System.out.println("Welcome to the world of Elucidate !!");
		System.out.println("It will help you understand things better.");

		ElucidateProcessor elucidateProcessor = new ElucidateProcessor();
		// String[] arg = { "/D:/workspace/NBE/read.properties" };
		elucidateProcessor.process();
	}

}
