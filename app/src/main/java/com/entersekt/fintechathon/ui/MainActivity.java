package com.entersekt.fintechathon.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.entersekt.fintechathon.api.ApiCalls;
import com.entersekt.fintechathon.http.R;
import com.entersekt.fintechathon.models.DummyObject;
import com.entersekt.fintechathon.models.Greeting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Call<Greeting> getGreetingCall;
    private Call<DummyObject> getDummyObjectCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupGetGreeting();
        setupGetDummyObject();
    }

    @Override
    protected void onStop() {
        stopAllOngoingCalls();
        super.onStop();
    }

    private void stopAllOngoingCalls() {
        if (getGreetingCall != null) {
            getGreetingCall.cancel();
        }
        if (getDummyObjectCall != null) {
            getDummyObjectCall.cancel();
        }
    }

    private void setupGetDummyObject() {
        TextView dummyObjectText = findViewById(R.id.get_dummy_object_text);
        OnGetDummyObjectClickListener clickListener = new OnGetDummyObjectClickListener(dummyObjectText);
        findViewById(R.id.get_dummy_object).setOnClickListener(clickListener);
    }

    private class OnGetDummyObjectClickListener implements View.OnClickListener {

        private final TextView textView;

        private OnGetDummyObjectClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View view) {
            getDummyObjectCall = ApiCalls.getDummyObjectCall();
            getDummyObjectCall.enqueue(new Callback<DummyObject>() {
                @Override
                public void onResponse(@NonNull Call<DummyObject> call, @NonNull Response<DummyObject> response) {
                    final DummyObject dummyObject = response.body();
                    if (response.isSuccessful() && dummyObject != null) {
                        textView.setText(dummyObject.toString());
                    } else {
                        String errorMessage = "Error Code: " + response.code() + ", Error Body: " + response.errorBody();
                        onError(errorMessage);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DummyObject> call, @NonNull Throwable t) {
                    if (!call.isCanceled()) {
                        String errorMessage = "Error: " + t.getMessage();
                        onError(errorMessage);
                    } else {
                        Log.e(TAG, "DummyObject call cancelled");
                    }
                }

                private void onError(String errorMessage) {
                    Log.e(TAG, errorMessage);
                    textView.setText(errorMessage);
                }
            });
        }
    }

    private void setupGetGreeting() {
        String name = "Android";
        TextView greetingText = findViewById(R.id.get_greeting_text);
        OnGetGreetingClickListener clickListener = new OnGetGreetingClickListener(name, greetingText);
        findViewById(R.id.get_greeting).setOnClickListener(clickListener);
    }

    private class OnGetGreetingClickListener implements View.OnClickListener {

        private final String name;
        private final TextView textView;

        OnGetGreetingClickListener(@Nullable String name, TextView textToUpdate) {
            this.name = name;
            this.textView = textToUpdate;
        }

        @Override
        public void onClick(View view) {
            getGreetingCall = ApiCalls.getGreetingCall(name);
            getGreetingCall.enqueue(new Callback<Greeting>() {
                @Override
                public void onResponse(@NonNull Call<Greeting> call, @NonNull Response<Greeting> response) {
                    final Greeting greeting = response.body();
                    if (response.isSuccessful() && greeting != null) {
                        textView.setText(greeting.toString());
                    } else {
                        String errorMessage = "Error Code: " + response.code() + ", Error Body: " + response.errorBody();
                        onError(errorMessage);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Greeting> call, @NonNull Throwable t) {
                    if (!call.isCanceled()) {
                        String errorMessage = "Error: " + t.getMessage();
                        onError(errorMessage);
                    } else {
                        Log.e(TAG, "Greeting call cancelled");
                    }
                }

                private void onError(String errorMessage) {
                    Log.e(TAG, errorMessage);
                    textView.setText(errorMessage);
                }
            });
        }
    }

}
