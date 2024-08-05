package com.doggo.dogadopt.retrofit;

import android.util.Log;

import com.doggo.dogadopt.model.Account;
import com.doggo.dogadopt.model.Dog;
import com.doggo.dogadopt.model.Request;
import com.doggo.dogadopt.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueryProcessor {

    Account accountData = new Account();
    Dog dogData = new Dog();
    Request requestData = new Request();
    List<Account> accountList = new ArrayList<>();
    List<Dog> dogList = new ArrayList<>();
    List<Request> requestList = new ArrayList<>();
    RetrofitService retrofitService = new RetrofitService();
    AccountApi accountApi = retrofitService.getRetrofit().create(AccountApi.class);
    DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);
    RequestApi requestApi = retrofitService.getRetrofit().create(RequestApi.class);
    CallBack cbs;

    public void DogAdd(byte[] photoBytes, String name, String breed, String age, String doa, String personality, String status, String gender){
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/"),photoBytes);
        RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"),name);
        RequestBody breedBody = RequestBody.create(MediaType.parse("text/plain"),breed);
        RequestBody ageBody = RequestBody.create(MediaType.parse("text/plain"),age);
        RequestBody doaBody = RequestBody.create(MediaType.parse("text/plain"),doa);
        RequestBody personalityBody = RequestBody.create(MediaType.parse("text/plain"),personality);
        RequestBody statusBody = RequestBody.create(MediaType.parse("text/plain"),status);
        RequestBody genderBody = RequestBody.create(MediaType.parse("text/plain"),gender);

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("photo","file",imageBody);
        dogApi.addDogSubmit(imagePart,nameBody,breedBody,ageBody,doaBody,personalityBody,statusBody,genderBody).enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                Log.i("Success", "Process completed " + response.body());
                cbs.returnResult(true);
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(false);
            }
        });
    }

    public void DogUpdate(int ID, byte[] photoBytes, String name, String breed, String age, String doa, String personality, String status, String gender){
        RequestBody imageBody = RequestBody.create(MediaType.parse("image*/"),photoBytes);
        RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"),name);
        RequestBody breedBody = RequestBody.create(MediaType.parse("text/plain"),breed);
        RequestBody ageBody = RequestBody.create(MediaType.parse("text/plain"),age);
        RequestBody doaBody = RequestBody.create(MediaType.parse("text/plain"),doa);
        RequestBody personalityBody = RequestBody.create(MediaType.parse("text/plain"),personality);
        RequestBody statusBody = RequestBody.create(MediaType.parse("text/plain"),status);
        RequestBody genderBody = RequestBody.create(MediaType.parse("text/plain"),gender);

        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("photo","file",imageBody);
        dogApi.updateDog(ID,imagePart,nameBody,breedBody,ageBody,doaBody,personalityBody,statusBody,genderBody).enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                Log.i("Success", "Process completed " + response.body());
                cbs.returnResult(true);
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(false);
            }
        });
    }

    public void DogReadAll(){
        Call<List<Dog>> call = dogApi.getDogs();
        call.enqueue(new Callback<List<Dog>>() {
            @Override
            public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                Log.i("Success", "Process completed " + response.body());
                dogList = response.body();
                cbs.returnResult(dogList);
            }

            @Override
            public void onFailure(Call<List<Dog>> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(dogList);
            }
        });
    }

    public void DogRead(int id){
        Call<Dog> call = dogApi.getDog(id);
        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                Log.i("Success", "Process completed " + response.body());
                Dog holder = (Dog) response.body();
                dogData.setAge(holder.getAge());
                dogData.setBreed(holder.getBreed());
                dogData.setDoa(holder.getDoa());
                dogData.setName(holder.getName());
                dogData.setId(holder.getId());
                dogData.setGender(holder.getGender());
                dogData.setPersonality(holder.getPersonality());
                dogData.setPhoto(holder.getPhoto());
                dogData.setStatus(holder.getStatus());
                cbs.returnResult(dogData);
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(dogData);
            }
        });
    }

    public void AccountAdd(String firstName, String lastName, String address, String contactNumber, int age, String username, String password, String role){
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setMyAddress(address);
        account.setContactNumber(contactNumber);
        account.setAge(age);
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        accountApi.createAccount(account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.i("Success", "Process completed " + response.body());
                cbs.returnResult(true);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(false);
            }
        });
    }

    public void AccountUpdate(int ID, String firstName, String lastName, String address, String contactNumber, int age, String username, String password, String role){
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setMyAddress(address);
        account.setContactNumber(contactNumber);
        account.setAge(age);
        account.setUsername(username);
        account.setPassword(password);
        account.setRole(role);
        accountApi.updateAccount(ID, account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Log.i("Success", "Process completed " + response.body());
                cbs.returnResult(true);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(false);
            }
        });
    }

    public void AccountReadAll(){
        Call<List<Account>> call = accountApi.showAllAccount();
        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    Log.i("Success", "Process completed " + response.body());
                    accountList = response.body();
                    cbs.returnResult(accountList);

                }
            }
            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(accountList);
            }
        });
    }

    public void AccountRead(int id){
        Call<Account> call = accountApi.getAccount(id);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {

                    accountData = response.body();
                    cbs.returnResult(accountData);
                    Log.i("Success", "Process completed " + response.body());


            }
            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(accountData);
            }
        });

    }

    public void RequestAdd(Long dogID, Long userID, String contact, String message, String name, String status){
        Request request = new Request();
        request.setDogId(dogID);
        request.setUserId(userID);
        request.setReqContact(contact);
        request.setReqMessage(message);
        request.setReqName(name);
        request.setReqStatus(status);
        requestApi.createRequest(request).enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.i("Success", "Process completed " + response.body());
                cbs.returnResult(true);
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(false);
            }
        });
    };

    public void RequestUpdate(Long reqID, Long dogID, Long userID, String contact, String message, String name, String status){
        Request request = new Request();
        request.setDogId(dogID);
        request.setUserId(userID);
        request.setReqContact(contact);
        request.setReqMessage(message);
        request.setReqName(name);
        request.setReqStatus(status);
        requestApi.updateRequest(request.getReqId(),request).enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.i("Success", "Process completed " + response.body());
                cbs.returnResult(true);
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(false);
            }
        });
    };

    public void RequestReadAll(){
        Call<List<Request>> call = requestApi.findRequests();
        call.enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {

                if (response.isSuccessful() && response.body() != null) {

                    Log.i("Success", "Process completed " + response.body());
                    requestList = response.body();
                    cbs.returnResult(requestList);

                }
            }
            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(requestList);
            }
        });
    }

    public void RequestRead(int id){
        Call<Request> call = requestApi.getRequest((long) id);
        call.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {

                requestData = response.body();
                cbs.returnResult(requestData);
                Log.i("Success", "Process completed " + response.body());


            }
            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                Log.e("Failure","Process not completed " ,t);
                cbs.returnResult(requestData);
            }
        });

    }

    public CallBack getCbs() {
        return cbs;
    }

    public void setCbs(CallBack cbs) {
        this.cbs = cbs;
    }
}
