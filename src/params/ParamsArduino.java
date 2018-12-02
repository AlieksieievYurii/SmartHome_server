package params;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public class ParamsArduino
{
    public static String NAME_FILE_PARAMS = "/ParamsArduino.json";

    private String fullPath;
    private File file;

    public ParamsArduino(String path)
    {
        this.fullPath = path.concat(NAME_FILE_PARAMS);
        file = new File(fullPath);
    }

    public void writeParams(String text)
    {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error of writing file of params -> " + fullPath);
            createFileParams();
        }
    }

    public String readParams() throws IOException {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createFileParams();
            return null;
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder text = new StringBuilder();
        String str;

        while((str = bufferedReader.readLine()) != null)
            text.append(str);

        bufferedReader.close();

        return text.toString();
    }

    public static String[] convertJsonParamsToArray(String jsonParams)
    {
        JsonElement jsonElement = new JsonParser().parse(jsonParams);
        JsonArray jsonElementsParams = jsonElement.getAsJsonArray();

        String[] res = new String[jsonElementsParams.size()];

        for(int i = 0; i < jsonElementsParams.size(); i++){
            JsonObject jsonObject = (JsonObject) jsonElementsParams.get(i);
            String nameParam = jsonObject.get("name").getAsString();
            int value = jsonObject.get("value").getAsInt();

            res[0] = nameParam;
            res[1] = String.valueOf(value);
        }

        return res;
    }

    private void createFileParams()
    {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
