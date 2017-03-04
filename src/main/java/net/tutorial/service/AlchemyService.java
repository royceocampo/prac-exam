package net.tutorial.service;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyVision;
import net.tutorial.service.credential.AlchemyCredential;
import net.tutorial.service.result.AlchemyResult;

import java.net.URL;

/**
 * Created by pongpantola.
 */
public class AlchemyService {
    private AlchemyVision alServ;

    public AlchemyService(){
        AlchemyCredential alCred = new AlchemyCredential();
        alServ = new AlchemyVision();
        alServ.setApiKey(alCred.apikey);
    }

    public AlchemyResult recognizeFaces(String strUrl){
        URL url = null;
        try {
            url = new URL(strUrl);
            return new AlchemyResult(alServ.recognizeFaces(url, false).toString());
        }catch(Exception e){
            System.err.println("AlchemyService.recognizeFaces(String strUrl) Exception: " + e.getMessage());
        }
        return null;
    }
}
