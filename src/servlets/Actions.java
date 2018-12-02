package servlets;
import jsoncreater.JsonCreaterParams;
import params.ParamsArduino;
import params.Sensor;
import readerfile.ReadWriteFile;
import readerfile.GetterTextFromFile;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "Actions")
public class Actions extends HttpServlet {

    public static final String NAME_JSON_ACTIONS = "\\ActionsArduino.json";

    private static final String NAME_PARAM_LIGHT = "light";
    private static final String NAME_PARAM_TEMPERATURE = "temperature";
    private static final String NAME_PARAM_HUMIDITY = "humidity";

    private static final String KEY_GET_QUERY = "3we42fi27rh";
    private static final String KEY_POST_QUERY = "JbhT692Hy2";

    private static final String[] NAME_PARAMETERS = {NAME_PARAM_TEMPERATURE,
                                                        NAME_PARAM_HUMIDITY
                                                        ,NAME_PARAM_LIGHT};

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        System.err.println("Posting...." + getServletName());
        String key = request.getParameter("key");

        if(checkKeyPOSTrequest(key))
        {
            //System.err.println("    KEY_POST -> [*] CORRECT" + this.getServletName()));
            String data = request.getParameter("data");
            writeJsonActions(data);
        }else
            System.err.println("    KEY_POST -> [*] WRONG : " + this.getServletName());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        System.err.println("Request..."+getServletName());
        String keyFromArduino = request.getParameter("key");

        if(checkKeyGETrequest(keyFromArduino))
        {
            //getAndWriteParamsGETrequest(request);
            printJSON(response);
        }else {
            System.err.println("    KEY_GET -> [*] WRONG");
            response.getWriter().print("Wrong key...\n" + new Date().getTime());
        }
    }

    private void writeJsonActions(String data)
    {
        ReadWriteFile readWriteFile = new ReadWriteFile(getServletContext().getRealPath("/WEB-INF/res"),NAME_JSON_ACTIONS);
        readWriteFile.writeFile(data);
    }

    private void printJSON(HttpServletResponse response) throws IOException {

        GetterTextFromFile getterTextFromFile = new GetterTextFromFile(getServletContext().getRealPath("/WEB-INF/res"));
        PrintWriter printWriter = response.getWriter();
        printWriter.print(getterTextFromFile.getTextFromFile(NAME_JSON_ACTIONS));
        printWriter.close();
    }

    private void getAndWriteParamsGETrequest(HttpServletRequest request)
    {
        ParamsArduino paramsArduino = new ParamsArduino(getServletContext().getRealPath("/WEB-INF/res"));
        paramsArduino.writeParams(JsonCreaterParams.getJsonParams(getParams(request)));
    }

    private ArrayList<Sensor> getParams(HttpServletRequest httpServletRequest)
    {
       ArrayList<Sensor> arrayListSensor = new ArrayList<>();

       for(String nameParam : NAME_PARAMETERS)
       {
           int value = Integer.parseInt(httpServletRequest.getParameter(nameParam));
           arrayListSensor.add(new Sensor(nameParam,value));
       }

        return arrayListSensor;
    }

    private boolean checkKeyGETrequest(String key)
    {
        return key.equals(KEY_GET_QUERY);
    }

    private boolean checkKeyPOSTrequest(String key)
    {
        return key.equals(KEY_POST_QUERY);
    }
}
