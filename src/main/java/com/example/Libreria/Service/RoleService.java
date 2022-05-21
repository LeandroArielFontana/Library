package com.example.Libreria.Service;

import com.example.Libreria.Entities.Role;
import com.example.Libreria.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private IRoleRepository iRoleRepository;

    @Transactional
    public void create(String roleName){
        iRoleRepository.save(new Role(roleName));
    }

    @Transactional(readOnly = true)
    public List<Role> findAll(){
        return iRoleRepository.findAll();
    }
}
