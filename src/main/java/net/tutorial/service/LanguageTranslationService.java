package net.tutorial.service;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;
import net.tutorial.service.credential.LanguageTranslationCredential;
import net.tutorial.service.result.LanguageTranslationResult;

/**
 * Created by pongpantola.
 */
public class LanguageTranslationService {

    private LanguageTranslation lt;

    public LanguageTranslationService(){
        LanguageTranslationCredential ltCred = new LanguageTranslationCredential();
        lt = new LanguageTranslation();
        lt.setUsernameAndPassword(ltCred.username, ltCred.password);
    }

    public LanguageTranslationResult translate(String text, String frmLanguage, String toLanguage){
        TranslationResult tr = lt.translate(text, frmLanguage, toLanguage);

        return new LanguageTranslationResult(tr.toString());
    }



}
