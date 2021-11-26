package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    List<User> listUsersWithCars();
    List<User> listUsersByCar(String model, int series);
    void deleteAll();
}
