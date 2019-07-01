package com.josdan.meliappexamen.dominio;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class PublicacionTest {

    Publicacion publicacion;

    @Before
    public void setUp(){
        publicacion = new Publicacion("id", "Galaxy S10", 51000.00f, "new", "url");
    }

    @Test
    public void comprobarGetFechaPublicacionStringRetornaFechaValidaEnFormato() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 0, 10);
        publicacion.setFechaPublicacion(calendar.getTime());

        String fechaResultado = publicacion.getFechaPublicacionString();
        String fechaCorrecta = "10-01-2019";

        assertEquals(fechaCorrecta, fechaResultado);
    }

    @Test
    public void comprobarMetodoGetFechaPublicacionStringRetornaFechaEnFormatoDd_MM_yyyy(){

        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 0, 10);
        publicacion.setFechaPublicacion(calendar.getTime());

        String fechaResultado = publicacion.getFechaPublicacionString();

        String fechaIncorrecta = "2019-01-10";

        assertNotEquals(fechaIncorrecta, fechaResultado);

    }

    @Test
    public void comprobarMetodoGetFechaPublicacionStringRetornaNullSiNoHayFechaEstablecidaEnPublicacion(){

        String resultado = publicacion.getFechaPublicacionString();

        String fechaNull = null;

        assertEquals(fechaNull, resultado);
    }

    @Test
    public void comprobarSetFechaPublicacionConArgumentoValido() {

        publicacion.establecerFechaPublicacion("2011-11-02T02:50:12.208Z");

        String resultado = publicacion.getFechaPublicacionString();

        String esperado = "02-11-2011";

        assertEquals(esperado, resultado);
    }

    @Test
    public void comprobarSetFechaPublicacionConArgumentoInvalidoRetornaNull(){

        publicacion.establecerFechaPublicacion("asdfasdfasdf");

        String resultado = publicacion.getFechaPublicacionString();

        String esperado = null;

        assertEquals(esperado, resultado);
    }

    @Test
    public void comprobarSetFechaPublicacionConArgumentoNulo(){
        publicacion.establecerFechaPublicacion(null);

        String resultado = publicacion.getFechaPublicacionString();

        String esperado = null;

        assertEquals(esperado, resultado);
    }

    @Test
    public void comprobarObtenerDescuentoRetornoValorValido() {

        publicacion.setPrecio(4500.00f);
        publicacion.setPrecioBase(5000.00f);

        int resultado = publicacion.obtenerDescuento();

        int esperado = 10;

        assertEquals(esperado, resultado);

    }

    @Test
    public void comprobarObtenerDescuentoRetornaCeroSiElPorcentajeEsNegativo(){

        publicacion.setPrecio(5000.00f);
        publicacion.setPrecioBase(4500.00f);

        int resultado = publicacion.obtenerDescuento();

        int esperado = 0;

        assertEquals(esperado, resultado);
    }

    @Test
    public void comprobarObtenerDescuentoRetornaCeroSiPrecioBaseEsIgualACero(){
        publicacion.setPrecio(4500.00f);
        publicacion.setPrecioBase(0);

        int resultado = publicacion.obtenerDescuento();

        int esperado = 0;

        assertEquals(esperado, resultado);
    }

    @Test
    public void comprobarSetCondicionConArgumentoInvalidoAsignaCondicionNoEspecificado(){

        publicacion.setCondicion("jasldkfj");

        Condicion esperado = Condicion.NO_ESPECIFICADO;

        Condicion resultado = publicacion.getCondicion();

        assertEquals(esperado, resultado);
    }


    @Test
    public void comprobarSetCondicionConArgumentoValidoPeroEnMayusculaEsPositivo(){
        publicacion.setCondicion("NEW");

        Condicion esperado = Condicion.NUEVO;

        Condicion resultado = publicacion.getCondicion();

        assertEquals(esperado, resultado);
    }

}