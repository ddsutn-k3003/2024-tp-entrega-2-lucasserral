package ar.edu.utn.dds.k3003.app;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import ar.edu.utn.dds.k3003.extra.RutaMapper;
import ar.edu.utn.dds.k3003.extra.RutaRepo;
import ar.edu.utn.dds.k3003.extra.TrasladoMapper;
import ar.edu.utn.dds.k3003.extra.TrasladoRepo;
import ar.edu.utn.dds.k3003.facades.FachadaHeladeras;
import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.EstadoTrasladoEnum;
import ar.edu.utn.dds.k3003.facades.dtos.RetiroDTO;
import ar.edu.utn.dds.k3003.facades.dtos.RutaDTO;
import ar.edu.utn.dds.k3003.facades.dtos.TrasladoDTO;
import ar.edu.utn.dds.k3003.facades.dtos.ViandaDTO;
import ar.edu.utn.dds.k3003.facades.exceptions.TrasladoNoAsignableException;
import ar.edu.utn.dds.k3003.model.Ruta;
import ar.edu.utn.dds.k3003.model.Traslado;

public class Fachada implements ar.edu.utn.dds.k3003.facades.FachadaLogistica {

    private RutaRepo rutaRepo;
    private TrasladoRepo trasladoRepo;
    private RutaMapper rutaMapper;
    private TrasladoMapper trasladoMapper;
    private FachadaViandas fachadaViandas;
    private FachadaHeladeras fachadaHeladeras;

    public Fachada() {
        this.rutaRepo = new RutaRepo();
        this.trasladoRepo = new TrasladoRepo();
        this.rutaMapper = new RutaMapper();
        this.trasladoMapper = new TrasladoMapper();
    }

    @Override
    public RutaDTO agregar(RutaDTO rutaDTO) {
        Ruta ruta = new Ruta(rutaDTO.getColaboradorId(), rutaDTO.getHeladeraIdOrigen(), rutaDTO.getHeladeraIdDestino());
        ruta = this.rutaRepo.add(ruta);
        return this.rutaMapper.map(ruta);
    }

    @Override
    public TrasladoDTO buscarXId(Long trasladoId) throws NoSuchElementException {
        Traslado tralsado = this.trasladoRepo.findById(trasladoId);
        return new TrasladoDTO(tralsado.getQrVianda(), tralsado.getRuta().getHeladeraIdOrigen(),
                tralsado.getRuta().getHeladeraIdDestino());
    }

    @Override
    public TrasladoDTO asignarTraslado(TrasladoDTO trasladoDTO) throws TrasladoNoAsignableException {
        ViandaDTO viandaDTO = fachadaViandas.buscarXQR(trasladoDTO.getQrVianda());

        if (Objects.isNull(viandaDTO)) {
            throw new NoSuchElementException();
        }

        Ruta ruta = this.rutaRepo.findByHeladeras(trasladoDTO.getHeladeraOrigen(),
                trasladoDTO.getHeladeraDestino());

        if (Objects.isNull(ruta)) {
            throw new TrasladoNoAsignableException();
        }

        Traslado traslado = trasladoRepo.save(new Traslado(viandaDTO.getCodigoQR(), ruta, EstadoTrasladoEnum.ASIGNADO,
                trasladoDTO.getFechaTraslado(), ruta.getColaboradorId()));

        return this.trasladoMapper.map(traslado);
    }

    @Override
    public List<TrasladoDTO> trasladosDeColaborador(Long arg0, Integer arg1, Integer arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void trasladoRetirado(Long trasladoId) {
        Traslado traslado = trasladoRepo.findById(trasladoId);
        String qrVianda = traslado.getQrVianda();

        this.fachadaHeladeras.retirar(new RetiroDTO(qrVianda, null, null));

    }

    @Override
    public void trasladoDepositado(Long arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHeladerasProxy(FachadaHeladeras fachadaHeladeras) {
        this.fachadaHeladeras = fachadaHeladeras;
    }

    @Override
    public void setViandasProxy(FachadaViandas fachadaViandas) {
        this.fachadaViandas = fachadaViandas;
    }
}
