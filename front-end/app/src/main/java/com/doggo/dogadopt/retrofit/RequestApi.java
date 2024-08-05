package com.doggo.dogadopt.retrofit;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.model.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RequestApi {

    @GET("/api/requests")
    Call <List<Request>> findRequests();

    @GET("/api/show-request/{id}")
    Call <Request> getRequest(@Path("id") Long id);

    @POST("/api/create-request")
    Call <Request> createRequest(@Body Request request);

    @PUT("/api/update-request/{id}")
    Call <Request> updateRequest (@Path("id") Long id, @Body Request request);

    @DELETE("/api/delete-request/{id}")
    Call<Request> deleteRequest(@Path("id") int id);

}
