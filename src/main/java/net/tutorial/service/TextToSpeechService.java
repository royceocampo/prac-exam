package net.tutorial.service;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import net.tutorial.misc.FilenameInputStreamPair;
import net.tutorial.service.credential.TextToSpeechCredential;
import net.tutorial.utility.HTTPUtility;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Created by pongpantola.
 */
public class TextToSpeechService {

    private TextToSpeech t2s;

    public TextToSpeechService(){
        TextToSpeechCredential t2sCred = new TextToSpeechCredential();
        t2s = new TextToSpeech();
        t2s.setUsernameAndPassword(t2sCred.username, t2sCred.password);
    }

    public InputStream convert(String text){
        String format = "audio/wav";

        InputStream speech = t2s.synthesize(text, format);

        return speech;
    }


    public void downloadFile(String text, String fName, HttpServletResponse response) {
        InputStream inStream = convert(text);
        FilenameInputStreamPair fiPair = new FilenameInputStreamPair(fName, inStream, "audio/wav");
        HTTPUtility.download(fiPair, response);
    }
}
