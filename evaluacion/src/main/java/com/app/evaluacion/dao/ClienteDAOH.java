package com.app.evaluacion.dao;

import com.app.evaluacion.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class ClienteDAOH implements ClienteDAO{

    @Autowired
    ClienteDAO clienteDAO;

    List<Cliente> listar() {
        return clienteDAO.findAll();
    }

}
