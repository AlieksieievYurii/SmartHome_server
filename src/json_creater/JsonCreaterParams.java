package json_creater;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonCreaterParams
{
    private static final String JSON_LIGHT = "light";

    public static String getJsonParams(String light)
    {
        JsonArray jsonElements = new JsonArray();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(JSON_LIGHT,light);
        //TODO Gota add more params
        jsonElements.add(jsonObject);
        return jsonElements.toString();
    }
}
