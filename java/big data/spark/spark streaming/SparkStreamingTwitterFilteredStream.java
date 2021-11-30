package stubs;

import java.util.function.Consumer;

import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.tweet.Tweet;
import io.github.redouane59.twitter.signature.TwitterCredentials;

public class SparkStreamingTwitterFilteredStream {
  
  // Incomplete - Getting the following dependency-related error:
  // Exception in thread "main" java.util.ServiceConfigurationError: com.fasterxml.jackson.databind.Module: com.fasterxml.jackson.datatype.jsr310.JavaTimeModule Unable to get public no-arg constructor
  // 	at java.base/java.util.ServiceLoader.fail(ServiceLoader.java:581)
  // 	at java.base/java.util.ServiceLoader.getConstructor(ServiceLoader.java:672)
  // 	at java.base/java.util.ServiceLoader$LazyClassPathLookupIterator.hasNextService(ServiceLoader.java:1232)
  // 	at java.base/java.util.ServiceLoader$LazyClassPathLookupIterator.hasNext(ServiceLoader.java:1264)
  // 	at java.base/java.util.ServiceLoader$2.hasNext(ServiceLoader.java:1299)
  // 	at java.base/java.util.ServiceLoader$3.hasNext(ServiceLoader.java:1384)
  // 	at com.fasterxml.jackson.databind.ObjectMapper.findModules(ObjectMapper.java:938)
  // 	at com.fasterxml.jackson.databind.ObjectMapper.findModules(ObjectMapper.java:921)
  // 	at com.fasterxml.jackson.databind.ObjectMapper.findAndRegisterModules(ObjectMapper.java:957)
  // 	at io.github.redouane59.twitter.TwitterClient.<clinit>(TwitterClient.java:101)
  // 	at stubs.SparkStreamingTwitterFilteredStream.getTweets(SparkStreamingTwitterFilteredStream.java:20)
  // 	at stubs.SparkStreamingTwitterFilteredStream.main(SparkStreamingTwitterFilteredStream.java:14)
	
	private static final String BEARER_TOKEN = "my token";
	
	public static void main(String[] args) {
		getTweets();
	}
	
	// Retrieves a response from the Twitter Filtered Stream API
	public static void getTweets() {
		// Create the client
		TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
				.bearerToken(BEARER_TOKEN)
				.build());
		
		// Create the Consumer object
		Consumer<Tweet> consumer = (Tweet tweet) -> System.out.println("Tweet accepted: " + tweet.getId());
		
		// Start the filtered stream
		twitterClient.startFilteredStream(consumer);
	}
}
