package com.nabwera.retrofit_send_objects.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nabwera.retrofit_send_objects.R;
import com.nabwera.retrofit_send_objects.api.model.User;
import com.nabwera.retrofit_send_objects.api.service.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText name = (EditText) findViewById(R.id.input_name);
        final EditText email = (EditText) findViewById(R.id.input_email);
        final EditText age = (EditText) findViewById(R.id.input_age);
        final EditText topics = (EditText) findViewById(R.id.input_topics);

        Button createAccountButton = (Button) findViewById(R.id.btn_signup);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inform the user the button has been clicked
                Toast.makeText(MainActivity.this, "SignUp Button Clicked", Toast.LENGTH_SHORT).show();

                // User object that we will send to the server
                User user = new User(
                        name.getText().toString(),
                        email.getText().toString(),
                        Integer.parseInt(age.getText().toString()),
                        topics.getText().toString().split(",")
                );

                sendNetworkRequest(user);
            }

        });

    }

    private void sendNetworkRequest(User user){
        // Create Retrofit Instance
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        // get client & call object for the request
        // Calling the service method createAccount will convert the properties of user into JSON representation.
        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.createAccount(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Yeah! USER_ID:" + response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
