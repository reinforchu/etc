/**
 * License: GPLv3
 * Date: 2014-05-02 Asia/Tokyo
 * GitHub: https://github.com/reinforchu/nyumenn
 * Web: http://reinforce.tv/
 */
package tv.reinforce.nyumenn;

import java.io.File;

/**
 * Nyumenn so cute!
 * @author reinforchu
 * @version 1.0.0.0
 */
public class Nyumenn {
	private static final String NyumennFile = "Nyumenn.xml";

	/**
	 * main method
	 * @param args Not in use
	 */
	public static void main(String[] args) {
		File configFilePath = new File(new File(System.getProperty("java.class.path")).getParent() + File.separator + NyumennFile);
		if (!configFilePath.exists() && !configFilePath.isFile() && !configFilePath.canRead()) {
			System.out.println("Nyumenn missing...");
		} else {
			ConfigLoader config = new ConfigLoader(NyumennFile);
			Menn nyumenn = new Menn();
			nyumenn.oAuthInit(config.getConsumerKey(), config.getConsumerSecret(), config.getAccessToken(), config.getAccessTokenSecret());
			nyumenn.soCute();
		}
	}
}