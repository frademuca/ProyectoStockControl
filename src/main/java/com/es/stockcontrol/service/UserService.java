package com.es.stockcontrol.service;

import com.es.stockcontrol.model.User;
import com.es.stockcontrol.repository.UserRepository;

import static java.util.Optional.empty;

public class UserService {

    public boolean login(String username, String password) {

        // 1º Obtener el usuario de la base de datos
        User user = new User();
        UserRepository userRepository = new UserRepository();
        user = userRepository.read(username);

        // 2º Comprobar que el username y la password coinciden con el obtenido de la bd
        if (user == null){
            return false;
        }

        if (user.getNombreUsuario().equals(username) && user.getPassword().equals(password)){
            return true;
        } else {
            return false;
        }

    }
}
