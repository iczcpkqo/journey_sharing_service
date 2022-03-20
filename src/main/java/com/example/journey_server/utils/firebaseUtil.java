package com.example.journey_server.utils;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Blob;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

@Component
public class firebaseUtil {

    private static final String FIREBASE_SERVICE_ACCOUNT = "src/main/resources/survey-service-account.json";

    private static String storageBucket = "surveys-e1f19.appspot.com";

    private static Bucket bucket;

    private static Storage service;

    private static Firestore db;

    static {

        // Use a service account
        InputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream(FIREBASE_SERVICE_ACCOUNT);
        } catch (FileNotFoundException e) {
            System.out.println("failed to read file service-account.json");
        }
        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (IOException e) {
            System.out.println("Failed to establish firebase connection");
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setStorageBucket(storageBucket)
                .build();
        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();

        StorageOptions build = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build();

        bucket = StorageClient.getInstance().bucket();
        service = build.getService();

    }


    public String addDataToBucket(String bucketname, Map<String, Object> data) {
        ApiFuture<DocumentReference> addedDocRef;
        String id = "";
        try {
            addedDocRef = db.collection(bucketname).add(data);
            id = addedDocRef.get().getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return id;
    }


}
