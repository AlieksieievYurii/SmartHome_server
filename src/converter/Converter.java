package converter;

import readerfile.ReadWriteFile;

import java.io.IOException;
//TODO I need implementation for more function
public class Converter
{
    private ReadWriteFile readBFile;
    private ReadWriteFile writeFile;

    private String fullPathAfile;
    private String fullPathBfile;

    public Converter(String fullPathAfile,String fullPathBfile)
    {
        this.fullPathAfile = fullPathAfile;
        this.fullPathBfile = fullPathBfile;

        readBFile = new ReadWriteFile(fullPathBfile);
        writeFile = new ReadWriteFile(fullPathAfile);
    }

    public void convert()
    {
        String data = readBfile();

        //Here should be some interesting implementation, but in other time I will do it

        writeFile(data);
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

    private void writeFile(String text)
    {
        writeFile.writeFile(text);
    }
}
