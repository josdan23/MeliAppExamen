package com.josdan.meliappexamen.controladores;

import com.josdan.meliappexamen.servicios.IServicioExterno;
import com.josdan.meliappexamen.servicios.IServicioExternoObservador;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarProductoControllerTest {

    @Mock
    IServicioExterno servicioExternoMock;


    BuscarProductoController controller;

    @Mock
    IBuscarProductoView vistaMock;

    IServicioExternoObservador observador;

    @Before
    public void setUp(){
        controller = new BuscarProductoController(vistaMock);
        controller.setServicioExterno(servicioExternoMock);

    }

    @Test
    public void comprobarBuscarProductoConArgumentoNuloDebeLlamarAVistaError() {

        controller.buscarProducto(null);

        Mockito.verify(vistaMock).mostrarError("La consulta es invalida");
    }

    @Test
    public void comprobarMostrarResultadosObtenidosConArgumengoNuloDebeLanzarVistaError(){

        controller.mostrarResultadosObtenidos(null);

        Mockito.verify(vistaMock).mostrarError("No se pudo obtener los resultados");
    }

}