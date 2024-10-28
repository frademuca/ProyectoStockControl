package com.es.stockcontrol.controller.impl;


import com.es.stockcontrol.controller.api.UserControllerAPI;
import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.model.User;
import com.es.stockcontrol.service.UserService;

public class UserController implements UserControllerAPI {

    private UserService userService;

    @Override
    public RespuestaHTTP<User> login(String userInput, String passInput) {

        try {

            if(userInput == null || userInput.isEmpty())
                return new RespuestaHTTP<>(400, "Credenciales incorrectos");
            if(passInput == null || passInput.isEmpty())
                return new RespuestaHTTP<>(400, "Credenciales incorrectos");

            boolean respuestaService = userService.login(userInput, passInput);

            return respuestaService ?
                    new RespuestaHTTP<>(200, "Acceso correcto") :
                    new RespuestaHTTP<>(401, "No autorizado");

        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error del servidor");
        }

    }
}