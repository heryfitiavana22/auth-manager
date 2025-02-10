package org.authmanager.user.infra.controllers;

import org.authmanager.user.domain.dto.output.UserOuput;
import org.authmanager.user.domain.exception.UnauthorizedAuthException;
import org.authmanager.user.domain.ports.UserService;
import org.authmanager.user.domain.ports.in.UserRest;

import io.quarkus.security.Authenticated;
import io.quarkus.security.UnauthorizedException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;

@Authenticated
@Path("/api/user")
public class UserController implements UserRest {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GET
    @Path("/me")
    public UserOuput me(@HeaderParam("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            return userService.me(token);
        } catch (UnauthorizedAuthException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

}
