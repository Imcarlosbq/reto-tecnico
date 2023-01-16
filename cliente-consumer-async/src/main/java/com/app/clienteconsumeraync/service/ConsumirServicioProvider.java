package com.app.clienteconsumeraync.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface ConsumirServicioProvider {

    void msProviderRegistrarCliente(String nombre, String apellido, Integer integer, Date date);

    ObjectNode[] msProviderListarCliente();

    ObjectNode msProviderKpiDeClientes();

    CompletableFuture<ObjectNode[]> msAsyncProviderListarClientes();

    void msAsyncProviderRegistrarCliente(String nombre, String apellido, Integer edad, Date fechaNacimiento);

}
