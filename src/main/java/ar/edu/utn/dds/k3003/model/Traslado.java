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
    private Ruta ruta;
    private Long colaboradorId;

    public Traslado(
            String qrVianda,
            Ruta ruta,
            EstadoTrasladoEnum estado,
            LocalDateTime fechaTraslado,
            Long colaboradorId) {
        this.qrVianda = qrVianda;
        this.fechaTraslado = fechaTraslado;
        this.estado = estado;
        this.ruta = ruta;
        this.colaboradorId = colaboradorId;
    }
}
