package com.josdan.meliappexamen.controladores;

import com.josdan.meliappexamen.dominio.Publicacion;

import java.util.List;

public interface IBuscarProductoView {

    void mostrarResultadosDeBusqueda(List<Publicacion> publicaciones);

    void mostrarError(String mensaje);
}
