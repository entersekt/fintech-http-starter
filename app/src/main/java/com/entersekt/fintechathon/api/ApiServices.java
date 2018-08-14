package com.entersekt.fintechathon.api;

import android.support.annotation.Nullable;

import com.entersekt.fintechathon.models.DummyObject;
import com.entersekt.fintechathon.models.Greeting;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

class ApiServices {

    private static final String EXAMPLE_OBJECT_ID = "55973508b0e9e4a71a02f05f";

    private static GreetingService greetingService;
    private static DummyObjectService dummyObjectService;

    private ApiServices() {
        // Private constructor to prevent accidental instantiation.
    }

    static synchronized GreetingService getGreetingService() {
        if (greetingService == null) {
            greetingService = ApiClients.getSpringApiClient().create(GreetingService.class);
        }
        return greetingService;
    }

    static synchronized DummyObjectService getDummyObjectService() {
        if (dummyObjectService == null) {
            dummyObjectService = ApiClients.getMockyApiClient().create(DummyObjectService.class);
        }
        return dummyObjectService;
    }

    public interface GreetingService {
        @GET("/greeting")
        Call<Greeting> getGreeting(@Nullable @Query("name") String name);
    }

    public interface DummyObjectService {
        @GET("/v2/" + EXAMPLE_OBJECT_ID)
        Call<DummyObject> getDummyObject();
    }
}
