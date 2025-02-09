package org.authmanager.user.infra.controllers;

import org.authmanager.user.domain.dto.input.Login;
import org.authmanager.user.domain.dto.input.Register;
import org.authmanager.user.domain.dto.output.Token;
import org.authmanager.user.domain.exception.CreateUserException;
import org.authmanager.user.domain.exception.UserExisted;
import org.authmanager.user.domain.ports.AuthService;
import org.authmanager.user.domain.ports.in.AuthRest;

import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthController implements AuthRest {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @RolesAllowed("superadmin")
    @POST
    @Path("/register")
    public Token register(Register user) {
        try {
            return authService.register(user);
        } catch (UserExisted e) {
            throw new ConflictException("User already exists");
        } catch (CreateUserException e) {
            System.err.println(e);
            throw new InternalServerErrorException("Failed to create user");
        }
    }

    @Override
    @POST
    @Path("/login")
    @PermitAll
    public Token login(Login login) {
        var value = authService.login(login);
        if (value.isErr()) {
            throw new UnauthorizedException("Invalid credentials");
        }
        return value.unwrap();
    }

}
