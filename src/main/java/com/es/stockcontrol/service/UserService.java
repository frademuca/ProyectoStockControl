package com.es.stockcontrol.service;

import com.es.stockcontrol.model.User;
import com.es.stockcontrol.repository.UserRepository;
import com.es.stockcontrol.utils.Validator;

import static java.util.Optional.empty;

public class UserService {

    public boolean login(String username, String password) {

        // Validar el formato del nombre de usuario introducido y si viene vacío
        if (Validator.validateName(username) || Validator.validateNonEmpty(username)) {
            return false;
        }

        // Obtener el usuario de la base de datos
        User user = new User();
        UserRepository userRepository = new UserRepository();
        user = userRepository.read(username);


        // Comprobar que el username y la password coinciden con el obtenido de la base de datos
        if (user.getNombreUsuario().equals(username) && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
