package com.nabwera.retrofit_send_objects.api.service;

import com.nabwera.retrofit_send_objects.api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nabwera on 27/07/2017.
 */

public interface UserClient {

    // Use @Body to declare User object as request body
    @POST("user")
    Call<User> createAccount(@Body User user);
}
