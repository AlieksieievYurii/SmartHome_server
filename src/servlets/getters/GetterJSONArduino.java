package servlets.getters;

import reader_file.ReadWriteFile;
import servlets.Actions;

import java.io.IOException;

public class GetterJSONArduino
{


    private String realPath;

    public GetterJSONArduino(String realPath) {
        this.realPath = realPath;
    }

    public String getJSONforArduino() throws IOException {
        ReadWriteFile r = new ReadWriteFile(realPath, Actions.NAME_JSON_ACTIONS);
        return r.readFile();
    }
}
