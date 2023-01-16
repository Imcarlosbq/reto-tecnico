package com.app.clienteconsumeraync.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ClienteConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(path = {"registrar/{nombre}/{apellido}/{edad}/{fechaNacimiento}"})
    public ResponseEntity<?> altaCliente(
            @PathVariable(name = "nombre") String nombre,
            @PathVariable(name = "apellido") String apellido,
            @PathVariable(name = "edad") Integer edad,
            @PathVariable(name = "fechaNacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento) {
        String urlRegistrar  = "http://localhost:8081/app/cliente/crearcliente";
        String urlListar  = "http://localhost:8081/app/cliente/list";
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("nombre", nombre);
        objectNode.put("apellido", apellido);
        objectNode.put("edad", edad);
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento);
        objectNode.put("fechaNacimiento", fecha);
        restTemplate.postForLocation(urlRegistrar, objectNode);
        ObjectNode[] obj = restTemplate.getForObject(urlListar, ObjectNode[].class);
        return new ResponseEntity<>(obj, HttpStatus.OK);
        //Arrays.stream(obj).forEach(x -> System.out.println(x.get("nombre").asText()));
    }


    @GetMapping(path = {"kpi"})
    public ResponseEntity<?> obtenerKpiDeClientes() {
        String urlKpiDeClientes  = "http://localhost:8081/app/cliente/kpideclientes";
        ResponseEntity<ObjectNode> response = restTemplate.getForEntity(urlKpiDeClientes, ObjectNode.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

}
