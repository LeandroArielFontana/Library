package com.example.Libreria3.Service;

import com.example.Libreria3.Entities.Client;
import com.example.Libreria3.Entities.Role;
import com.example.Libreria3.Entities.UserSecurity;
import com.example.Libreria3.Repository.IClientRepository;
import com.example.Libreria3.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IClientRepository iClientRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String MENSAJE = "El username ingresado no existe %s";

    @Transactional
    public UserSecurity create(String username, String password, Role role){
        UserSecurity userSecurity = new UserSecurity();

        userSecurity.setUsername(username);
        userSecurity.setPassword(encoder.encode(password));
        userSecurity.setRole(role);

        return iUserRepository.save(userSecurity);
    }

    //Metodo para logearse, corrobora los datos en la DB
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity userSecurity = iUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(MENSAJE, username)));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userSecurity.getRole().getName());

        return new User(userSecurity.getUsername(), userSecurity.getPassword(), Collections.singletonList(authority));
    }
}
