package com.example.api_testing;

import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

public class GETRequestEx {
    public static void GETRequest() throws IOException {
        String urlName = "https://reqres.in/api/products/3?id=3";
        URL urlForGetReq = new URL(urlName);
        String read = null;
        HttpURLConnection connection = (HttpURLConnection) urlForGetReq.openConnection();
        connection.setRequestMethod("GET");
//        connection.setRequestProperty("userId", "123"); // set userId its a sample here
        int codeResponse = connection.getResponseCode();
// checking whether the connection has been established or not
        if (codeResponse == HttpURLConnection.HTTP_OK) {
// reading the response from the server
            InputStreamReader isrObj = new InputStreamReader(connection.getInputStream());
            BufferedReader bf = new BufferedReader(isrObj);
            File file = new File("output\\GET_employees.xml");
            FileWriter fr = new FileWriter(file, true);
// to store the response from the servers
            StringBuffer responseStr = new StringBuffer();
            while ((read = bf.readLine()) != null) {
                responseStr.append(read);
            }
            JSONObject json = new JSONObject(String.valueOf(responseStr));
            String xml = XML.toString(json);
            fr.write("<employee>\n"+xml+"\n</employee>");
            fr.close();
// closing the BufferedReader
            bf.close();
// disconnecting the connection
            connection.disconnect();
// print the response
            System.out.println("JSON String Result is: \n" + responseStr.toString());
        } else {
            System.out.println("GET Request did not work");
        }
    }

    // main method
    public static void main(String argvs[]) throws IOException {
      GETRequest();
    }
}
