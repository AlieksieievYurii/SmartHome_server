package servlets;

import hash.HashCode;
import reader_file.GetterTextFromFile;
import responce.PrinterResponseForDevices;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

@WebServlet(name = "ActionsHash")
public class ActionsHash extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Sending HASH code");
        try {
            response.getWriter().print(HashCode.getHashOfJsonFileActionArduino(getServletContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
