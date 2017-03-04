package net.tutorial.service.credential;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by pongpantola.
 */

public class RedisCredential {

    public String hostname;
    public String port;
    public String name;
    public String password;

    public RedisCredential(){
        try {
            String envApp = System.getenv("VCAP_APPLICATION");
            String envServices = System.getenv("VCAP_SERVICES");

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(envServices);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vcapArray = (JSONArray) jsonObject.get("redis-2.6");
            JSONObject vcap = (JSONObject) vcapArray.get(0);
            JSONObject credentials = (JSONObject) vcap.get("credentials");
            hostname = credentials.get("hostname").toString();
            port = credentials.get("port").toString();
            name = credentials.get("name").toString();
            password = credentials.get("password").toString();
        }catch(ParseException pe){
            System.err.println("RedisCredential() ParseException: " + pe.getMessage());
        }
    }

    public String toString(){
        String str = "";
        str += "hostname: " + hostname + "\n";
        str += "port: " + port + "\n";
        str += "name: " + name + "\n";
        str += "password: " + password + "\n";
        return str;
    }
}
