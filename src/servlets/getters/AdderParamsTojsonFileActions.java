package servlets.getters;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AdderParamsTojsonFileActions
{

    private static final String PROPERTY_PARAMS_FROM_ARDUINO = "ParamsFromArduino";
    private static final String PROPERTY_ACTIONS  = "Actions";
    public static String addParams(String json,String params)
    {
        JsonElement jsonElementActions = new JsonParser().parse(json);
        JsonElement jsonElementParams = new JsonParser().parse(params);

        JsonArray jsonArrayActions =  jsonElementActions.getAsJsonArray();
        JsonArray jsonArrayParams =  jsonElementParams.getAsJsonArray();


        JsonObject res = new JsonObject();
        res.add(PROPERTY_PARAMS_FROM_ARDUINO,jsonArrayParams);
        res.add(PROPERTY_ACTIONS,jsonArrayActions);

        return res.toString();
    }
}
