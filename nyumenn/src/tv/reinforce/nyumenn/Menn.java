package tv.reinforce.nyumenn;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter API class
 * @author reinforchu
 */
public class Menn {
	private static Configuration config = null;

	/**
	 * Initialization
	 * @param ConsumerKey ConsumerKey
	 * @param ConsumerSecret ConsumerSecret
	 * @param AccessToken AccessToken
	 * @param AccessTokenSecret AccessTokenSecret
	 */
	public void oAuthInit(String ConsumerKey, String ConsumerSecret, String AccessToken, String AccessTokenSecret) {
		config = new ConfigurationBuilder()
		.setOAuthConsumerKey(ConsumerKey)
		.setOAuthConsumerSecret(ConsumerSecret)
		.setOAuthAccessToken(AccessToken)
		.setOAuthAccessTokenSecret(AccessTokenSecret)
		.setDebugEnabled(false)
		.build();
	}

	/**
	 * Post Nyumenn so cute!
	 */
	public void soCute() {
		Twitter twitter = new TwitterFactory(config).getInstance();
		try {
			twitter.updateStatus("@nyumenn にゅーめんかわいい！");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}