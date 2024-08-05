package com.rijai.LocationApi.controller;

import com.rijai.LocationApi.model.Dog;
import com.rijai.LocationApi.service.IDogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


@RestController
public class DogController {
    @Autowired
    private IDogService dogService;


    @RequestMapping("/api/dogs")
    public List<Dog> findDogs(){
       return dogService.getDogs();
    }

    @RequestMapping(value = "/api/show-dog/{id}")
    public Dog showDog(@PathVariable Long id) {
       return dogService.getDog(id);
    }

    @RequestMapping(value="/api/add-dog", method= RequestMethod.POST)
    public Dog addDogSubmit(@RequestParam(value = "photo", required = false) MultipartFile file, @RequestParam("name") String name, @RequestParam("breed") String breed, @RequestParam("age") int age, @RequestParam("doa") Date doa, @RequestParam("personality") String personality, @RequestParam("status") String status, @RequestParam("gender") String gender) throws IOException {
        byte[] bytes = null;
        if (file != null) {
            bytes = file.getBytes();
        }
        Dog dog = new Dog(null, bytes, name, breed, age, doa, personality, status, gender);
        return dogService.addDog(dog);

    }

    @RequestMapping(value="/api/update-dog/{id}", method=RequestMethod.PUT)
    public Dog updateDog(@PathVariable("id") Long id, @RequestParam(value = "photo", required = false) MultipartFile file, @RequestParam("name") String name, @RequestParam("breed") String breed, @RequestParam("age") int age, @RequestParam("doa") Date doa, @RequestParam("personality") String personality, @RequestParam("status") String status, @RequestParam("gender") String gender) throws IOException {
        byte[] bytes = null;
        if (file != null) {
            bytes = file.getBytes();
        }
        else{
            Dog dog = dogService.getDog(id);
            bytes = dog.getPhoto();
        }
        Dog dog = new Dog(id, bytes, name, breed, age, doa, personality, status, gender);
        return dogService.updateDog(id, dog);
    }
    @RequestMapping(value = "/api/delete-dog/{id}", method = RequestMethod.DELETE)
    public void deleteDog(@PathVariable("id") Long id) {
        dogService.deleteDog(id);
    }

}
