package com.europa.springblog.repositories;

import com.europa.springblog.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepo extends JpaRepository<Dog, Long> {

}
