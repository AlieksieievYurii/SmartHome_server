package servlets;
import json_creater.JsonCreaterParams;
import params.ParamsArduino;
import reader_file.ReadWriteFile;
import servlets.getters.GetterJSONArduino;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "Actions")
public class Actions extends HttpServlet {

    public static final String NAME_JSON_ACTIONS = "\\ActionsArduino.json";

    private static final String NAME_PARAM_LIGHT = "light";
    private static final String KEY_GET_QUERY = "3we42fi27rh";
    private static final String KEY_POST_QUERY = "JbhT692Hy2";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        System.err.println("Posting....");
        String key = request.getParameter("key");

        if(checkKeyPOSTrequest(key))
        {
            //System.err.println("    KEY_POST -> [*] CORRECT");
            String data = request.getParameter("data");
            writeJsonActions(data);
        }else
            System.err.println("    KEY_POST -> [*] WRONG");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        //System.err.println("Request...");
        String keyFromArduino = request.getParameter("key");

        if(checkKeyGETrequest(keyFromArduino))
        {
            //System.err.println("    KEY_GET -> [*] CORRECT");
            getAndWriteParamsGETrequest(request);
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

        GetterJSONArduino getterJSONArduino = new GetterJSONArduino(getServletContext().getRealPath("/WEB-INF/res"));
        PrintWriter printWriter = response.getWriter();
        printWriter.print(getterJSONArduino.getJSONforArduino());
        printWriter.close();
    }

    private void getAndWriteParamsGETrequest(HttpServletRequest request)
    {
        String light = request.getParameter(NAME_PARAM_LIGHT);

        ParamsArduino paramsArduino = new ParamsArduino(getServletContext().getRealPath("/WEB-INF/res"));
        paramsArduino.writeParams(JsonCreaterParams.getJsonParams(light));
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
