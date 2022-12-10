package hu.dansz.userdetails.collection;

import hu.dansz.userdetails.POJO.User;

import java.util.List;

public interface IUserCollection {
    List<User> getUsers();
    User findUserById(int id);
    void saveUser(User user);
}
