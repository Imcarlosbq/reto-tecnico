package com.app.evaluacion.controller;

import com.app.evaluacion.dao.ClienteDAOH;
import com.app.evaluacion.dto.DatosEstadisticosClientes;
import com.app.evaluacion.model.Cliente;
import com.app.evaluacion.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "cliente")
public class ClienteController {

    /*private List<Cliente> clientes;

    @PostConstruct
    public void init() {
        this.clientes = new ArrayList<>();
        clientes.add(new Cliente(1L, "Juan", "Gomez Pastor", 42, new Date(1980, 12, 20), new Date()));
        clientes.add(new Cliente(2L, "Ernesto", "Buitrón Cristobal", 72, new Date(1950, 10, 4), new Date()));
        clientes.add(new Cliente(3L, "Carlos", "Gomez Solis", 32, new Date(1990, 10, 10), new Date()));
        clientes.add(new Cliente(4L, "Jorge", "Ureta Manco", 40, new Date(1983, 5, 22), new Date()));
        clientes.add(new Cliente(5L, "Susana", "Jaime Sotel", 36, new Date(1986, 4, 30), new Date()));
    }*/

    @Autowired
    ClienteService clienteService;

    @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> listar () {
        List<Cliente> clientes = clienteService.listar();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping(path = "/crearcliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> registrar (@RequestBody Cliente cliente) {
        Cliente clienteDB = clienteService.registrar(cliente);
        return new ResponseEntity<>(clienteDB, HttpStatus.OK);
    }

    @GetMapping(path = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DatosEstadisticosClientes> kpideclientes () {
        DatosEstadisticosClientes datosEstadisticosClientes = clienteService.kpiClientes();
        return new ResponseEntity<>(datosEstadisticosClientes, HttpStatus.OK);
    }

}
