package some.thing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFriend {

	public void printPatternHeader(String string, File file, File processedFile) {
		String str = string;
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_th = Pattern.compile("<th(.*?)>(.*?)</th>");
		Matcher matcher_th;
		boolean row_written = false;
		String str_tr;
		while (matcher_tr.find()) {
			// System.out.println("start index" + matcher_tr.start());
			// System.out.println("end index" + matcher_tr.end());
			// System.out.println(matcher_tr.group());
			str_tr = matcher_tr.group();
			matcher_th = pattern_th.matcher(str_tr);
			while (matcher_th.find()) {
				// System.out.println("***********************************");
				// System.out.println("start index" + matcher_th.start());
				// System.out.println("end index" + matcher_th.end());
				// System.out.print(unicodeAndQuotesProcessor(matcher_th.group(2))
				// + ",");
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(processedFile, true)))) {
					out.print(unicodeAndQuotesProcessor(matcher_th.group(2)) + ",");
					row_written = true;
				} catch (IOException e) {
					// exception handling left as an exercise for the reader
				}
			}
			// System.out.println("***********************************");
		}
		if (row_written) {
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(processedFile, true)))) {
				out.println("File Name");
				row_written = false;
			} catch (IOException e) {
				// exception handling left as an exercise for the reader
			}
		}

	}

	public void printPatternData(String string, File file, File processedFile) {
		String str = string;
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_td = Pattern.compile("<td(.*?)>(.*?)</td>");
		Matcher matcher_td;
		boolean row_written = false;
		String str_tr;
		while (matcher_tr.find()) {
			// System.out.println("start index" + matcher_tr.start());
			// System.out.println("end index" + matcher_tr.end());
			// System.out.println(matcher_tr.group());
			str_tr = matcher_tr.group();
			matcher_td = pattern_td.matcher(str_tr);
			while (matcher_td.find()) {
				// System.out.println("***********************************");
				// System.out.println("start index" + matcher_th.start());
				// System.out.println("end index" + matcher_th.end());
				// System.out.print(unicodeAndQuotesProcessor(matcher_td.group(2))
				// + ",");
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(processedFile, true)))) {
					out.print(unicodeAndQuotesProcessor(matcher_td.group(2)) + ",");
					row_written = true;
				} catch (IOException e) {
					// exception handling left as an exercise for the reader
				}
			}
			if (row_written) {
				try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(processedFile, true)))) {
					out.println(file.getName());
					row_written = false;
				} catch (IOException e) {
					// exception handling left as an exercise for the reader
				}
			}
		}

	}

	private String unicodeAndQuotesProcessor(String arg) {
		String product = arg;
		if (arg.equals("&#160;")) {
			arg = "";
		}
		product = ("\"" + arg + "\"");

		return product;
	}

	public static void main(String[] args) {
		String str = new String(
				"<table border='1'><tr><th class='awrbg'>Event</th><th class='awrbg'>Waits</th><th class='awrbg'>Time(s)</th><th class='awrbg'>Avg wait (ms)</th><th class='awrbg'>% DB time</th><th class='awrbg'>Wait Class</th></tr>");
		Pattern pattern_tr = Pattern.compile("<tr(.*?)>(.*?)</tr>");
		Matcher matcher_tr = pattern_tr.matcher(str);
		Pattern pattern_th = Pattern.compile("<th(.*?)>(.*?)</th>");
		Matcher matcher_th;
		String str_tr;
		while (matcher_tr.find()) {
			// System.out.println("start index" + matcher_tr.start());
			// System.out.println("end index" + matcher_tr.end());
			System.out.println(matcher_tr.group());
			str_tr = matcher_tr.group();
			matcher_th = pattern_th.matcher(str_tr);
			while (matcher_th.find()) {
				System.out.println("***********************************");
				// System.out.println("start index" + matcher_th.start());
				// System.out.println("end index" + matcher_th.end());
				System.out.println(matcher_th.group(2));
			}
		}

	}

}

// <tr><td class='awrc'>DB CPU</td><td align="right" class='awrc'>&#160;</td><td
// align="right" class='awrc'>2,092</td><td align="right"
// class='awrc'>&#160;</td><td align="right" class='awrc'>51.70</td><td
// class='awrc'>&#160;</td></tr>
// <tr><td class='awrnc'>db file sequential read</td><td align="right"
// class='awrnc'>103,945</td><td align="right" class='awrnc'>201</td><td
// align="right" class='awrnc'>2</td><td align="right"
// class='awrnc'>4.98</td><td class='awrnc'>User I/O</td></tr>
// <tr><td class='awrc'>db file scattered read</td><td align="right"
// class='awrc'>31,502</td><td align="right" class='awrc'>37</td><td
// align="right" class='awrc'>1</td><td align="right" class='awrc'>0.91</td><td
// class='awrc'>User I/O</td></tr>
// <tr><td class='awrnc'>library cache pin</td><td align="right"
// class='awrnc'>14,027</td><td align="right" class='awrnc'>23</td><td
// align="right" class='awrnc'>2</td><td align="right"
// class='awrnc'>0.58</td><td class='awrnc'>Concurrency</td></tr>
// <tr><td class='awrc'>read by other session</td><td align="right"
// class='awrc'>24,543</td><td align="right" class='awrc'>23</td><td
// align="right" class='awrc'>1</td><td align="right" class='awrc'>0.56</td><td
// class='awrc'>User I/O</td></tr>
// </table>");