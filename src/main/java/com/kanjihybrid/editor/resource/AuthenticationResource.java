package com.kanjihybrid.editor.resource;

import com.kanjihybrid.editor.dto.AuthenticationDto;
import com.kanjihybrid.editor.model.User;
import com.kanjihybrid.editor.util.UserUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Frank Lloyd Teh
 */
@RestController
public class AuthenticationResource {

    @Transactional
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/authentication", method = GET)
    public AuthenticationDto authentication() {
        User user = UserUtil.getCurrentUser();

        AuthenticationDto auth = new AuthenticationDto();
        auth.setUsername(user.getUsername());
        auth.getRoles().addAll(user.getRoles());
        auth.setFirstName(user.getFirstName());
        auth.setLastName(user.getLastName());
        auth.setEmail(user.getUsername());
        return auth;
    }

}
