package com.example.api_testing;

import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class POSTRequestEx {
    public static void POSTReq() throws IOException {
// The message that is going to be sent to the server
// using the POST request
        final String messageContent = "{\n" + "\"userId\": 199, \r\n" +
                " \"id\": 101, \r\n" +
                " \"title\": \"About JavaTpoint\", \r\n" +
                " \"body\": \"JavaTpoint is a good site to learn Java. One must visit the site.\"" + "\n}";
// Printing the message
        System.out.println(messageContent);
// URL of the API or Server
        String url = "https://jsonplaceholder.typicode.com/posts";
        URL urlObj = new URL(url);
        HttpURLConnection postCon = (HttpURLConnection) urlObj.openConnection();
        postCon.setRequestMethod("POST");
//        postCon.setRequestProperty("userId", "abcdef");
// Setting the message content type as JSON
        postCon.setRequestProperty("Content-Type", "application/json");
        postCon.setDoOutput(true);
// for writing the message content to the server
        OutputStream osObj = postCon.getOutputStream();
        osObj.write(messageContent.getBytes());
// closing the output stream
        osObj.flush();
        osObj.close();
        int respCode = postCon.getResponseCode();
        System.out.println("Response from the server is: \n");
        System.out.println("The POST Request Response Code :  " + respCode);
        System.out.println("The POST Request Response Message : " + postCon.getResponseMessage());
        if (respCode == HttpURLConnection.HTTP_CREATED) {
// reaching here means the connection has been established
// By default, InputStream is attached to a keyboard.
// Therefore, we have to direct the InputStream explicitly
// towards the response of the server
            InputStreamReader irObj = new InputStreamReader(postCon.getInputStream());
            BufferedReader br = new BufferedReader(irObj);
            String input = null;
            StringBuffer sb = new StringBuffer();
            File file = new File("output\\POST_employees.xml");
            FileWriter fr = new FileWriter(file, true);
            while ((input = br.readLine()) != null) {
                sb.append(input);
            }
            JSONObject json = new JSONObject(String.valueOf(sb));
            String xml = XML.toString(json);
            fr.write("<employee>\n"+xml+"\n</employee>");
            fr.close();
            br.close();
            postCon.disconnect();
// printing the response
            System.out.println(sb.toString());
        } else {
// connection was not successful
            System.out.println("POST Request did not work.");
        }
    }

    // main method
    public static void main(String argvs[]) throws IOException {
        POSTReq();
    }
}
