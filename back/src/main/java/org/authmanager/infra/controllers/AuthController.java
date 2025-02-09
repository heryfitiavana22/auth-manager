package org.authmanager.infra.controllers;

import org.authmanager.domain.dto.input.Login;
import org.authmanager.domain.dto.input.Register;
import org.authmanager.domain.dto.output.Token;
import org.authmanager.domain.ports.AuthService;
import org.authmanager.domain.ports.in.AuthRest;

import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthController implements AuthRest {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Token register(Register user) {
        return authService.register(user);
    }

    @Override
    @POST
    @Path("/login")
    @PermitAll
    public Token login(Login login) {
        var value = authService.login(login);
        if (value.isEmpty()) {
            throw new UnauthorizedException("Invalid credentials");
        }
        return value.get();
    }

}
