/**
 * License: GPLv3
 * Date: 2014-04-29 Asia/Tokyo
 * GitHub: https://github.com/reinforchu/MD5reverse
 * Twitter: https://twitter.com/reinforchu
 */
package tv.reinforce.md5reverse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Expose plaintext from MD5 hash
 * @author reinforchu
 * @version 1.0.0
 * @see reverse-hash-lookup.online-domain-tools.com there're many database amount.
 */
public class Reverse {
	private static Document doc = null;
	private static String hash = "";

	/**
	 * Scraping from md5.gromweb.com
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Expose plaintext from MD5 hash.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(hash.isEmpty()) {
			System.out.print("Hash> ");
			try {
				hash = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			doc = Jsoup.connect("http://md5.gromweb.com/?md5=" + hash).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String plainText = doc.select("#form_string").attr("value"); // Get plaintext
		System.out.println("Text> " + plainText); // Empty data is returned if don't exist in the database.
	}

}