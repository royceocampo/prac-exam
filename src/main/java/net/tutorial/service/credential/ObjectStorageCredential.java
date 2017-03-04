package net.tutorial.service.credential;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by pongpantola.
 */

public class ObjectStorageCredential{
    public String auth_url;
    public String project;
    public String projectId;
    public String region;
    public String userId;
    public String username;
    public String password;
    public String domainId;
    public String domainName;

    public ObjectStorageCredential(){
        try {
            String envApp = System.getenv("VCAP_APPLICATION");
            String envServices = System.getenv("VCAP_SERVICES");

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(envServices);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vcapArray = (JSONArray) jsonObject.get("Object-Storage");
            JSONObject vcap = (JSONObject) vcapArray.get(0);
            JSONObject credentials = (JSONObject) vcap.get("credentials");
            auth_url = credentials.get("auth_url").toString() + "/v3";
            project = credentials.get("project").toString();
            projectId = credentials.get("projectId").toString();
            region = credentials.get("region").toString();
            userId = credentials.get("userId").toString();
            username = credentials.get("username").toString();
            password = credentials.get("password").toString();
            domainId = credentials.get("domainId").toString();
            domainName = credentials.get("domainName").toString();
        }catch(ParseException pe){
            System.err.println("ObjecStorageCredential() ParseException: " + pe.getMessage());
        }
    }

    public String toString(){
        String str = "";
        str += "auth_url: " + auth_url + "\n";
        str += "project: " + project + "\n";
        str += "projectId: " + projectId + "\n";
        str += "region: " + region + "\n";
        str += "userId: " + userId + "\n";
        str += "username: " + username + "\n";
        str += "password: " + password + "\n";
        str += "domainId: " + domainId + "\n";
        str += "domainName: " + domainName + "\n";
        return str;
    }
}
