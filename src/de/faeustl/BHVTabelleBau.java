package de.faeustl;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import de.faeustl.model.Platzierung;
import de.faeustl.model.Tabelle;
import de.faeustl.wordpress.Mannschaftsfinder;
import de.faeustl.wordpress.WPReadWriter;
import de.faeustl.wpDataWriter.bhvWPDataWriter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BHVTabelleBau {

	@SuppressWarnings("unchecked")
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
			
			//System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = "[" + json + "]";
		Gson gson = new GsonBuilder().create();
		List<Tabelle> tabelle = null;

		Type tabelleType = new TypeToken<ArrayList<Tabelle>>() {
		}.getType();

		try {
			tabelle = (List<Tabelle>) gson.fromJson(json, tabelleType);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Platzierung platzierung : tabelle.get(0).Platzierung) {
			Mannschaftsfinder.findBHVMannschaft(platzierung);
		}
		
		Gson gsonOut = new GsonBuilder().setPrettyPrinting()

				// .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)

				.create();
		

		StringWriter stringWriter = new StringWriter();

		JsonWriter jsonWriter = new JsonWriter(stringWriter);
		//writeMessagesArray(jsonWriter, tabelle);
		try {
			bhvWPDataWriter.writeMessagesArray(jsonWriter, tabelle.get(0).Platzierung);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(stringWriter.getBuffer().toString());

		String jsonString = gsonOut.toJson(stringWriter.getBuffer().toString());
		
		WPReadWriter.writeTabelle(0, stringWriter.getBuffer().toString());
		
		//System.out.println(jsonString);

		
	}
}