package net.tutorial.service;

import net.tutorial.misc.FilenameInputStreamPair;
import net.tutorial.service.credential.ObjectStorageCredential;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.openstack.OSFactory;

import java.util.List;

/**
 * Created by pongpantola.
 */
public class ObjectStorageService {
    private OSClient os = null;
    private SwiftAccount account = null;

    public ObjectStorageService() {
        ObjectStorageCredential osCred = new ObjectStorageCredential();


        Identifier domainIdent = Identifier.byName(osCred.domainName);
        Identifier projectIdent = Identifier.byName(osCred.project);

        os = OSFactory.builderV3()
                .endpoint(osCred.auth_url)
                .credentials(osCred.userId, osCred.password)
                .scopeToProject(projectIdent, domainIdent)
                .authenticate();

        account = os.objectStorage().account().get();
    }

    public boolean createContainer(String cName) {
        return os.objectStorage().containers().create(cName).isSuccess();
    }


    public boolean deleteContainer(String cName) {
        return os.objectStorage().containers().delete(cName).isSuccess();
    }


    public String putFile(String cName, String fName, Payload payload) {
        return os.objectStorage().objects().put(cName, fName, payload);
    }

    public boolean deleteFile(String cName, String fName) {
        return os.objectStorage().objects().delete(cName, fName).isSuccess();
    }


    private SwiftObject getSwiftObject(String cName, String fName) {
        return os.objectStorage().objects().get(cName, fName);
    }




    /*
    public SwiftAccount getAccount() {
        account = os.objectStorage().account().get();
        return account;
    }*/

    public List<? extends SwiftObject> listAllObjects(String cName) {
        return os.objectStorage().objects().list(cName);
    }


    public String putFile(String cName, FilenameInputStreamPair fiPair) {
        Payload payload = Payloads.create(fiPair.getInputStream());
        return putFile(cName, fiPair.getFilename(), payload);
    }

    public FilenameInputStreamPair getFile(String cName, String fName) {
        SwiftObject swiftObj = getSwiftObject(cName, fName);
        FilenameInputStreamPair fiPair = new FilenameInputStreamPair(swiftObj.getName(), swiftObj.download().getInputStream(), swiftObj.getMimeType());
        return fiPair;
    }

    /*
    public String uploadFile(String cName, HttpServletRequest request) {
        FilenameInputStreamPair fiPair = HTTPUtility.upload(request);
        Payload payload = Payloads.create(fiPair.getInputStream());
        return putFile(cName, fiPair.getFilename(), payload);
    }


    public String uploadFile(String cName, String fName, HttpServletRequest request) {
        FilenameInputStreamPair fiPair = HTTPUtility.upload(request);
        Payload payload = Payloads.create(fiPair.getInputStream());
        return putFile(cName, fName, payload);
    }


    public void downloadFile(String cName, String fName, HttpServletResponse response) {
        SwiftObject swiftObj = getFile(cName, fName);
        FilenameInputStreamPair fiPair = new FilenameInputStreamPair(swiftObj.getName(), swiftObj.download().getInputStream(), swiftObj.getMimeType());
        HTTPUtility.download(fiPair, response);
    }
    */


}
