package com.app.evaluacion.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Getter
@Builder
public class DatosEstadisticosClientes {

    private BigDecimal promedioEdad;

    private BigDecimal desviacionEstandarEdad;

}
