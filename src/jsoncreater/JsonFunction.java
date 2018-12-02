package jsoncreater;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonFunction
{
    public static JsonArray putInArray(JsonArray jsonArray, JsonObject jsonObject)
    {
        JsonArray newArray = new JsonArray();
        boolean flag = false;

        for(int i = 0; i < jsonArray.size(); i++)
        {
            JsonObject jsonObjectFromArray = jsonArray.get(i).getAsJsonObject();
            if(jsonObjectFromArray.get("P").getAsInt() == jsonObject.get("P").getAsInt()) {
                newArray.add(jsonObject);
                flag = true;
            }
            else
                newArray.add(jsonObjectFromArray);
        }

        if(!flag)
            newArray.add(jsonObject);
        return newArray;
    }
}
