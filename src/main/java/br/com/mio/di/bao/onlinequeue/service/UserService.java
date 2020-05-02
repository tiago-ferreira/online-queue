package br.com.mio.di.bao.onlinequeue.service;

import br.com.mio.di.bao.onlinequeue.model.users.User;
import br.com.mio.di.bao.onlinequeue.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getUsers() {
        return usersRepository.findAll();
    }
}
