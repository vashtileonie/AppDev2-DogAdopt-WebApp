package com.doggo.dogadopt.retrofit;
import com.doggo.dogadopt.model.Account;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AccountApi {

    @GET("/api/accounts")
    Call <List<Account>> showAllAccount();

    @GET("/api/show-account/{id}")
    Call <Account> getAccount(@Path("id") int id);

    @POST("/api/create-account")
    Call<Account> createAccount(@Body Account account);

    @PUT("/api/update-account/{id}")
    Call<Account> updateAccount (@Path("id") int id, @Body Account account);

    @DELETE("/api/delete-account/{id}")
    Call<Account> deleteAccount(@Path("id") int id);


}
