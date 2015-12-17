package html.data.processing;

public class Elucidate {

	public static void main(String[] args) {
		System.out.println("Welcome to the world of Elucidate !!");
		System.out.println("It will help you understand things better.");

		ElucidateProcessor elucidateProcessor = new ElucidateProcessor();
		String[] arg = { "awrrpt_1_134567_134568.html", "awrrpt_1_134568_134569.html" };
		elucidateProcessor.process(arg);
	}

}
