package reader_file;

import json_creater.JsonCreaterForArduino;

import java.io.*;

public class ReadWriteFile
{
    private String fullPath;
    private File file;

    public ReadWriteFile(String pathFile, String nameFile) {
        this.fullPath = pathFile.concat(nameFile);

        openFile();
    }
    public ReadWriteFile(String pathFile) {
        this.fullPath = pathFile;
        openFile();
    }


    private void openFile()
    {
        file = new File(fullPath);
    }

    public String readFile() throws IOException {

        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("The file" + fullPath + " was not found!");
            createNewJsonFile();
            return null;
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder textFromFile = new StringBuilder();
        String str;

        while ((str = bufferedReader.readLine()) != null)
            textFromFile.append(str);

        return textFromFile.toString();
    }

    private void createNewJsonFile()
    {
        try {
            file.createNewFile();
            writeFile(JsonCreaterForArduino.getDefaultStringJSONforArduino());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean writeFile(String text)
    {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error of writing file of json -> " + fullPath);
            return false;
        }
        return true;
    }

}
