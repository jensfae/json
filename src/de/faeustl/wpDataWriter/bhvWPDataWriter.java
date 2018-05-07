package de.faeustl.wpDataWriter;

import java.io.IOException;
import java.util.List;

import com.google.gson.stream.JsonWriter;

import de.faeustl.model.Autor;
import de.faeustl.model.Platzierung;
import de.faeustl.model.Tabelle;

/*
"data": {
    "0": {
        "pos": "Pos.",
        "name": "Mannschaft",
        "ptstwo": "Pts-",
        "pts": "Pkt",
        "t": "Sp",
        "w": "W",
        "d": "D",
        "l": "L",
        "f": "F",
        "a": "A",
        "diff": "DIFF"
    },
    "1566": {
        "ptstwo": "2",
        "pts": "0",
        "t": "1",
        "w": "0",
        "d": "0",
        "l": "1",
        "f": "19",
        "a": "26",
        "diff": "-7",
        "name": "HC Sulzb.Rosenb.",
        "pos": 3
    },*/

public  class bhvWPDataWriter {
	
	public static void writeMessagesArray(JsonWriter writer, List<Platzierung> messages) throws IOException {
		writer.beginObject();
		
		for (Platzierung message : messages) {
			writer.name(message.vereinsNummer);
			writeMessage(writer, message);
		}
		writer.endObject();
	}
	public static void writeMessage(JsonWriter writer, Platzierung pPlatzierung) throws IOException {
		writer.beginObject();
		//writer.name(pPlatzierung.vereinsName);
		
		//writer.beginArray();
		writer.name("t").value(pPlatzierung.spiele);
		writer.name("w").value(pPlatzierung.siege);
		writer.name("d").value(pPlatzierung.unentschieden);
		writer.name("l").value(pPlatzierung.nierdlagen);
		writer.name("name").value(pPlatzierung.vereinsName);
		//writer.endArray();
		
		writer.endObject();
	}
}
