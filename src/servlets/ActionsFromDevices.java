package servlets;

import converter.Converter;
import params.ParamsArduino;
import reader_file.ReadWriteFile;
import servlets.getters.AdderParamsTojsonFileActions;
import servlets.getters.GetterJSONArduino;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ActionsFromDevices")
public class ActionsFromDevices extends HttpServlet {

    private static final String NAME_JSON_ACTIONS_FROM_DEVICES = "\\ActionsFromDevices.json";

    private static final String KEY_GET_QUERY = "12345678";
    private static final String KEY_POST_QUERY = "12345678";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Posting..."+getServletName());

        if(checkKeyPOSTrequest(request.getParameter("key")))
        {
            writeJsonActions(request.getParameter("data"));
            new Converter(
                    getServletContext().getRealPath("/WEB-INF/res")+Actions.NAME_JSON_ACTIONS,
                    getServletContext().getRealPath("/WEB-INF/res")+NAME_JSON_ACTIONS_FROM_DEVICES)
                    .convert();
        }
        else
            System.err.println("    KEY_POST -> [*] WRONG : "+ this.getServletName());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Getting..."+getServletName());

        if(checkKeyGETrequest(request.getParameter("key")))
            printJSON(response);
        else
            System.err.println("    KEY_GET -> [*] WRONG : "+ this.getServletName());
    }

    private void writeJsonActions(String data)
    {
        ReadWriteFile readWriteFile = new ReadWriteFile(getServletContext().getRealPath("/WEB-INF/res"),NAME_JSON_ACTIONS_FROM_DEVICES);
        readWriteFile.writeFile(data);
    }

    private void printJSON(HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print(getFullJsonForResponce());
        printWriter.close();
    }

    private String getFullJsonForResponce() throws IOException
    {
        GetterJSONArduino getterJSONArduino = new GetterJSONArduino(getServletContext().getRealPath("/WEB-INF/res"));
        String actions = getterJSONArduino.getJSONforArduino(NAME_JSON_ACTIONS_FROM_DEVICES);
        String params = getterJSONArduino.getJSONforArduino(ParamsArduino.NAME_FILE_PARAMS);

        return AdderParamsTojsonFileActions.addParams(actions,params);
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
