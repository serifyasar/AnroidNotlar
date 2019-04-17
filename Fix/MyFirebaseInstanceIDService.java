package com.serifyasargmail.fixnfix;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG="MyFirebaseIIDService";
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        registerToken(token);
        Log.d("TOKEN Verildi", token);
    }

    private void registerToken(String token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://fixnfix.co.uk/api/token?token="+token+"&crypt=sy1289*")
                .build();


        try {
            client.newCall(request).execute();

        } catch (IOException e) {
            Log.d(TAG,"hata: "+e.getMessage());
        }

    }
}