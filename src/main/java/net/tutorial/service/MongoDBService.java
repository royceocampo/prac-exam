package net.tutorial.service;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.util.*;
import net.tutorial.service.credential.MongoDBCredential;
import org.bson.*;

import java.util.ArrayList;
import java.util.List;

//import java.io.*;

/**
 * Created by pongpantola.
 */
public class MongoDBService {
    MongoClient mongo;
    MongoDatabase mongoDB;

    public MongoDBService(){
        MongoDBCredential mongoCred = new MongoDBCredential();
        mongo = new MongoClient(new MongoClientURI(mongoCred.url));
        mongoDB = mongo.getDatabase(mongoCred.db);
    }

    public void putJSON(String collectionName, String strJSON) {
        Document doc = Document.parse(strJSON);
        mongoDB.getCollection(collectionName).insertOne(doc);
    }

    public List<Document> get(String collectionName) {
        MongoCollection<Document> mongoCollection = mongoDB.getCollection(collectionName);
         List<Document> mongoList = (List<Document>) mongoCollection.find().into(
                new ArrayList<Document>());

        return mongoList;
    }

    public Document getLast(String collectionName) {
        MongoCollection<Document> mongoCollection = mongoDB.getCollection(collectionName);
        List<Document> mongoList = (List<Document>) mongoCollection.find().into(
                new ArrayList<Document>());

        Document document = null;

        if (mongoList.size()>0)
            document = mongoList.get(mongoList.size() - 1);

        return document;
    }
}
