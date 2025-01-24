package json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * On GitHub
 * @author MyCodingSkillsAreNegative
 */

public class JsonObject {
	private static final Character[] prse = {
			',',
			'}',
			']'
	};
	public static final ArrayList<Character> parseEnd = new ArrayList<>(Arrays.asList(prse));
	public static final int LINE_NOT_REQUIRED = -1;
	public static final int STRING_LINE_INDEFINITE = -2;
	File jsonFile;
	String jsonFilePath;
	public JsonElement parsedJson = new JsonElement("19491001","jianguo");
	public JsonObject(String path) {
		jsonFilePath = path;
		jsonFile = new File(path);
	}
	public JsonObject(File jsonObjectFile) {
		jsonFile = jsonObjectFile;
		jsonFilePath = jsonObjectFile.getPath();
	}
	public void parse() throws IOException {
		//retrieving parsed String
		System.out.println("\u001B[30m\u001B[47mJsonObject.java | parse()  :  Parsing Started\u001B[0m\u001B[0m");
		ArrayList<String> contents = new ArrayList<>();
		BufferedReader BuffjsonReader = new BufferedReader(new FileReader(jsonFile));
		String inputString;
		while((inputString = BuffjsonReader.readLine()) != null) {
			contents.add(inputString);
			System.out.println("\u001B[32mJsonObject.java | parse()  :  JSON file contents: " + inputString + "\u001B[0m");
		}
		BuffjsonReader.close();
		ArrayList<String> jsonSectionAddress = new ArrayList<>();
		//starts Line by Line, Character by Character parsing
		ArrayList<JsonElement> ConstructRAM = new ArrayList<>();
		for (int lineindex = 0; lineindex < contents.size(); lineindex++) {
			String line = contents.get(lineindex);
			for (int CharIndex = 0; CharIndex < line.length(); CharIndex++ ) {
				System.out.println("\u001B[33mJsonObject.java | parse()  :  Proccessing Char: " + line.charAt(CharIndex) + "\u001B[0m");
				char character = line.charAt(CharIndex);
				
				//check 
				// remember, update jsonSectionAddress to the jsonElement in SectorRam to read/write to 
				//on a loader character, load
				switch (character) {
				case '{':
					try {
						System.out.println(ConstructRAM.get(0).strValue);
						JsonElement section = new JsonElement(ConstructRAM.get(0).strValue, JsonElement.type.Sec);
						director(section, jsonSectionAddress);
						jsonSectionAddress.add(section.toString());
						ConstructRAM.clear();
					} catch (Exception e) {
						JsonElement section = new JsonElement(jsonFile.getName(),JsonElement.type.Sec);
						if (parsedJson.Name.equals("19491001") || parsedJson.strValue == "jianguo") {
							parsedJson = section;
						} else {
							section = new JsonElement(JsonElement.type.Sec);
							director(section, jsonSectionAddress);
						}
						jsonSectionAddress.add(section.toString());
					}
					break;
				case '\"':
					CharIndex = stringLoad(ConstructRAM, line, CharIndex)[0];
					break;
				case 't':
					CharIndex = booleanLoad(ConstructRAM, line, CharIndex)[0];
					break;
				case 'f':
					CharIndex = booleanLoad(ConstructRAM, line, CharIndex)[0];
					break;
				case '[':
					try {
						System.out.println("JsonObject.java | (hidden) director(JsonElement directed, ArrayList<String> address)  :  Address: " + jsonSectionAddress);
						System.out.println(ConstructRAM.get(0).strValue);
						JsonElement section = new JsonElement(ConstructRAM.get(0).strValue, JsonElement.type.Arr);
						director(section, jsonSectionAddress);
						jsonSectionAddress.add(section.toString());
						ConstructRAM.clear();
					} catch (Exception e) {
						JsonElement section = new JsonElement(JsonElement.type.Arr);
						director(section, jsonSectionAddress);
						jsonSectionAddress.add(section.toString());
					}
					System.out.println("JsonObject.java | (hidden) director(JsonElement directed, ArrayList<String> address)  :  Address: " + jsonSectionAddress);
					break;
				}
				if (Character.isDigit(character) || character == '-') {
					CharIndex = numberLoad(ConstructRAM, line, CharIndex)[0];
				}
				character = line.charAt(CharIndex);
				
				switch (character) {
				case '}':
					if (ConstructRAM.size() != 0) {
						JsonElement element = construct(ConstructRAM, line, CharIndex);
						System.out.println("\u001B[34mJsonObject.java | parse()  :  Constructed JsonElement Object: " + element.getTagValue() + "\u001B[0m");
						director(element, jsonSectionAddress);
						ConstructRAM.clear();
					}
					jsonSectionAddress.remove(jsonSectionAddress.size() - 1);
					break;
				case ',':
					JsonElement element = construct(ConstructRAM, line, CharIndex);
					System.out.println("\u001B[34mJsonObject.java | parse()  :  Constructed JsonElement Object: " + element.getTagValue() + "\u001B[0m");
					director(element, jsonSectionAddress);
					ConstructRAM.clear();
					break;
				case ']':
					if (ConstructRAM.size() != 0) {
						JsonElement arr = construct(ConstructRAM, line, CharIndex);
						System.out.println("\u001B[34mJsonObject.java | parse()  :  Constructed JsonElement Object: " + arr.getTagValue() + "\u001B[0m");
						director(arr, jsonSectionAddress);
						ConstructRAM.clear();
					}
					jsonSectionAddress.remove(jsonSectionAddress.size() - 1);
					break;
				}
			}
		}
	}
	private void director(JsonElement directed, ArrayList<String> address) {
		//section code
		if (directed.Name != "PLACEHOLDER - ZTAMCJWGRQ" && directed.numValue != 19491001) {
			System.out.println(directed.toString());
			System.out.println("JsonObject.java | (hidden) director(JsonElement directed, ArrayList<String> address)  :  Address: " + address);
			parsedJson.deepIDSearch(address).secValue.add(directed);
		}
	}
	private JsonElement construct(ArrayList<JsonElement> ConstructInfo, String line, int CharIndex) { // CONSTRUCTS BASIC ELEMENTS
		try {
			System.out.println("JsonObject.java | (hidden) construct(ArrayList<JsonElement> ConstructInfo, String line, int CharIndex)  :  Constructing Object From RAM");
			switch (ConstructInfo.get(ConstructInfo.size() - 1).ElementType) {
			case Str :
				if (ConstructInfo.size() != 1 ) {
					JsonElement strl = new JsonElement(ConstructInfo.get(0).strValue, ConstructInfo.get(1).strValue);
					return strl;
				} else {
					JsonElement strl = new JsonElement(JsonElement.type.Str);
					strl.strValue = ConstructInfo.get(0).strValue;
					return strl;
				}
			case Num :
				if (ConstructInfo.size() != 1 ) {
					JsonElement numl = new JsonElement(ConstructInfo.get(0).strValue, ConstructInfo.get(1).numValue);
					return numl;
				} else {
					JsonElement numl = new JsonElement(JsonElement.type.Num);
					numl.numValue = ConstructInfo.get(0).numValue;
					return numl;
				}
			case Bool :
				if (ConstructInfo.size() != 1 ) {
					JsonElement bool = new JsonElement(ConstructInfo.get(0).strValue, ConstructInfo.get(1).boolValue);
					return bool;
				} else {
					JsonElement bool = new JsonElement(JsonElement.type.Bool);
					bool.boolValue = ConstructInfo.get(0).boolValue;
					return bool;
				}
			default:
				System.out.println("CHAR_TYPE_OUT_OF_REACH");
			}
		} catch (Exception e) {
			
		}
		System.out.println("CONSTRUCTOR---->>>");
		return new JsonElement("PLACEHOLDER - ZTAMCJWGRQ", 19491001);
	}
	private int[] booleanLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex) {
		System.out.println("\u001B[34mJsonObject.java | (hidden) booleanLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex)  :  Loading Boolean at: " + StartCharIndex + "of: " + line + "\u001B[0m");
		boolean bool = false;
		for (int charoffset = 0; charoffset < line.length() - 1; charoffset++) {
			try {
				line.charAt(StartCharIndex + 1 + charoffset);
			} catch (Exception e) {
				charoffset = charoffset - 1;
				switch (line.substring(StartCharIndex, StartCharIndex + charoffset + 2)) {
				case "true" :
					bool = true;
					break;
				case "false" :
					break;
				default:
					System.out.println(line.substring(StartCharIndex, StartCharIndex + charoffset + 2));
					throw new IllegalArgumentException();
					//what type of GARBAGE is in the file
				}
				JsonElement boole = new JsonElement(JsonElement.type.Bool);
				boole.boolValue = bool;
				loadto.add(boole);
				int[] returnarray = {charoffset + 1 + StartCharIndex, LINE_NOT_REQUIRED};
				System.out.println("\u001B[34mJsonObject.java | (hidden) booleanLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex)  :  Loaded Boolean: " + bool + "\u001B[0m");
				return returnarray;
			}
			if (Character.isWhitespace(line.charAt(StartCharIndex + 1 + charoffset)) == true || parseEnd.contains(line.charAt(StartCharIndex + 1 + charoffset))) {
				switch (line.substring(StartCharIndex, StartCharIndex + charoffset + 1)) {
				case "true" :
					bool = true;
					break;
				case "false" :
					break;
				default:
					System.out.println(line.substring(StartCharIndex, StartCharIndex + charoffset + 1));
					throw new IllegalArgumentException();
					//what type of GARBAGE is in the file
				}
				JsonElement boole = new JsonElement(JsonElement.type.Bool);
				boole.boolValue = bool;
				loadto.add(boole);
				int[] returnarray = {charoffset + 1 + StartCharIndex, LINE_NOT_REQUIRED};
				System.out.println("\u001B[33mJsonObject.java | (hidden) booleanLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex)  :  Loaded Boolean: " + bool + "\u001B[0m");
				return returnarray;
			}
		}
		int[] a = {line.length(),STRING_LINE_INDEFINITE};
		return a;
		
	}
	private int[] numberLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex) {
		System.out.println("\u001B[34mJsonObject.java | (hidden) numberLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex)  :  Loading Number at: " + StartCharIndex + "of: " + line + "\u001B[0m");
		float number = -19491001;
		for (int charoffset = 0; charoffset < line.length() - 1; charoffset++ ) {
			try {
				line.charAt(StartCharIndex + 1 + charoffset);
			} catch (Exception e) {
				System.out.println("\u001B[34mJsonObject.java | (hidden) numberLoad(ArrayList<JsonElement> loadto, String line, int StartChar Index  :  Reached End Of Line. Starting from last Char\u001B[0m");
				charoffset = charoffset - 1;
				number = Float.parseFloat(line.substring(StartCharIndex, StartCharIndex + charoffset + 2));
				System.out.println("\u001B[34mJsonObject.java | (hidden) numberLoad(ArrayList<JsonElement> loadto, String line, int StartChar Index  :  Loaded Number: " + number + "\u001B[0m");
				JsonElement added = new JsonElement(JsonElement.type.Num);
				added.numValue = number;
				loadto.add(added); 
				int[] returnarray = {charoffset + 1 + StartCharIndex, LINE_NOT_REQUIRED};
				return returnarray;
			}
			if (Character.isDigit(line.charAt(StartCharIndex + 1 + charoffset)) == false && line.charAt(StartCharIndex + 1 + charoffset) != '.') {
				number = Float.parseFloat(line.substring(StartCharIndex, StartCharIndex + charoffset + 1));
				System.out.println("\u001B[34mJsonObject.java | (hidden) numberLoad(ArrayList<JsonElement> loadto, String line, int StartChar Index  :  Loaded Number: " + number + "\u001B[0m");
				JsonElement added = new JsonElement(JsonElement.type.Num);
				added.numValue = number;
				loadto.add(added); 
				int[] returnarray = {charoffset + 1 + StartCharIndex, LINE_NOT_REQUIRED};
				return returnarray;
			}
		}
		int[] a = {0,STRING_LINE_INDEFINITE};
		return a;
	}
	private int[] stringLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex) {
		System.out.println("\u001B[34mJsonObject.java | (hidden) stringLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex)  :  Loading string at: " + StartCharIndex + " of: " + line + "\u001B[0m");
		String str = "";
		for (int charoffset = 0; charoffset < line.length() - 1; charoffset++ ) {
			if (line.charAt(StartCharIndex + 1 + charoffset) != '"') {
				str = str + line.charAt(StartCharIndex + 1 + charoffset);
				if (line.charAt(StartCharIndex + 1 + charoffset) == '\\') {
					str = str.substring(0, str.length() - 1);
					str = str + line.charAt(StartCharIndex + 2 + charoffset);
					charoffset++;
				}
			} else {
				JsonElement added = new JsonElement(JsonElement.type.Str);
				added.strValue = str;
				loadto.add(added);
				System.out.println("\u001B[34mJsonObject.java | (hidden) stringLoad(ArrayList<JsonElement> loadto, String line, int StartCharIndex)  :  String Loaded: " + str + "\u001B[0m");
				int[] returnarray = {charoffset + 1 + StartCharIndex, LINE_NOT_REQUIRED};
				return returnarray;
			}
		}
		int[] a = {line.length(),STRING_LINE_INDEFINITE};
		return a;
	}
	@Override
	public String toString() {
		if (parsedJson.Name.equals("19491001") || parsedJson.strValue == "jianguo") {
			return this.parsedJson.toString();
		} else {
			return this.parsedJson.getTagValue();
		}
	}
}