package com.app.evaluacion.service;

import com.app.evaluacion.dto.DatosEstadisticosClientes;
import com.app.evaluacion.model.Cliente;

import java.math.BigDecimal;
import java.util.List;

public interface ClienteService {

    List<Cliente> listar();

    Cliente registrar(Cliente cliente);

    DatosEstadisticosClientes kpiClientes();

}
