package net.tutorial.service.result;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Created by pongpantola.
 */
public class LanguageTranslationResult {
    public JSONObject jsonTranslationResult;
    public LanguageTranslationResult(String translationResult){
        try {
            JSONParser parser = new JSONParser();
            jsonTranslationResult = (JSONObject) parser.parse(translationResult);
        }catch(Exception e){
            System.err.println("LanguageTranslationResult.LanguageTranslationResult() Exception: " + e.getMessage());
        }
    }

    //{"word_count":3,"translations":[{"translation":"Today is Saturday."}],"character_count":14}

    public JSONArray getTranslations(){
        return (JSONArray) jsonTranslationResult.get("translations");
    }

    public String getTranslation(int i){
        JSONArray jsonTranslations = getTranslations();

        String strTranslation = null;

        if (i < jsonTranslations.size()) {
            JSONObject jsonTranslation = null;
            jsonTranslation = (JSONObject) jsonTranslations.get(i);
            strTranslation = jsonTranslation.get("translation").toString();
        }

        return strTranslation;
    }


    public String toString(){
        return jsonTranslationResult.toString();
    }


}
