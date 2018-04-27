package de.faeustl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.faeustl.model.Autor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestMain {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		OkHttpClient client = new OkHttpClient();

		// MediaType mediaType = MediaType.parse("application/json");
		// RequestBody body = RequestBody.create(mediaType,
		// "{\n\"fields\":\n\t{\n\t\"nachname\":\"Fäustl\", \"vorname\":\"ich
		// will\"\n\t}\n}");
		String json = "{\"data\":{\"1667\":{\"pts\":\"21\", \"name\": \"ESV\" }}}";
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		
		Request request = new Request.Builder().url("http://handball.esv1927.de/wp-json/wp/v2/sp_table/1913").get()
				.addHeader("Content-Type", "application/json")
				.addHeader("Cache-Control", "no-cache")
				.addHeader("Postman-Token", "b3288194-08e2-4679-a101-cd0af9373f9e")
				.addHeader("Authorization", "Basic "+basicAuth)
				.post(RequestBody.create(MediaType.parse(json), json))
				.build();
		Response response = null;
		String jsons = "";
		try {
			response = client.newCall(request).execute();
			json = response.body().string();
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*Gson gson = new GsonBuilder().create();
		Autor[] autorenArray = null;
		try {
			autorenArray = gson.fromJson(json, Autor[].class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gsonOut = new GsonBuilder().setPrettyPrinting()

				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)

				.create();

		String jsonString = gsonOut.toJson(autorenArray);
		System.out.println(jsonString);
*/
	}

}
