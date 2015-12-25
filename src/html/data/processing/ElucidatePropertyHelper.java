package html.data.processing;

public class ElucidatePropertyHelper {

	static private ElucidatePropertyHelper _instance = null;
	static public String srcFolder = null;
	static public String destFolder = null;

	protected ElucidatePropertyHelper() {
		srcFolder = "/D:/workspace/NBE/raw";
		destFolder = "/D:/workspace/NBE/processed";
		// "/D:/workspace/NBE/read.properties"
	}

	static public ElucidatePropertyHelper instance() {
		if (_instance == null) {
			_instance = new ElucidatePropertyHelper();
		}
		return _instance;
	}

	// public void createProperty() {
	// Properties prop = new Properties();
	// OutputStream output = null;
	// try {
	//
	// output = new FileOutputStream("elucidate.config.properties");
	//
	// // set the properties
	// prop.setProperty("ishtml", "true");
	// prop.setProperty("isjson", "false");
	//
	// // save properties to project root folder
	// prop.store(output, null);
	//
	// } catch (IOException io) {
	// io.printStackTrace();
	// } finally {
	// if (output != null) {
	// try {
	// output.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }

	// public static String getProperty(String arg) {
	// Properties prop = new Properties();
	// InputStream input = null;
	// String propValue = null;
	// ;
	// try {
	//
	// File base = new File(
	// ElucidatePropertyHelper.class.getProtectionDomain().getCodeSource().getLocation().toURI())
	// .getParentFile();
	// File configFile = new File(base, "read.properties");
	//
	// input = new FileInputStream(configFile);
	// if (input == null) {
	// System.out.println("read.properties File is missing.");
	// return null;
	// }
	//
	// prop.load(input);
	//
	// propValue = prop.getProperty(arg);
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (URISyntaxException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } finally {
	// try {
	// if (input != null) {
	// input.close();
	// return propValue;
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// return null;
	// }

}
