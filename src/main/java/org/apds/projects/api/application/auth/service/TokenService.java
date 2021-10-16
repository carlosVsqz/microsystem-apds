package org.apds.projects.api.application.auth.service;

import com.auth0.jwt.interfaces.DecodedJWT;

import org.apds.projects.api.application.auth.entity.User;
import org.apds.projects.api.application.auth.model.TokenInfo;

public interface TokenService {

    TokenInfo createToken(User user);

    DecodedJWT verifyToken(String token);

}
