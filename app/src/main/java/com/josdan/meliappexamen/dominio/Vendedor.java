package com.josdan.meliappexamen.dominio;

public class Vendedor {

    private String idVendedor;
    private String nombre;

    public Vendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
