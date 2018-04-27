package de.faeustl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BHVTabelleBau {

	public static void main(String[] args) {
		OkHttpClient client = new OkHttpClient();

		// MediaType mediaType = MediaType.parse("application/json");
		// RequestBody body = RequestBody.create(mediaType,
		// "{\n\"fields\":\n\t{\n\t\"nachname\":\"Fäustl\", \"vorname\":\"ich
		// will\"\n\t}\n}");
		String json = "{\"data\":{\"1667\":{\"pts\":\"21\", \"name\": \"ESV\" }}}";
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));

		Request request = new Request.Builder().url("https://openwhisk.eu-de.bluemix.net/api/v1/web/jfaeustl%40de.ibm.com_firstOne/ESV/BHVTabellen.json?mannschaft=Herren I").get()
				.addHeader("Content-Type", "application/json").addHeader("Cache-Control", "no-cache")
				.addHeader("Postman-Token", "b3288194-08e2-4679-a101-cd0af9373f9e")
				.addHeader("Authorization", "Basic " + basicAuth).post(RequestBody.create(MediaType.parse(json), json))
				.build();
		Response response = null;

		try {
			response = client.newCall(request).execute();
			json = response.body().string();
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}