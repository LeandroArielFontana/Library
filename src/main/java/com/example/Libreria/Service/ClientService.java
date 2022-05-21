package com.example.Libreria.Service;

import com.example.Libreria.Entities.Client;
import com.example.Libreria.Entities.Role;
import com.example.Libreria.Exceptions.MyException;
import com.example.Libreria.Repository.IClientRepository;
import com.example.Libreria.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private IClientRepository iClientRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void create(long dni, String name, String lastName, String phoneNumber, String username, String password, Role role) throws MyException {

        if (Util.checkWhiteSpace(name) && Util.checkWhiteSpace(lastName)) {
            throw new MyException("Los campos no pueden venir vacios");
        } else if (!Util.checkNames(name) && !Util.checkNames(lastName)) {
            throw new MyException("Los nombres no deben contener numeros o espacios");
        } else if (!Util.checkPhoneNumber(phoneNumber)) {
            throw new MyException("Numero de telefono invalido (debe tener entre 8 y 10 digitos)");
        } else if (!Util.checkDNI(dni)) {
            throw new MyException("El DNI ingresado debe tener entre 7 y 9 caracteres");
        }

        iClientRepository.save(new Client(dni, name, lastName, phoneNumber, userService.create(username, password, role)));
    }

    @Transactional
    public void update(int id, String name, String lastName, String phoneNumber, long dni) throws MyException {
        Client client = iClientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new MyException("No existe un Cliente con ese ID");
        } else if (Util.checkWhiteSpace(name) && Util.checkWhiteSpace(lastName)) {
            throw new MyException("Los campos no pueden venir vacios");
        } else if (!Util.checkNames(name) && !Util.checkNames(lastName)) {
            throw new MyException("Los nombres no deben contener numeros o espacios");
        } else if (!Util.checkPhoneNumber(phoneNumber)) {
            throw new MyException("Numero de telefono invalido (debe tener entre 8 y 10 digitos)");
        } else if (!Util.checkDNI(dni)) {
            throw new MyException("El DNI ingresado debe tener entre 7 y 9 caracteres");
        }

        client.setName(name);
        client.setLastName(lastName);
        client.setPhoneNumber(phoneNumber);
        client.setDni(dni);
        iClientRepository.save(client);
    }

    @Transactional
    public void delete(Integer id) {
        iClientRepository.deleteById(id);
    }

    @Transactional
    public void register(Integer id) {
        iClientRepository.register(id);
    }

    @Transactional
    public Client findById(Integer id) throws MyException {
        Client client = iClientRepository.findById(id).orElse(null);

        if (client == null) {
            throw new MyException("No existe un Cliente con ese ID");
        }

        return client;
    }

    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return iClientRepository.findAll();
    }

    @Transactional
    public List<Client> availableClients() {
        return iClientRepository.available();
    }
}
