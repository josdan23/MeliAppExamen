package com.josdan.meliappexamen.controladores;

import com.josdan.meliappexamen.dominio.Atributo;
import com.josdan.meliappexamen.dominio.Publicacion;
import com.josdan.meliappexamen.dominio.Vendedor;
import com.josdan.meliappexamen.servicios.IServicioExterno;
import com.josdan.meliappexamen.servicios.IServicioExternoObservador;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DetalleDeProductoController implements IServicioExternoObservador {

    private IDetalleDeProductoView vista;
    private IServicioExterno servicioExterno;

    public DetalleDeProductoController(IDetalleDeProductoView vista){
        this.vista = vista;
    }

    public void setServicioExterno(IServicioExterno servicioExterno){
        this.servicioExterno = servicioExterno;
        servicioExterno.setObservador(this);
    }

    public void verDetalleDePublicacion(String idPublicacion){

        if (idPublicacion != null)
            servicioExterno.obtenerPublicacion(idPublicacion);
        else
            vista.mostrarError("No se pudo obtener el detalle");
    }


    @Override
    public void mostrarPublicacion(Publicacion publicacion) {

        if (publicacion != null) {

            vista.mostrarTitulo(publicacion.getTitulo());
            vista.mostrarPrecio(publicacion.getPrecioBase());
            vista.mostrarDescripcion(publicacion.getDescripcion());
            vista.mostrarCondicionDeLaPublicacion(publicacion.getCondicion().toString());
            vista.mostrarFechaDePublicacion(publicacion.getFechaPublicacionString());
            vista.mostrarImagenes(publicacion.getUrlsDeImagenes());

            Map<String, String> atributosMap = new LinkedHashMap<>();
            for (Atributo atributo : publicacion.getAtributos()) {
                atributosMap.put(atributo.getName(), atributo.getValor());
            }

            vista.mostrarAtributos(atributosMap);
            vista.mostrarCantidadVendida(publicacion.getCantidadVendida());
            vista.mostraDescuento(publicacion.obtenerDescuento());
            servicioExterno.obtenerVendedor(publicacion.getVendedor().getIdVendedor());
            servicioExterno.obtenerDescripcion(publicacion.getIdPublicacion());
            servicioExterno.obtenerReputacion(publicacion.getIdPublicacion());

        }
        else
            vista.mostrarError("No se pudo obtener la publicacion");
    }

    @Override
    public void mostrarVendedor(Vendedor vendedor) {
        if (vendedor != null)
            vista.mostrarNombreDeVendedor(vendedor.getNombre());
        else
            vista.mostrarError("No se pudo obtener el vendedor");
    }

    @Override
    public void mostrarDescripcion(String descripcion) {
        if (descripcion != null)
            vista.mostrarDescripcion(descripcion);
        else
            vista.mostrarError("No se pudo obtener la descripción");
    }

    @Override
    public void mostrarReputacion(float reputacion) {

        vista.mostrarReputacion(reputacion);
    }

    @Override
    public void mostrarCantidadDeOpiniones(int cantidadDeOpiniones) {
        vista.mostrarCantidadDeOpiniones(cantidadDeOpiniones);
    }

    @Override
    public void mostrarResultadosObtenidos(List<Publicacion> resultados) {
    }


}
