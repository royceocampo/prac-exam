package net.tutorial.misc;

import java.io.InputStream;


/**
 * Created by pongpantola.
 */
public class FilenameInputStreamPair {
    private InputStream inStream;
    private String fname;
    private String mimeType;

    public FilenameInputStreamPair(String fname, InputStream inStream){
        this.inStream = inStream;
        this.fname = fname;
    }

    public FilenameInputStreamPair(String fname, InputStream inStream, String mimeType){
        this.fname = fname;
        this.inStream = inStream;

        this.mimeType = mimeType;
    }

    public InputStream getInputStream(){
        return inStream;
    }

    public String getFilename(){
        return fname;
    }

    public void setFilename(String fname){
        this.fname = fname;
    }

    public String getMimeType(){
        return mimeType;
    }
}