package jsoncreater;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import params.Sensor;

import java.util.ArrayList;

public class JsonCreaterParams
{
   private static final String NAME_PARAM = "ParamsFromArduino";
   private static final String NAME_VALUE = "Value";

    public static String getJsonParams(ArrayList<Sensor> sensorArrayList)
    {
        JsonArray jsonElements = new JsonArray();

        for (Sensor aSensorArrayList : sensorArrayList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(NAME_PARAM, aSensorArrayList.getName());
            jsonObject.addProperty(NAME_VALUE, aSensorArrayList.getValue());
            jsonElements.add(jsonObject);
        }


        return jsonElements.toString();
    }
}
