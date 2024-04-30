package ar.edu.utn.dds.k3003.app;

import java.util.List;
import java.util.NoSuchElementException;

import ar.edu.utn.dds.k3003.facades.FachadaHeladeras;
import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.RutaDTO;
import ar.edu.utn.dds.k3003.facades.dtos.TrasladoDTO;
import ar.edu.utn.dds.k3003.facades.exceptions.TrasladoNoAsignableException;

public class Fachada implements ar.edu.utn.dds.k3003.facades.FachadaLogistica {

    public Fachada() {

    }

    @Override
    public RutaDTO agregar(RutaDTO arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TrasladoDTO buscarXId(Long arg0) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TrasladoDTO asignarTraslado(TrasladoDTO arg0) throws TrasladoNoAsignableException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TrasladoDTO> trasladosDeColaborador(Long arg0, Integer arg1, Integer arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void trasladoRetirado(Long arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void trasladoDepositado(Long arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHeladerasProxy(FachadaHeladeras arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setViandasProxy(FachadaViandas arg0) {
        // TODO Auto-generated method stub

    }
}
