package com.musiqle.Musiqle;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Long> {

    List<Users> findAllByOrderByIdAsc();
}
