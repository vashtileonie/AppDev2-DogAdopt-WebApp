package com.rijai.LocationApi.service;

import com.rijai.LocationApi.model.Dog;
import com.rijai.LocationApi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService implements IDogService {
    @Autowired
    private DogRepository repository;

    public List<Dog> getDogs() {
        return (List<Dog>) repository.findAll();
    }

    public Dog getDog(Long id) {
        Optional<Dog> optional=repository.findById(id);
        if(optional.isEmpty())
            return null;
        else
            return (Dog) optional.get();
    }

    public Dog addDog(Dog dog) {
        return repository.save(dog);
    }

    public Dog updateDog(Long id, Dog dog) {
        Optional<Dog> existingDog = repository.findById(id);
        if(existingDog.isPresent()) {
            return repository.save(dog);
        }
        else
            return null;
    }

    public void deleto(Long id) {
        Optional<Dog> dog = repository.findById(id);
        dog.ifPresent(value -> repository.delete(value));
    }

    public void deleteDog(Long id)
    {
        Optional<Dog> dog = repository.findById(id);
        if(dog.isPresent()) {
            repository.delete(dog.get());
        }
    }    

}
