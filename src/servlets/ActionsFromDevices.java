package servlets;

import com.google.gson.*;
import converter.Converter;
import hash.HashCode;
import jsoncreater.JsonFunction;
import readerfile.GetterTextFromFile;
import responce.PrinterResponseForDevices;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActionsFromDevices")
public class ActionsFromDevices extends HttpServlet {

    public static final String NAME_JSON_ACTIONS_FROM_DEVICES = "/ActionsFromDevices.json";

    private static final String KEY_GET_QUERY = "12345678";
    private static final String KEY_POST_QUERY = "12345678";

    private GetterTextFromFile getterTextFromFile;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        getterTextFromFile = new GetterTextFromFile(getServletContext().getRealPath("/WEB-INF/res/"));
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Posting..."+getServletName());

        if(checkKeyPostRequest(request.getParameter("key")))
        {
            writeJsonActions(request.getParameter("data"));
            new Converter(
                    getServletContext().getRealPath("/WEB-INF/res")+Actions.NAME_JSON_ACTIONS,
                    getServletContext().getRealPath("/WEB-INF/res")+NAME_JSON_ACTIONS_FROM_DEVICES)
                    .convert();
            try {
                HashCode.writeHashCodeOfJsonActionsDevice(getServletContext());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error of write Hash code!");
            }
        }
        else
            System.err.println("    KEY_POST -> [*] WRONG : "+ this.getServletName());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Getting..."+getServletName());

        if(checkKeyGetRequest(request.getParameter("key")))
            PrinterResponseForDevices.printJSON(response,getServletContext());
        else
            System.err.println("    KEY_GET -> [*] WRONG : "+ this.getServletName());
    }

    private void writeJsonActions(String element) throws IOException {
        String jsonFromFile;
        
        jsonFromFile = getterTextFromFile.getTextFromFile(NAME_JSON_ACTIONS_FROM_DEVICES);
        JsonArray jsonElements;
        JsonObject jsonObject;
        
        try
        {
           jsonElements = new JsonParser().parse(jsonFromFile).getAsJsonArray();
        }catch (Exception error){
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(new JsonParser().parse(element).getAsJsonObject());
            getterTextFromFile.writeTextToFile(NAME_JSON_ACTIONS_FROM_DEVICES,jsonArray.toString());
            return;
        }
      
        try {
            jsonObject = new JsonParser().parse(element).getAsJsonObject();
        }catch (Exception error){
            System.err.println("Error of parsing object " + element);
            return;
        }
      
        jsonElements = JsonFunction.putInArray(jsonElements,jsonObject);
        getterTextFromFile.writeTextToFile(NAME_JSON_ACTIONS_FROM_DEVICES,jsonElements.toString());
        
    }

    private boolean checkKeyGetRequest(String key)
    {
        return key.equals(KEY_GET_QUERY);
    }

    private boolean checkKeyPostRequest(String key)
    {
        return key.equals(KEY_POST_QUERY);
    }

}
