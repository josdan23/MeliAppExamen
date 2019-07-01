package com.josdan.meliappexamen.controladores;

import java.util.List;
import java.util.Map;

public interface IDetalleDeProductoView {

    void mostrarImagenes(List<String> urlsImagenes);

    void mostrarTitulo (String titulo);

    void mostrarPrecio(float precio);

    void mostrarFechaDePublicacion(String date);

    void mostrarNombreDeVendedor(String nombre);

    void mostrarDescripcion(String descripcion);

    void mostrarCantidadDeOpiniones(int cantidad);

    void mostrarCantidadVendida(int cantidad);

    void mostrarAtributos(Map<String, String> atributos);

    void mostrarCondicionDeLaPublicacion(String condicion);

    void mostrarReputacion(float reputacion);

    void mostraDescuento(int descuento);

    void mostrarError(String mensaje);
}
