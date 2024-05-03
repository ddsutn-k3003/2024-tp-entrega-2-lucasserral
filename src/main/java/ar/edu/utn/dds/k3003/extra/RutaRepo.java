package ar.edu.utn.dds.k3003.extra;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.text.html.Option;

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

    public List<Ruta> filterByHeladeras(Integer heladeraOrigen, Integer heladeraDestino) {
        List<Ruta> rutas = this.rutas.stream()
                .filter(rutaCurr -> rutaCurr.getHeladeraIdOrigen().equals(heladeraOrigen)
                        && rutaCurr.getHeladeraIdDestino().equals(heladeraDestino))
                .toList();
        return rutas;
    }

    public Ruta findByHeladeras(Integer heladeraOrigen, Integer heladeraDestino) {
        List<Ruta> rutas = this.filterByHeladeras(heladeraOrigen, heladeraDestino);
        if (rutas.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Ruta ruta = rutas.get(0);
            return ruta;
        }
    }

}
