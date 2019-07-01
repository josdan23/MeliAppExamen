package com.josdan.meliappexamen.servicios;

public interface IServicioExterno {

    void buscarProducto(String consulta, int limiteInferior, int cantidad);

    void obtenerPublicacion(String idPublicacion);

    void obtenerVendedor(String idVendedor);

    void setObservador(IServicioExternoObservador observador);

    void obtenerReputacion(String idPublicacion);

    void obtenerDescripcion(String idPublicacion);
}
