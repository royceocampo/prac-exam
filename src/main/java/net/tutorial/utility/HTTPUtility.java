package net.tutorial.utility;

import net.tutorial.misc.FilenameInputStreamPair;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

//import org.openstack4j.api.OSClient;
//import org.openstack4j.model.storage.object.SwiftAccount;
//import org.openstack4j.model.common.Identifier;
//import org.openstack4j.openstack.OSFactory;
//import javax.servlet.http.HttpServletResponse;


/**
 * Created by pongpantola.
 */
public class HTTPUtility {

    public static FilenameInputStreamPair upload(HttpServletRequest request){
        String result = null;
        //response.setContentType("text/html;charset=UTF-8");
        //try (PrintWriter out = response.getWriter()) {


        FilenameInputStreamPair fiPair = null;
        String fname = null;
        InputStream inStream = null;
        //Payload payload = null;

        if (ServletFileUpload.isMultipartContent(request)) {

            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {

                List<FileItem> fields = upload.parseRequest(request);
                Iterator<FileItem> it = fields.iterator();
                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    boolean isFormField = fileItem.isFormField();
                    if (isFormField) {
                    } else {
                        fname = fileItem.getName();
                        inStream = fileItem.getInputStream();
                        //payload = Payloads.create(fileItem.getInputStream());
                    }
                }

                if (!fname.isEmpty() && !(upload == null)) {
                    fiPair = new FilenameInputStreamPair(fname, inStream);

                }
            } catch (Exception e) {
                System.out.println("HTTPUtility.upload(HttpServletRequest request) Exception: " + e.getMessage());
            }
        }

        return fiPair;
        ///response.sendRedirect("home.jsp");

    }

    /*
    public static void downloadFilenamePayloadPair(SwiftObject swiftObj, HttpServletRequest request, HttpServletResponse response){
    //protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      //      throws ServletException, IOException {

        InputStream inStr = null;
        OutputStream outStr = null;

        //response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter()
        try {


                    response.setContentType(swiftObj.getMimeType());
                    response.setHeader("Content-Disposition", "attachment; filename=\""+swiftObj.getName()+"\"");
                    inStr = swiftObj.download().getInputStream();
                    outStr = response.getOutputStream();
                    IOUtils.copy(inStr, outStr);
                    inStr.close();
                    outStr.flush();
                    outStr.close();
        } catch(Exception e) {
        }
    }
*/


    public static void download(FilenameInputStreamPair fiPair, HttpServletResponse response){
        try {
            if (fiPair.getMimeType() != null)
                response.setContentType(fiPair.getMimeType());


            response.setHeader("Content-Disposition", "attachment; filename=\""+fiPair.getFilename()+"\"");

            OutputStream outStream = response.getOutputStream();
            InputStream inStream = fiPair.getInputStream();
            IOUtils.copy(fiPair.getInputStream(), outStream);
            inStream.close();
            outStream.flush();
            outStream.close();
        } catch(Exception e) {
            System.err.println("HTTPUtility.download(FilenameInputStreamPair fiPair, HttpServletResponse response) Exception: " +e.getMessage());
        }
    }

}
