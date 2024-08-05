package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Dog;

import java.util.List;

public interface IDogService {
    List<Dog> getDogs();
    Dog addDog(Dog dog);
    Dog updateDog(Long id, Dog dog);
    Dog getDog(Long id);
    void deleteDog(Long id);
}