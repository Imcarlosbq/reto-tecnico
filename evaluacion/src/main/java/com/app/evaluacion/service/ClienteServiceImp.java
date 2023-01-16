package com.app.evaluacion.service;

import com.app.evaluacion.dao.ClienteDAO;
import com.app.evaluacion.dto.DatosEstadisticosClientes;
import com.app.evaluacion.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClienteDAO clienteDAO;
    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listar() {
        return clienteDAO.findAll();
    }

    @Transactional
    @Override
    public Cliente registrar(Cliente cliente) {
        return clienteDAO.save(cliente);
    }

    private BigDecimal promedio(){
        List<Cliente> clientes = clienteDAO.findAll();
        Integer cantidad = clientes.size();
        Integer sumaEdades = clientes.stream().map(x -> x.getEdad()).reduce(0 , (a, b) -> a + b);
        BigDecimal num1 = new BigDecimal(sumaEdades);
        BigDecimal num2 = new BigDecimal(cantidad);
        BigDecimal prom = num1.divide(num2);
        return prom;
    }

    private BigDecimal desviacion(){
        List<Cliente> clientes = clienteDAO.findAll();
        Integer cantidad = clientes.size();
        BigDecimal prom = promedio();
        List<BigDecimal> diferenciaCuadradaEdadConPromedio = clientes.stream().map(x -> (new BigDecimal(x.getEdad()).subtract(prom)).pow(2)).collect(Collectors.toList());
        BigDecimal sumaDiferenciaCuadrada = diferenciaCuadradaEdadConPromedio.stream().reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        BigDecimal div = sumaDiferenciaCuadrada.divide(new BigDecimal(cantidad - 1));
        BigDecimal desviacion = new BigDecimal(Math.sqrt(div.doubleValue()));
        return desviacion;
    }

    @Transactional(readOnly = true)
    @Override
    public DatosEstadisticosClientes kpiClientes() {
        BigDecimal prom = promedio();
        BigDecimal desv = desviacion();
        DatosEstadisticosClientes datosEstadisticosClientes = DatosEstadisticosClientes.builder().promedioEdad(prom).desviacionEstandarEdad(desv).build();
        return datosEstadisticosClientes;
    }

}
