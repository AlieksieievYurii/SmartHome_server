package converter;

import reader_file.ReadWriteFile;

import java.io.IOException;
//TODO I need implementation for more function
public class Converter
{
    private ReadWriteFile readBFile;
    private ReadWriteFile writeAFile;

    private String fullPathAfile;
    private String fullPathBfile;

    public Converter(String fullPathAfile,String fullPathBfile)
    {
        this.fullPathAfile = fullPathAfile;
        this.fullPathBfile = fullPathBfile;

        readBFile = new ReadWriteFile(fullPathBfile);
        writeAFile = new ReadWriteFile(fullPathAfile);
    }

    public void convert()
    {
        String data = readBfile();
        writeAfile(data);
    }

    private String readBfile()
    {
        try {
            return readBFile.readFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void writeAfile(String text)
    {
        writeAFile.writeFile(text);
    }
}
