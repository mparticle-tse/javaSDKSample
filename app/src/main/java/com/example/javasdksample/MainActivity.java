package com.example.javasdksample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mparticle.ApiClient;
import com.mparticle.client.EventsApi;
import com.mparticle.model.Batch;
import com.mparticle.model.CommerceEvent;
import com.mparticle.model.CommerceEventData;
import com.mparticle.model.CustomEvent;
import com.mparticle.model.CustomEventData;
import com.mparticle.model.Product;
import com.mparticle.model.ProductAction;
import com.mparticle.model.UserIdentities;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    EventsApi api = new ApiClient("API KEY", "SECRET")
                            .createService(EventsApi.class);
                    Batch batch = new Batch();
                    batch.environment(Batch.Environment.DEVELOPMENT);
                    batch.userIdentities(new UserIdentities()
                            .customerId("50")
                            .email("milestest@foo.com")
                    );
                    Map<String, Object> userAttributes = new HashMap<>();
                    userAttributes.put("foo", "us");
                    batch.userAttributes(userAttributes);



                    //Commerce Action
                    Product product = new Product()
                            .totalProductAmount(new BigDecimal("123.12"))
                            .id("product-id")
                            .name("product-name");
                    ProductAction action = new ProductAction()
                            .action(ProductAction.Action.PURCHASE)
                            .totalAmount(new BigDecimal("123.12"))
                            .transactionId("foo-transaction-id")
                            .products(Arrays.asList(product));
                    CommerceEvent event = new CommerceEvent().data(
                            new CommerceEventData().productAction(action)
                    );
                    batch.addEventsItem(event);

                    //Custom Event
                    Map customAttributes = new HashMap<>();
                    customAttributes.put("foo", "bar");
                    CustomEvent event2 = new CustomEvent().data(
                            new CustomEventData()
                                    .eventName("My Custom Event Name")
                                    .customEventType(CustomEventData.CustomEventType.LOCATION)
                    );
                    event2.getData().customAttributes(customAttributes);
                    batch.addEventsItem(event2);



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
        thread2.start();
    }
}
