package de.faeustl.wordpress;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import de.faeustl.model.Platzierung;
import de.faeustl.model.Tabelle;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Mannschaftsfinder {

	public static void findBHVMannschaft(Platzierung pPlatzierung) {
		
		OkHttpClient client = new OkHttpClient();

		String json = "";
		String userCredentials = "jensfae:cwa0503mu";
		String basicAuth = Base64.getEncoder().encodeToString((userCredentials).getBytes(StandardCharsets.UTF_8));
		
		String url ="http://handball.esv1927.de/wp-json/wp/v2/sp_team?fields=id,title&filter[meta_value]=" + pPlatzierung.vereinsName + "&filter[meta_key]=liga_name";
		
		
		Request request = new Request.Builder().url(url).get()
				.addHeader("Content-Type", "application/json").addHeader("Cache-Control", "no-cache")
				.addHeader("Postman-Token", "b3288194-08e2-4679-a101-cd0af9373f9e")
				.addHeader("Authorization", "Basic " + basicAuth)
				
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
		
		Gson gson = new GsonBuilder().create();
		List<WPMannschaftsSearchResult> listResult = null;

		Type result = new TypeToken<ArrayList<WPMannschaftsSearchResult>>() {
		}.getType();

		try {
			listResult = (List<WPMannschaftsSearchResult>) gson.fromJson(json, result);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pPlatzierung.vereinsNummer = listResult.get(0).id;
		//System.out.print(pPlatzierung);

	}
}
