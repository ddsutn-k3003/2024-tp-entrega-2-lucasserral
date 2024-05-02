package ar.edu.utn.dds.k3003.extra;

import ar.edu.utn.dds.k3003.model.Ruta;

public class RutaMapper {
    public Ruta Ruta(Ruta ruta) {
        return new Ruta(ruta.getColaboradorId(), ruta.getHeladeraIdOrigen(), ruta.getHeladeraIdDestino());
    }
}
