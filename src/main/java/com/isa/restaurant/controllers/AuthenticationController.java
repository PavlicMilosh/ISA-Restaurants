package com.isa.restaurant.controllers;

import javax.servlet.http.HttpServletRequest;

import com.isa.restaurant.domain.DTO.SecurityUser;
import com.isa.restaurant.domain.DTO.authentication.AuthenticationRequestDTO;
import com.isa.restaurant.domain.DTO.authentication.AuthenticationResponseDTO;
import com.isa.restaurant.security.TokenUtils;
import com.mysql.jdbc.Security;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthenticationController
{

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${isa.token.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequestDTO authenticationRequest) throws AuthenticationException
    {
        // Perform the authentication
        Authentication authentication = null;
        UsernamePasswordAuthenticationToken t = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        try
        {
            authentication = this.authenticationManager.authenticate(t);
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Reload password post-authentication so we can generate token
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        SecurityUser su = (SecurityUser) userDetails;
        String token = this.tokenUtils.generateToken(userDetails);

        // Return the token
        return new ResponseEntity<>(new AuthenticationResponseDTO(token, su.getId(), su.getEmail(), su.getAuthorities().toString(), su.getEnabled()), HttpStatus.OK);
    }

    @RequestMapping(value = "${isa.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request)
    {
        String token = request.getHeader(this.tokenHeader);
        String username = this.tokenUtils.getUsernameFromToken(token);
        SecurityUser user = (SecurityUser) this.userDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token))
        {
            String refreshedToken = this.tokenUtils.refreshToken(token);
            return new ResponseEntity<>(new AuthenticationResponseDTO(refreshedToken, user.getId(), user.getEmail(), user.getAuthorities().toString(), user.getEnabled()), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
