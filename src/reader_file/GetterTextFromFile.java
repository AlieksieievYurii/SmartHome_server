package reader_file;

import java.io.IOException;

public class GetterTextFromFile
{
    private String realPath;

    public GetterTextFromFile(String realPath) {
        this.realPath = realPath;
    }

    public String getTextFromFile(String nameFile) throws IOException {
        ReadWriteFile r = new ReadWriteFile(realPath, nameFile);
        return r.readFile();
    }

    public boolean writeTextToFile(String nameFile,String data)
    {
        ReadWriteFile r = new ReadWriteFile(realPath, nameFile);
        return r.writeFile(data);
    }
}
