package com.example.javasdksample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mparticle.ApiClient;
import com.mparticle.client.EventsApi;
import com.mparticle.model.Batch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    EventsApi api = new ApiClient("us1-75b0533942c68f4bb1f0d5ee082f360a", "fAHBYGiXI_o948OhpRkyMkUNcRaNcfjGwYiihk-0LC6XiCMSclkJPjs2HVFpJWzd")
                            .createService(EventsApi.class);
                    Batch batch = new Batch();
                    batch.environment(Batch.Environment.DEVELOPMENT);
                    Map<String, Object> userAttributes = new HashMap<>();
                    userAttributes.put("Java SDK", "Engaged");
                    batch.userAttributes(userAttributes);
                    Call<Void> singleResult = api.uploadEvents(batch);
                    try {
                        Response<Void> singleResponse = singleResult.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}