package com.app.evaluacion.dao;

import com.app.evaluacion.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
}
