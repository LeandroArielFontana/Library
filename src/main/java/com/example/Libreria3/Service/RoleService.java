package com.example.Libreria3.Service;

import com.example.Libreria3.Entities.Role;
import com.example.Libreria3.Repository.IRoleRepository;
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
        Role role = new Role();
        role.setName(roleName);
        iRoleRepository.save(role);
    }

    @Transactional(readOnly = true)
    public List<Role> findAll(){
        return iRoleRepository.findAll();
    }
}
