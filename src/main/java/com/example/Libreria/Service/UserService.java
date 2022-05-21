package com.example.Libreria.Service;

import com.example.Libreria.Entities.Role;
import com.example.Libreria.Entities.UserSecurity;
import com.example.Libreria.Repository.IClientRepository;
import com.example.Libreria.Repository.IUserRepository;
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
        return iUserRepository.save(new UserSecurity(username, encoder.encode(password), role));
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
