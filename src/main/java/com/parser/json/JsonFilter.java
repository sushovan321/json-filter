package com.parser.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonFilter {

    private JsonObject jsonObject;

    public String getFilteredJSON(String jsonString) {
        jsonObject = new Gson().fromJson(
            jsonString, new TypeToken<JsonObject>() {
            }.getType()
        );
        filterEmptyKeyAndValue(jsonObject);
        return jsonObject.toString();
    }

    private boolean filterEmptyKeyAndValue(Object jsonArg) {
        boolean valueExist = false;
        if (jsonArg instanceof JsonPrimitive) {
            valueExist = JsonFilterHelper
                .validateTextValueIfEmptyOrNull(((JsonPrimitive) jsonArg).getAsString());
        } else if (jsonArg instanceof JsonArray) {
            JsonArray arr = (JsonArray) jsonArg;
            for (int i = 0; i < arr.size(); i++) {
                if (filterEmptyKeyAndValue(arr.get(i))) {
                    valueExist = true;
                } else {
                    arr.remove(i);
                    i--;
                }
            }
        } else if (jsonArg instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) jsonArg;
            Iterator<String> keys = jsonObject.keySet().iterator();
            ArrayList<String> fields = new ArrayList<>();
            while (keys.hasNext()) {
                fields.add(keys.next());
            }
            for (String field : fields) {
                Object fieldVal = jsonObject.get(field);
                if (filterEmptyKeyAndValue(fieldVal)) {
                    valueExist = true;
                } else {
                    jsonObject.remove(field);
                }

            }

        }
        return valueExist;

    }


}
