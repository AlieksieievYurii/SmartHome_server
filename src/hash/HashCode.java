package hash;

import reader_file.GetterTextFromFile;
import responce.PrinterResponseForDevices;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.security.MessageDigest;

public class HashCode
{
    public static String getHashOfJsonFileActionArduino(ServletContext context) throws IOException {

        GetterTextFromFile getterTextFromFile = new GetterTextFromFile(context.getRealPath("/WEB-INF/res/"));
        return getterTextFromFile.getTextFromFile("HashCodeOfActionsDevice.txt");
    }

    public static void writeHashCodeOfJsonActionsDevice(ServletContext context) throws Exception
    {
        GetterTextFromFile getterTextFromFile = new GetterTextFromFile(context.getRealPath("/WEB-INF/res/"));
        String text = PrinterResponseForDevices.getJsonActionsFromDevices(getterTextFromFile);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(text.getBytes(),0,text.length());

        byte[] hash = messageDigest.digest();

        String hashCode = new BASE64Encoder().encode(hash);

        if(!getterTextFromFile.writeTextToFile("HashCodeOfActionsDevice.txt",hashCode))
            throw new Exception("Error of writing HashCode of actions device to file");
    }
}
