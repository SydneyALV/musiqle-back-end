package com.musiqle.Musiqle;

import java.util.List;

public interface IUserService {

    List<User> find();
    User create(User user);
    User update(Long id, int score);
    void delete(Long id);

}
