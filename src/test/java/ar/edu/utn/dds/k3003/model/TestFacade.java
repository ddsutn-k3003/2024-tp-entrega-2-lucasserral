package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.facades.FachadaHeladeras;
import ar.edu.utn.dds.k3003.facades.FachadaViandas;
import ar.edu.utn.dds.k3003.facades.dtos.*;
import ar.edu.utn.dds.k3003.facades.exceptions.TrasladoNoAsignableException;
import ar.edu.utn.dds.k3003.tests.TestTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({ MockitoExtension.class })
public class TestFacade implements TestTP<Fachada> {

  Fachada instance;
  @Mock
  FachadaViandas fachadaViandas;
  @Mock
  FachadaHeladeras fachadaHeladeras;

  public TestFacade() {
  }

  @BeforeEach
  void setUp() throws Throwable {
    try {
      this.instance = (Fachada) this.instance();
      this.instance.setHeladerasProxy(this.fachadaHeladeras);
      this.instance.setViandasProxy(this.fachadaViandas);
    } catch (Throwable var2) {
      throw var2;
    }
  }

  @Test
  @DisplayName("Crear y asignar traslado")
  void testCreateTrasladoAssign() throws Exception {
    String qrViandaTest = "test_vianda";
    ViandaDTO viandaDto = new ViandaDTO(qrViandaTest, LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 2L, 1);
    Mockito.when(this.fachadaViandas.buscarXQR(qrViandaTest)).thenReturn(viandaDto);
    Ruta ruta = new Ruta(99999999L, 1, 2);
    this.instance
        .agregar(new RutaDTO(ruta.getColaboradorId(), ruta.getHeladeraIdOrigen(), ruta.getHeladeraIdDestino()));
    // Traslado traslado = new Traslado(viandaDto.getCodigoQR(), ruta, null,
    // LocalDateTime.now(), ruta.getColaboradorId());
    TrasladoDTO traslado_2 = this.instance.asignarTraslado(new TrasladoDTO(qrViandaTest, 1, 2));
    Assertions.assertTrue(this.instance.buscarXId(traslado_2.getId()).getQrVianda().equals(qrViandaTest),
        "vianda incorrecta");
    Assertions.assertTrue(traslado_2.getColaboradorId().equals(ruta.getColaboradorId()), "colaborador id incorrecto");
  }

  @Test
  @DisplayName("Asignar vianda inexistente")
  void testNoRoute() {
    String viandaIdTest = "viandaIdTest";
    Mockito.when(this.fachadaViandas.buscarXQR(viandaIdTest)).thenThrow(NoSuchElementException.class);
    Ruta ruta = new Ruta(9999L, 1, 2);
    RutaDTO rutaDto = this.instance
        .agregar(new RutaDTO(ruta.getColaboradorId(), ruta.getHeladeraIdOrigen(), ruta.getHeladeraIdDestino()));
    this.instance.agregar(rutaDto);
    TrasladoDTO traslado = new TrasladoDTO(viandaIdTest, ruta.getHeladeraIdOrigen(), ruta.getHeladeraIdDestino());
    Assertions.assertThrows(NoSuchElementException.class, () -> {
      this.instance.asignarTraslado(traslado);
    }, "Throw si no existe el qr");
  }

  @Test
  @DisplayName("Asignar traslado sin ruta ni colaborador")
  void noColaboradorParaRutaDisponible() {
    String qrViandaId = "prueba_id_vianda";
    TrasladoDTO traslado = new TrasladoDTO(qrViandaId, 1, 2); // recordar que no creamos ninguna ruta con colaboradorId
    Assertions.assertThrows(TrasladoNoAsignableException.class, () -> {
      this.instance.asignarTraslado(traslado);
    }, "Throw si no hay ruta asignable");
  }

  @Test
  @DisplayName("Retirar traslado")
  void retirarTraslado() throws Exception {
    String qrViandaTest = "test_vianda";
    ViandaDTO viandaDto = new ViandaDTO(qrViandaTest, LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 2L, 1);
    Mockito.when(this.fachadaViandas.buscarXQR(qrViandaTest)).thenReturn(viandaDto);
    Ruta ruta = new Ruta(99999999L, 1, 2);
    this.instance
        .agregar(new RutaDTO(ruta.getColaboradorId(), ruta.getHeladeraIdOrigen(), ruta.getHeladeraIdDestino()));
    Traslado traslado = new Traslado(viandaDto.getCodigoQR(), ruta, null, LocalDateTime.now(), ruta.getColaboradorId());
    TrasladoDTO traslado_2 = this.instance.asignarTraslado(new TrasladoDTO(qrViandaTest,
        traslado.getRuta().getHeladeraIdOrigen(), traslado.getRuta().getHeladeraIdDestino()));
    this.instance.trasladoRetirado(traslado_2.getId());
    Assertions.assertTrue(this.instance.buscarXId(traslado_2.getId()).getStatus().equals(EstadoTrasladoEnum.EN_VIAJE));
  }

  @Test
  @DisplayName("Entregar traslado")
  void entregarTraslado() throws Exception {
    String qrViandaTest = "test_vianda";
    ViandaDTO viandaDto = new ViandaDTO(qrViandaTest, LocalDateTime.now(), EstadoViandaEnum.PREPARADA, 2L, 1);
    Mockito.when(this.fachadaViandas.buscarXQR(qrViandaTest)).thenReturn(viandaDto);
    Ruta ruta = new Ruta(99999999L, 1, 2);
    this.instance
        .agregar(new RutaDTO(ruta.getColaboradorId(), ruta.getHeladeraIdOrigen(), ruta.getHeladeraIdDestino()));
    Traslado traslado = new Traslado(viandaDto.getCodigoQR(), ruta, null, LocalDateTime.now(), ruta.getColaboradorId());
    TrasladoDTO traslado_2 = this.instance.asignarTraslado(new TrasladoDTO(qrViandaTest,
        traslado.getRuta().getHeladeraIdOrigen(), traslado.getRuta().getHeladeraIdDestino()));
    this.instance.trasladoRetirado(traslado_2.getId());
    this.instance.trasladoDepositado(traslado_2.getId());
    Assertions.assertTrue(this.instance.buscarXId(traslado_2.getId()).getStatus().equals(EstadoTrasladoEnum.ENTREGADO));
  }

  public String paquete() {
    return "ar.edu.utn.dds.k3003.tests.logistica";
  }

  public Class<Fachada> clase() {
    return Fachada.class;
  }
}
