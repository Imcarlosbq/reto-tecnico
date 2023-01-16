package com.app.clienteconsumeraync.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
public class ConsumirServicioProviderImp implements ConsumirServicioProvider {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void msProviderRegistrarCliente(String nombre, String apellido, Integer edad, Date fechaNacimiento) {
        String urlRegistrar  = "http://localhost:8081/app/cliente/crearcliente";
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("nombre", nombre);
        objectNode.put("apellido", apellido);
        objectNode.put("edad", edad);
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento);
        objectNode.put("fechaNacimiento", fecha);
        restTemplate.postForLocation(urlRegistrar, objectNode);
    }

    @Override
    public ObjectNode[] msProviderListarCliente() {
        String urlListar  = "http://localhost:8081/app/cliente/list";
        ObjectNode[] objectNodes = restTemplate.getForObject(urlListar, ObjectNode[].class);
        return objectNodes;
    }

    @Override
    public ObjectNode msProviderKpiDeClientes() {
        String urlKpiDeClientes  = "http://localhost:8081/app/cliente/kpideclientes";
        ObjectNode objectNode = restTemplate.getForEntity(urlKpiDeClientes, ObjectNode.class).getBody();
        return objectNode;
    }

    @Async
    @Override
    public CompletableFuture<ObjectNode[]> msAsyncProviderListarClientes() {
        String urlListar  = "http://localhost:8081/app/cliente/list";
        ObjectNode[] objectNodes = restTemplate.getForObject(urlListar, ObjectNode[].class);
        return CompletableFuture.completedFuture(objectNodes);
    }

    @Async
    @Override
    public void msAsyncProviderRegistrarCliente(String nombre, String apellido, Integer edad, Date fechaNacimiento) {
        String urlRegistrar  = "http://localhost:8081/app/cliente/crearcliente";
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("nombre", nombre);
        objectNode.put("apellido", apellido);
        objectNode.put("edad", edad);
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento);
        objectNode.put("fechaNacimiento", fecha);
        restTemplate.postForLocation(urlRegistrar, objectNode);
        CompletableFuture.completedFuture(null);
    }

}
