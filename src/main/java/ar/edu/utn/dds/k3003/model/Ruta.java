package ar.edu.utn.dds.k3003.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ruta {
    private Long id;
    private Long colaboradorId;
    private Integer heladeraIdOrigen;
    private Integer heladeraIdDestino;

    public Ruta(Long colaboradorId, Integer heladeraIdOrigen, Integer heladeraIdDestino) {
        // Para definir el id, creo que no lo haría en el código sino después en la
        // capa de datos (dentro de la db con un index creciente)
        this.colaboradorId = colaboradorId;
        this.heladeraIdDestino = heladeraIdDestino;
        this.heladeraIdOrigen = heladeraIdOrigen;
    }
}
