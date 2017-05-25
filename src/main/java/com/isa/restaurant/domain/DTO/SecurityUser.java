package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Milos on 23-May-17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Data
public class SecurityUser implements UserDetails
{
    private Long id;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean enabled;

    public SecurityUser(User user)
    {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        this.enabled = user.getEnabled();
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }
}
