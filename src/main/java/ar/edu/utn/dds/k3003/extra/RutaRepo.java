package ar.edu.utn.dds.k3003.extra;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import ar.edu.utn.dds.k3003.model.Ruta;

public class RutaRepo {

    private static AtomicLong seqId = new AtomicLong();
    private ArrayList<Ruta> rutas;

    public RutaRepo() {
        this.rutas = new ArrayList<Ruta>();
    }

    public Ruta add(Ruta ruta) {
        ruta.setId(seqId.getAndIncrement());
        this.rutas.add(ruta);
        return ruta;
    }

    public Ruta update(Ruta ruta) {
        return ruta;
    }

    public Ruta findById(Long id) {
        Optional<Ruta> first = this.rutas.stream().filter(x -> x.getId().equals(id)).findFirst();
        return first.orElseThrow(() -> new NoSuchElementException(
                String.format("No hay una ruta de id: %s", id)));
    }

}
