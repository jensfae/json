package de.faeustl.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = Visibility.NONE, setterVisibility = Visibility.PUBLIC_ONLY)
public class ExtraFields {
	private String rendered;
	private String _protected;
	
	public String getRendered() {
		return rendered;
	}


	public String get_protected() {
		return _protected;
	}
	
	@JsonCreator
	public ExtraFields(@JsonProperty("protected") String rendered, @JsonProperty("rendered") String _protected) {
		this.rendered = rendered;
		this._protected = _protected;
	}

}

