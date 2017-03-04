package net.tutorial.service.credential;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class AlchemyCredential{
    public String apikey;

    public AlchemyCredential(){
        try {
            String envApp = System.getenv("VCAP_APPLICATION");
            String envServices = System.getenv("VCAP_SERVICES");

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(envServices);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vcapArray = (JSONArray) jsonObject.get("alchemy_api");
            JSONObject vcap = (JSONObject) vcapArray.get(0);
            JSONObject credentials = (JSONObject) vcap.get("credentials");
            apikey = credentials.get("apikey").toString();
        }catch(ParseException pe){
            System.err.println("TextToSpeechCredential() ParseException: " + pe.getMessage());
        }
    }

    public String toString(){
        String str = "";
        str += "username: " + apikey + "\n";
        return str;
    }
}
