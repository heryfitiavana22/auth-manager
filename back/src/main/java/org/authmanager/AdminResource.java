package org.authmanager;

import org.keycloak.TokenVerifier;
import org.keycloak.representations.AccessToken;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/admin")
@Authenticated
public class AdminResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String admin() {
        return "granted";
    }

    @GET
    @Path("/user")
    public Response getUserInfo(@HeaderParam("Authorization") String authHeader) {
        try {
            System.out.println(authHeader);
            String token = authHeader.replace("Bearer ", "");

            // Vérification et décodage du token
            TokenVerifier<AccessToken> tokenVerifier = TokenVerifier.create(token, AccessToken.class)
                    .parse();

            AccessToken accessToken = tokenVerifier.getToken();

            return Response.ok(accessToken).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Impossible de récupérer les informations de l'utilisateur : " + e.getMessage())
                    .build();
        }
    }
}
