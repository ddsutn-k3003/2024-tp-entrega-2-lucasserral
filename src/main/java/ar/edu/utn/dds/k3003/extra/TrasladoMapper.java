package ar.edu.utn.dds.k3003.extra;

import ar.edu.utn.dds.k3003.model.Traslado;

public class TrasladoMapper {

    public Traslado Traslado(Traslado traslado) {
        Traslado trasladoTemp = new Traslado(traslado.getQrVianda(), traslado.getFechaTraslado(),
                traslado.getHeladeraOrigen(), traslado.getHeladeraDestino(), traslado.getColaboradorId());

        trasladoTemp.setId(traslado.getId());
        trasladoTemp.setEstado(traslado.getEstado());
        return trasladoTemp;
    }

}
