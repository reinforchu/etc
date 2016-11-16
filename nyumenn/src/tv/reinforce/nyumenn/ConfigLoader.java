package tv.reinforce.nyumenn;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

/**
 * Configuration file loader
 * @author reinforchu
 */
public class ConfigLoader {
	private static Document xml = null;
	private static String ConsumerKey = "";
	private static String ConsumerSecret = "";
	private static String AccessToken = "";
	private static String AccessTokenSecret = "";

	/**
	 * Constructor
	 * Load private keys form Nyumenn.xml
	 */
	public ConfigLoader(String configFileName) {
		loadXML(new File(System.getProperty("java.class.path")).getParent() + File.separator + configFileName);
		ConsumerKey = getValue("/keys/consumer/key");
		ConsumerSecret = getValue("/keys/consumer/secret");
		AccessToken = getValue("/keys/access/token");
		AccessTokenSecret = getValue("/keys/access/secret");
	}

	/**
	 * Read XML file
	 * @param filePath XML file path
	 */
	public void loadXML(String filePath) {
		SAXReader reader = new SAXReader();
		try {
			xml = reader.read(filePath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return value ​​in XPath format
	 * @param XPath XPath format
	 * @return Value
	 */
	public String getValue(String XPath) {
		String value = "";
		try {
			List<?> nodes = xml.selectNodes(XPath);
			for(Iterator<?> i = nodes.iterator(); i.hasNext();) {
				Node node = (Node) i.next();
				value = node.getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Return consumer key
	 * @return Consumer key
	 */
	String getConsumerKey() {
		return ConsumerKey;
	}

	/**
	 * Return consumer secret
	 * @return Consumer secret
	 */
	String getConsumerSecret() {
		return ConsumerSecret;
	}

	/**
	 * Return access token
	 * @return Access token
	 */
	String getAccessToken() {
		return AccessToken;
	}

	/**
	 * Return access token secret
	 * @return Access token secret
	 */
	String getAccessTokenSecret() {
		return AccessTokenSecret;
	}

}