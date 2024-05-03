package ar.edu.utn.dds.k3003.extra;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import ar.edu.utn.dds.k3003.model.Traslado;

public class TrasladoRepo {

    private static AtomicLong seqId = new AtomicLong();
    private ArrayList<Traslado> traslados;

    public TrasladoRepo() {
        this.traslados = new ArrayList<Traslado>();
    }

    public Traslado save(Traslado traslado) {
        if (Objects.isNull(traslado.getId())) {
            traslado.setId(seqId.getAndIncrement());
            this.traslados.add(traslado);
        }
        return traslado;
    }

    public Traslado findById(Long id) {
        Optional<Traslado> first = this.traslados.stream().filter(x -> x.getId().equals(id)).findFirst();
        return first.orElseThrow(() -> new NoSuchElementException(
                String.format("No hay un traslado de id: %s", id)));
    }

    public List<Traslado> filterByColaboradorID(Long colaboradorId) {
        return this.traslados.stream().filter(traslado -> traslado.getRuta().getColaboradorId().equals(colaboradorId))
                .toList();
    }
}
