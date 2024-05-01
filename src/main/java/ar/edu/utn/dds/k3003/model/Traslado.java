package ar.edu.utn.dds.k3003.model;

import java.time.LocalDateTime;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoTrasladoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Traslado {

    private Long id;
    private String qrVianda;
    private EstadoTrasladoEnum estado;
    private LocalDateTime fechaTraslado;
    private Integer heladeraOrigen;
    private Integer heladeraDestino;
    private Long colaboradorId;

    public Traslado(String qrVianda, LocalDateTime fechaTraslado, Integer heladeraOrigen, Integer heladeraDestino,
            Long colaboradorId) {
        this.qrVianda = qrVianda;
        this.fechaTraslado = fechaTraslado;
        this.heladeraOrigen = heladeraOrigen;
        this.heladeraDestino = heladeraDestino;
        this.colaboradorId = colaboradorId;
    }
}
