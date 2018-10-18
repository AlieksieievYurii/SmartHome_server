package params;

import java.io.*;

public class ParamsArduino
{
    private static String NAME_FILE_PARAMS = "\\ParamsArduino.json";

    private String fullPath;
    private File file;

    public ParamsArduino(String path)
    {
        this.fullPath = path.concat(NAME_FILE_PARAMS);
        file = new File(fullPath);
    }

    public void writeParams(String text)
    {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error of writing file of params -> " + fullPath);
            createFileParams();
        }
    }

    public String readParams() throws IOException {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createFileParams();
            return null;
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder text = new StringBuilder();
        String str;

        while((str = bufferedReader.readLine()) != null)
            text.append(str);

        bufferedReader.close();

        return text.toString();
    }

    private void createFileParams()
    {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
