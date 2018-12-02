package responce;

import hash.HashCode;
import params.ParamsArduino;
import servlets.ActionsFromDevices;
import servlets.getters.AdderParamsToJsonFileActions;
import readerfile.GetterTextFromFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class PrinterResponseForDevices
{

    public static void printJSON(HttpServletResponse response, ServletContext context) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print(getFullJsonForResponse(context));
        printWriter.close();
    }

    private static String getFullJsonForResponse(ServletContext context) throws IOException
    {
        GetterTextFromFile getterTextFromFile = new GetterTextFromFile(context.getRealPath("/WEB-INF/res"));
        String actions = getJsonActionsFromDevices(getterTextFromFile);
        String params = getJsonParamsFromArduino(getterTextFromFile);
        String hashCode = HashCode.getHashOfJsonFileActionArduino(context);

        return AdderParamsToJsonFileActions.addParams(actions,params,hashCode);
    }

    public static String getJsonActionsFromDevices(GetterTextFromFile getterTextFromFile) throws IOException {
        return getterTextFromFile.getTextFromFile(ActionsFromDevices.NAME_JSON_ACTIONS_FROM_DEVICES);
    }

    private static String getJsonParamsFromArduino(GetterTextFromFile getterTextFromFile) throws IOException {
        return getterTextFromFile.getTextFromFile(ParamsArduino.NAME_FILE_PARAMS);
    }
}
