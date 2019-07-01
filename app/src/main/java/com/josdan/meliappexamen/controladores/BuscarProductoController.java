package com.josdan.meliappexamen.controladores;

import com.josdan.meliappexamen.dominio.Publicacion;
import com.josdan.meliappexamen.dominio.Vendedor;
import com.josdan.meliappexamen.servicios.IServicioExterno;
import com.josdan.meliappexamen.servicios.IServicioExternoObservador;

import java.util.List;

public class BuscarProductoController implements IServicioExternoObservador {

    private int limiteInferior = 0;
    private final int CANT_PUBLICACIONES = 10;

    private IBuscarProductoView vista;
    private IServicioExterno servicioExterno;

    public BuscarProductoController(IBuscarProductoView vista){
        this.vista = vista;
    }

    public void setServicioExterno(IServicioExterno servicioExterno){
        this.servicioExterno = servicioExterno;
        servicioExterno.setObservador(this);
    }

    public void buscarProducto(String consulta) {
        if (consulta != null) {
            servicioExterno.buscarProducto(consulta, limiteInferior, CANT_PUBLICACIONES);
            limiteInferior = limiteInferior + CANT_PUBLICACIONES;
        }
        else
            vista.mostrarError("La consulta es invalida");
    }

    @Override
    public void mostrarResultadosObtenidos(List<Publicacion> resultados) {
        if (resultados != null)
            vista.mostrarResultadosDeBusqueda(resultados);
        else
            vista.mostrarError("No se pudo obtener los resultados");
    }

    @Override
    public void mostrarPublicacion(Publicacion publicacion) {

    }

    @Override
    public void mostrarVendedor(Vendedor vendedor) {

    }

    @Override
    public void mostrarDescripcion(String descripcion) {

    }

    @Override
    public void mostrarReputacion(float parseJsonAReputacion) {

    }

    @Override
    public void mostrarCantidadDeOpiniones(int cantidadDeOpiniones) {

    }

}

