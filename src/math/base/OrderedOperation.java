package math.base;

import java.io.IOException;

import json.*;

public class OrderedOperation {
	public JsonObject operations;
	public OrderedOperation(String json) {
		JsonObject op = new JsonObject(json);
		try {
			op.parse();
		} catch (IOException e) {
		}
		operations = op;
	}
}
