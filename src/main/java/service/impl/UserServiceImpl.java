package service.impl;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public Page<User> findAllByEmailContaining(String name, Pageable pageable) {
        return userRepository.findAllByEmailContaining(name, pageable);
    }

    @Override
    public Iterable<User> findAllByEmailContaining(String name) {
        return userRepository.findAllByEmailContaining(name);
    }
}
