package com.josdan.meliappexamen.servicios;

import com.josdan.meliappexamen.dominio.Publicacion;
import com.josdan.meliappexamen.dominio.Vendedor;

import java.util.List;

public interface IServicioExternoObservador {

    void mostrarResultadosObtenidos(List<Publicacion> resultados);

    void mostrarPublicacion(Publicacion publicacion);

    void mostrarVendedor(Vendedor vendedor);

    void mostrarDescripcion(String descripcion);

    void mostrarReputacion(float parseJsonAReputacion);

    void mostrarCantidadDeOpiniones(int cantidadDeOpiniones);
}
