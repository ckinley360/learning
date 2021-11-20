package stubs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class TwitterFilteredStreamHTTPRequest {

	private static final String BEARER_TOKEN = "my token";
	
	public static void main(String[] args) {
		System.out.println(getTweets());
	}
	
	// Retrieves a response from the Twitter Filtered Stream API
	public static String getTweets() {
		// Create the client
		HttpClient client = HttpClient.newHttpClient();
		
		// Create the request
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.twitter.com/2/tweets/search/stream?tweet.fields=created_at&expansions=author_id&user.fields=location"))
			.header("Authorization", "Bearer " + BEARER_TOKEN)
			.GET()
			.build();
		
		// Send the request and receive the response
		try {
			client.sendAsync(request, BodyHandlers.ofString())
				.thenApply((HttpResponse response) -> String.valueOf(response.statusCode()) + "\n" + response.body() + "\n" + "\n")
				.thenAccept(responseString -> return responseString);
			String responseString = String.valueOf(response.statusCode()) + "\n" + response.body();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
}
