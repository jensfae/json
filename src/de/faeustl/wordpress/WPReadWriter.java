package de.faeustl.wordpress;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class WPReadWriter {

	
	public static void writeTabelle(int pTabellenNummer, String pData) {
		
		OkHttpClient client = new OkHttpClient();
		String json = "{\"data\":" + pData + "}";
		String url = "http://handball.esv1927.de/wp-json/wp/v2/sp_table/43358";
		System.out.println(json);
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));

		Request request = new Request.Builder().url(url).get()
				.addHeader("Content-Type", "application/json").addHeader("Cache-Control", "no-cache")
				.addHeader("Postman-Token", "b3288194-08e2-4679-a101-cd0af9373f9e")
				.addHeader("Authorization", "Basic " + basicAuth)
				.post(RequestBody.create(MediaType.parse(json), json))
				.build();
		try {
			client.newCall(request).execute();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
