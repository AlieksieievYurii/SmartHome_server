package jsoncreater;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonCreaterForArduino
{
    public static final String JSON_TYPE = "T";
    public static final String JSON_TYPE_PWM = "A";
    public static final String JSON_TYPE_DIGITAL = "D";
    public static final String JSON_PIN = "P";
    public static final String JSON_STATUS = "S";//it is for Digital pins
    public static final String JSON_STATUS_DEFAULT_LOW = "L";//it is for Digital pins
    public static final String JSON_VALUE = "V";//it is for analog(PWM) pins

    private static final int[] PINS_ARDUINO_DIGITAL = {22,23,24,25,26,27,
                                                28,29,30,31,32,
                                                33,34,35,36,37,
                                                38,39,40,41,42,
                                                43,44,45,46,47,
                                                48,49,50,51,52,53};

    private static final int[] PINS_ARDUINO_PWM = {2,3,5,6,7,8,9,10,11,12,13};

    public static String getDefaultStringJSONforArduino()
    {
        JsonArray jsonElements = new JsonArray();
        for (int aPINS_ARDUINO_DIGITAL : PINS_ARDUINO_DIGITAL) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(JSON_TYPE, JSON_TYPE_DIGITAL);
            jsonObject.addProperty(JSON_PIN, aPINS_ARDUINO_DIGITAL);
            jsonObject.addProperty(JSON_STATUS, JSON_STATUS_DEFAULT_LOW);

            jsonElements.add(jsonObject);
        }

        for (int aPINS_ARDUINO_PWM : PINS_ARDUINO_PWM) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(JSON_TYPE, JSON_TYPE_PWM);
            jsonObject.addProperty(JSON_PIN, aPINS_ARDUINO_PWM);
            jsonObject.addProperty(JSON_VALUE, 0);

            jsonElements.add(jsonObject);
        }
        return jsonElements.toString();
    }

}
