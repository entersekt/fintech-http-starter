package com.entersekt.fintechathon.api;

import android.support.annotation.Nullable;

import com.entersekt.fintechathon.models.DummyObject;
import com.entersekt.fintechathon.models.Greeting;

import retrofit2.Call;

public class ApiCalls {

    private ApiCalls() {
        // Private constructor to prevent accidental instantiation.
    }

    public static Call<Greeting> getGreetingCall(@Nullable String name) {
        return ApiServices.getGreetingService().getGreeting(name);
    }

    public static Call<DummyObject> getDummyObjectCall() {
        return ApiServices.getDummyObjectService().getDummyObject();
    }
}
