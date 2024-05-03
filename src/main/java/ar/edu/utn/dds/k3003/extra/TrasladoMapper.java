package ar.edu.utn.dds.k3003.extra;

import ar.edu.utn.dds.k3003.facades.dtos.TrasladoDTO;
import ar.edu.utn.dds.k3003.model.Traslado;

public class TrasladoMapper {

    public TrasladoDTO map(Traslado traslado) {
        TrasladoDTO trasladoTemp = new TrasladoDTO(traslado.getQrVianda(), traslado.getEstado(),
                traslado.getFechaTraslado(), traslado.getRuta().getHeladeraIdOrigen(),
                traslado.getRuta().getHeladeraIdDestino());

        trasladoTemp.setId(traslado.getId());
        trasladoTemp.setColaboradorId(traslado.getColaboradorId());
        return trasladoTemp;
    }

}
