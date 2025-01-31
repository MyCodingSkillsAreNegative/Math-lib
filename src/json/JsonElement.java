package json;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
 * On GitHub
 * @author MyCodingSkillsAreNegative
 */

public class JsonElement {
	//The name of a element without a name
	public static final String ELEMENT_ANONYMOUS = "ELEMENT_NO_NAME_INA:hn2Z19491001S19221230";
	//Data type enums
	enum type {
		Str,
		Num,
		Sec,
		Bool,
		Arr
	}
	//Element type
	type ElementType;
	//Element name
	public String Name;
	//Values
	String strValue;
	float numValue;
	boolean boolValue;
	ArrayList<JsonElement> secValue = new ArrayList<JsonElement>(); //also for Arr
	public JsonElement(String name, String val) {
		Name = name;
		strValue = val;
		ElementType = type.Str;
	}
	public JsonElement(String name, float val) {
		Name = name;
		numValue = val;
		ElementType = type.Num;
	}
	public JsonElement(String name, boolean val) {
		Name = name;
		boolValue = val;
		ElementType = type.Bool;
	}
	public JsonElement(String name, type ELEMENT_TYPE) {
		Name = name;
		ElementType = ELEMENT_TYPE;
	}
	public JsonElement(type VALUE) {
		Name = ELEMENT_ANONYMOUS;
		ElementType = VALUE;
	}
	public Object getValue() {
		switch (this.ElementType) {
		case Str :
			return strValue;
		case Num :
			return numValue;
		case Sec :
			return secValue;
		case Bool :
			return boolValue;
		case Arr:
			return secValue;
		default : 
			return "ERROR: UNCONFINED ENUM VALUE";
		}
	}
	private boolean checkAnonymous() {
		if (this.Name == ELEMENT_ANONYMOUS) {
			return true;
		} else {
			return false;
		}
	}
	public String getTagValue() {
		switch (this.ElementType) {
		case Str:
			if (this.checkAnonymous()) {
				return "\"" + this.strValue + "\"";
			}
			String strtag = "\"" + this.Name + "\":\"" + this.strValue + "\"";
			return strtag;
		case Num:
			if (this.checkAnonymous()) {
				return this.numValue + "";
			}
			String numtag = "\"" + this.Name + "\":" + this.numValue;
			return numtag;
		case Bool:
			if (this.checkAnonymous()) {
				return this.boolValue + "";
			}
			String booltag = "\"" + this.Name + "\":" + this.boolValue;
			return booltag;
		case Sec:
			if (this.checkAnonymous()) {
				return "{"+this.strOfContents() + "}";
			}
			String sectag = "";
			sectag = "\"" + this.Name + "\":{" + this.strOfContents() + "}";
			return sectag;
		case Arr:
			if (this.checkAnonymous()) {
				return "["+this.strOfContents() + "]";
			}
			String arrtag = "";
			arrtag = "\"" + this.Name + "\":[" + this.strOfContents() + "]";
			return arrtag;
		default:
			return "A";
		}
	}
	private String strOfContents() {
		String returned  = "";
		for (JsonElement i : this.secValue) {
			returned = returned + i.getTagValue() + ",";
		}
		returned = returned.substring(0, returned.length() - 1);
		return returned;
	}
	public JsonElement deepSearch(String...location) {
		JsonElement parent = this;
		for (int i  = 0; i < location.length; i++) {
			parent = parent.surfaceSearch(location[0]);
		}
		return parent;
	}
	public JsonElement deepSearch(ArrayList<String> a) {
		JsonElement parent = this;
		for (int i  = 0; i < a.size(); i++) {
			parent = parent.surfaceSearch(a.get(i));
		}
		return parent;
	}
	public JsonElement surfaceSearch(String surfaceIDTag) {
		if (surfaceIDTag == this.Name) {
			return this;
		}
		for (JsonElement i : this.secValue) {
			if (i.Name == surfaceIDTag) {
				return i;
			}
		}
		return new JsonElement("NOT FOUND", "NOT FOUND");
	}
	private JsonElement SufacefromString(String objID) {
		if (this.toString().equals(objID)) {
			return this;
		}
		for (JsonElement i : this.secValue) {
			if (i.toString().equals(objID)) {
				return i;
			}
		}
		throw new NoSuchElementException();
	}
	public JsonElement deepIDSearch(String ...ID) {
		JsonElement parent = this;
		for (int i = 0; i < ID.length; i++) {
			parent = parent.SufacefromString(ID[i]);
		}
		return parent;
	}
	public JsonElement deepIDSearch(ArrayList<String> address) {
		JsonElement parent = this;
		for (int i = 0; i < address.size(); i++) {
			parent = parent.SufacefromString(address.get(i));
		}
		return parent;
	}
}