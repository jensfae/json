package de.faeustl.model;

public class Autor {
	public String anrede;
	public String titel;
	public String vorname;
	public String nachname;
	public ExtraFields title;
	public String getTitle() {
		return title.getRendered();
	}
	public ExtraFields content;
	public String status;
}
