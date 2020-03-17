package com.europa.springblog.controllers;

import com.europa.springblog.models.Dog;
import com.europa.springblog.repositories.DogRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DogController {

    private DogRepo dogDao;

    public DogController(DogRepo adDao) {
        this.dogDao = adDao;
    }

    @GetMapping("/dogs")
    @ResponseBody
    public List<Dog> getAllDogs() {
        return dogDao.findAll();
    }

    @GetMapping("/dogs/save")
    @ResponseBody
    public String saveAd() {
        Dog newDog = new Dog();
        newDog.setAge(4);
        newDog.setName("Benito");
        newDog.setResideState("TX");
        dogDao.save(newDog);
        return "Saving dog";
    }

    @GetMapping("/dogs/update")
    @ResponseBody
    public String updateAd() {
        Dog dog = dogDao.getOne(1L);
        dog.setAge(1);
        dogDao.save(dog);
        return "Updating dog";
    }



}
