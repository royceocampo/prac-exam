package net.tutorial.service.result;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by pongpantola.
 */
public class AlchemyResult {
    public JSONObject jsonRecognizeFacesResult;

    public AlchemyResult(String strRecognizeFacesResult){
        try {
            JSONParser parser = new JSONParser();
            jsonRecognizeFacesResult = (JSONObject) parser.parse(strRecognizeFacesResult);
        }catch(Exception e){
            System.err.println("AlchemyResult.AlchemyResult() Exception: " + e.getMessage());
        }
    }

    public JSONArray getImageFaces(){
        System.out.println("jsonRecognizeFacesResult:"+jsonRecognizeFacesResult);
        System.out.println("jsonRecognizeFacesResult.get(\"imageFaces\"):"+jsonRecognizeFacesResult.get("imageFaces"));
        return (JSONArray) jsonRecognizeFacesResult.get("imageFaces");
    }

    public JSONObject getImageFace(int i){
        JSONArray jsonImageFaces = getImageFaces();

        JSONObject jsonImageFace = null;

        if (i < jsonImageFaces.size())
            jsonImageFace = (JSONObject) jsonImageFaces.get(i);

        return jsonImageFace;
    }

    public String getAge(int i){
        String str = "";
        JSONObject jsonAge = (JSONObject) getImageFace(i).get("age");

        if (jsonAge != null)
            str = jsonAge.get("ageRange").toString();

        return str;
    }

    public String getGender(int i){
        String str = "";
        JSONObject jsonAge = (JSONObject) getImageFace(i).get("gender");

        if (jsonAge != null)
            str = jsonAge.get("gender").toString();

        return str;
    }

    public String toString(){
        return jsonRecognizeFacesResult.toString();
    }



}
