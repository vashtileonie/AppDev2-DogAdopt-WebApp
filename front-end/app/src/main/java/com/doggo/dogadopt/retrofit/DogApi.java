package com.doggo.dogadopt.retrofit;

import com.doggo.dogadopt.model.Dog;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DogApi {

    @GET("/api/dogs")
    Call<List<Dog>> getDogs();

    @GET("/api/show-dog/{id}")
    Call<Dog> getDog(@Path("id") int id);

    @Multipart
    @POST("/api/add-dog")
    Call<Dog> addDogSubmit(
                     @Part MultipartBody.Part photo,
                     @Part("name") RequestBody name,
                     @Part("breed") RequestBody breed,
                     @Part("age") RequestBody age,
                     @Part("doa") RequestBody doa,
                     @Part("personality") RequestBody personality,
                     @Part("status") RequestBody status,
                     @Part("gender") RequestBody gender);

    @Multipart
    @PUT("/api/update-dog/{id}")
    Call<Dog> updateDog(
                     @Path("id") int id,
                     @Part MultipartBody.Part photo,
                     @Part("name") RequestBody name,
                     @Part("breed") RequestBody breed,
                     @Part("age") RequestBody age,
                     @Part("doa") RequestBody doa,
                     @Part("personality") RequestBody personality,
                     @Part("status") RequestBody status,
                     @Part("gender") RequestBody gender);

    @DELETE("/api/delete-dog/{id}")
    Call<Dog> deleteDog(@Path("id") int id);


}
