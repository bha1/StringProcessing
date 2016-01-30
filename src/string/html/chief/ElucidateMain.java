package string.html.chief;

import elucidate.service.Elucidate;

public class ElucidateMain {

	public static void main(String[] args) {
		System.out.println("Welcome to AWR elucidate 2.0 !!");
		System.out.println("We will help you understand things better");
		Elucidate elucidate = Elucidate.getInstance();
		elucidate.processBegin();
	}

}
