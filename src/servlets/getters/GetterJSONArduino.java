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

    public String getJSONforArduino(String nameFile) throws IOException {
        ReadWriteFile r = new ReadWriteFile(realPath, nameFile);
        return r.readFile();
    }
}
