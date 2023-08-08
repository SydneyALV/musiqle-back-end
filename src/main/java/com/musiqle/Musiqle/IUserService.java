package com.musiqle.Musiqle;

import java.util.List;

public interface IUserService {
    List<Users> find();

    Users create(Users users);

    Users update(Long id, int score);

    void delete(Long id);
}
