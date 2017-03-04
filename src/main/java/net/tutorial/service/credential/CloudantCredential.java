package net.tutorial.service.credential;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by pongpantola.
 */

public class CloudantCredential {

    public String host;
    public String port;
    public String username;
    public String password;

    public CloudantCredential(){
        try {
            String envApp = System.getenv("VCAP_APPLICATION");
            String envServices = System.getenv("VCAP_SERVICES");

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(envServices);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vcapArray = (JSONArray) jsonObject.get("cloudantNoSQLDB");
            JSONObject vcap = (JSONObject) vcapArray.get(0);
            JSONObject credentials = (JSONObject) vcap.get("credentials");
            host = credentials.get("host").toString();
            port = credentials.get("port").toString();
            username = credentials.get("username").toString();
            password = credentials.get("password").toString();
        }catch(ParseException pe){
            System.err.println("CloudantCredential() ParseException: " + pe.getMessage());
        }
    }

    public String toString(){
        String str = "";
        str += "host: " + host + "\n";
        str += "port: " + port + "\n";
        str += "username: " + username + "\n";
        str += "password: " + password + "\n";
        return str;
    }
}
