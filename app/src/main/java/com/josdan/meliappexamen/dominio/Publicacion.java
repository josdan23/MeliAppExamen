package com.josdan.meliappexamen.dominio;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Publicacion {

    private String idPublicacion;
    private String titulo;
    private String imagen;
    private float precio;
    private float precioBase;
    private String descripcion;
    private float reputacion;
    private int cantidadDeOpiniones;
    private int cantidadVendida;
    private List<Atributo> atributos;
    private Condicion condicion;
    private Date fechaPublicacion;
    private List<String> urlsDeImagenes;
    private Vendedor vendedor;

    public Publicacion(String idPublicacion, String titulo, float precioBase, String condicion, String imagen){

        this.idPublicacion = idPublicacion;
        setTitulo(titulo);
        setPrecioBase(precioBase);
        setCondicion(condicion);
        setimagen(imagen);

        atributos = new ArrayList<>();

        setPrecio(0);
        setReputacion(0);
        setCantidadDeOpiniones(0);
        setCantidadVendida(0);
    }

    //SETERS

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPrecio(float precio) {
        if (precio <= 0)
            this.precio = 0;
        else
            this.precio = precio;
    }

    public void setPrecioBase(float precioBase) {
        if (precioBase <= 0)
            this.precioBase = 0;
        else
            this.precioBase = precioBase;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setReputacion(float reputacion) {
        if (reputacion <= 0)
            this.reputacion = 0;
        else
            this.reputacion = reputacion;
    }

    public void setCantidadDeOpiniones(int cantidadDeOpiniones) {
        if (cantidadDeOpiniones <= 0)
            this.cantidadDeOpiniones = 0;
        else
            this.cantidadDeOpiniones = cantidadDeOpiniones;
    }

    public void setCantidadVendida(int cantidadVendida) {
        if (cantidadVendida <= 0)
            this.cantidadVendida = 0;
        else
            this.cantidadVendida = cantidadVendida;
    }

    public void setimagen(String imagen) {
        this.imagen = imagen;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void setIdPublicacion(String idPublicacion) {
        this.idPublicacion = idPublicacion;
    }


    //GETERS

    public String getIdPublicacion() {
        return idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public float getPrecio() {
        return precio;
    }

    public float getPrecioBase() {
        return precioBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getReputacion() {
        return reputacion;
    }

    public int getCantidadDeOpiniones() {
        return cantidadDeOpiniones;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Condicion getCondicion() {
        return condicion;
    }


    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getFechaPublicacionString(){
        if (fechaPublicacion != null){
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.format(fechaPublicacion);
        }
        return null;
    }

    public void establecerFechaPublicacion(String fecha){

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");

            try {
                Date fechaPublicacion = formato.parse(fecha);

                setFechaPublicacion(fechaPublicacion);
            } catch (ParseException e) {
                setFechaPublicacion(null);
            } catch (NullPointerException e){
                setFechaPublicacion(null);
            }
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    public void agregarVendedor(String idVendedor){
        this.vendedor = new Vendedor(idVendedor);
    }

    public int obtenerDescuento(){
        if (precioBase != 0 ){
            int porcentaje = Math.round((precioBase - precio)*100/precioBase) ;

            if (porcentaje < 0)
                return 0;
            else
                return porcentaje;
        }
        return 0;
    }


    public void setCondicion(String condicion){

        switch (condicion.toLowerCase()){
            case "new": setCondicion(Condicion.NUEVO);
                break;
            case "used" : setCondicion(Condicion.USADO);
                break;
            default: setCondicion(Condicion.NO_ESPECIFICADO);
        }
    }


    public List<String> getUrlsDeImagenes() {
        return urlsDeImagenes;
    }

    public void setUrlsDeImagenes(List<String> urlsDeImagenes) {
        this.urlsDeImagenes = urlsDeImagenes;
    }
}
