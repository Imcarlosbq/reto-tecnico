package com.app.clienteconsumeraync.controller;

import com.app.clienteconsumeraync.service.ConsumirServicioProvider;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class ClienteConsumerController {

    @Autowired
    ConsumirServicioProvider consumirServicioProvider;

    @GetMapping(path = {"registrar/{nombre}/{apellido}/{edad}/{fechaNacimiento}"})
    public ResponseEntity<?> altaCliente(
            @PathVariable(name = "nombre", required = true) String nombre,
            @PathVariable(name = "apellido", required = true) String apellido,
            @PathVariable(name = "edad", required = true) Integer edad,
            @PathVariable(name = "fechaNacimiento", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento) {
        consumirServicioProvider.msProviderRegistrarCliente(nombre, apellido, edad, fechaNacimiento);
        ObjectNode[] obj = consumirServicioProvider.msProviderListarCliente();
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @GetMapping(path = {"registrar-async/{nombre}/{apellido}/{edad}/{fechaNacimiento}"})
    public ResponseEntity<?> altaClienteAsync(
            @PathVariable(name = "nombre", required = true) String nombre,
            @PathVariable(name = "apellido", required = true) String apellido,
            @PathVariable(name = "edad", required = true) Integer edad,
            @PathVariable(name = "fechaNacimiento", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento) throws InterruptedException, ExecutionException {
        consumirServicioProvider.msAsyncProviderRegistrarCliente(nombre, apellido, edad, fechaNacimiento);
        CompletableFuture<ObjectNode[]> completableFuture = consumirServicioProvider.msAsyncProviderListarClientes();
        for (int i = 0; i < 100; i++) {
            System.out.println("Esperando respuesta");
            Thread.sleep(1000);
        }
        return new ResponseEntity<>(completableFuture.get(), HttpStatus.OK);
        //Arrays.stream(obj).forEach(x -> System.out.println(x.get("nombre").asText()));
    }


    @GetMapping(path = {"kpi"})
    public ResponseEntity<?> obtenerKpiDeClientes() {
        ObjectNode response = consumirServicioProvider.msProviderKpiDeClientes();
        //return restTemplate.getForEntity(urlKpiDeClientes, ObjectNode.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
