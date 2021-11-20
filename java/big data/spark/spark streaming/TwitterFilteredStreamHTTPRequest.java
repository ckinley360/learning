package stubs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class TwitterFilteredStreamHTTPRequest {

	private static final String BEARER_TOKEN = "my token";
	
	// Retrieves a response from the Twitter Filtered Stream REST API
	public static String getTweets() {
		// Create the client
		HttpClient client = HttpClient.newHttpClient();
		
		// Create the request
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.twitter.com/2/tweets/search/stream?tweet.fields=created_at&expansions=author_id&user.fields=location"))
			.header("Authorization", "Bearer " + BEARER_TOKEN)
			.GET()
			.build();
	}
}
