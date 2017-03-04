package net.tutorial.service;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import net.tutorial.misc.FilenameInputStreamPair;
import net.tutorial.service.credential.SpeechToTextCredential;
import net.tutorial.utility.HTTPUtility;
import net.tutorial.utility.RandomUtility;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by pongpantola.
 */
public class SpeechToTextService {

    private SpeechToText s2t;

    public SpeechToTextService(){
        SpeechToTextCredential s2tCred = new SpeechToTextCredential();
        s2t = new SpeechToText();
        s2t.setUsernameAndPassword(s2tCred.username, s2tCred.password);
    }

    public String convert(InputStream inStream){

        String text = null;

        try {
            File tempAudioFile = File.createTempFile(RandomUtility.getRandomTimestamp(), "wav");
            FileOutputStream outStream = new FileOutputStream(tempAudioFile);
            IOUtils.copy(inStream, outStream);


            SpeechResults output = s2t.recognize(tempAudioFile, "audio/wav");
            List<Transcript> transcriptList = output.getResults();
            if (transcriptList.size() > 0) {
                List<SpeechAlternative> speechList = transcriptList.get(0).getAlternatives();
                if (speechList.size() > 0){
                    text = speechList.get(0).getTranscript();
                }
            }
        }catch(Exception e){
            System.err.println("SpeechToTextService.convert(InputStream inStream) Exception: " + e.getMessage());
        }

        return text;
    }

    public String uploadFile(HttpServletRequest request) {
        FilenameInputStreamPair fiPair = HTTPUtility.upload(request);
        InputStream inStream = fiPair.getInputStream();
        return convert(inStream);
    }
}
