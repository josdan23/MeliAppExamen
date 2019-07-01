package com.josdan.meliappexamen.tools;

import com.josdan.meliappexamen.dominio.Atributo;
import com.josdan.meliappexamen.dominio.Publicacion;
import com.josdan.meliappexamen.dominio.Vendedor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static Parser instance;

    public static Parser getInstance(){
        if (instance == null)
            instance = new Parser();

        return instance;
    }

    public Vendedor parseJsonAVendedor(JSONObject object) throws JSONException, NullPointerException {

        Vendedor vendedor = new Vendedor(object.getString("id"));
        vendedor.setNombre(object.getString("nickname"));
        return vendedor;
    }

    public Atributo parseJsonAAtribto(JSONObject object) throws JSONException {
        Atributo atributo = new Atributo(
                object.getString("name"),
                object.getString("value_name"));
        return atributo;
    }

    public Publicacion parseJsonAPublicacion(JSONObject object) throws JSONException {

        String idPublicacion = object.getString("id");
        String titulo = object.getString("title");
        String urlImagen = object.getString("thumbnail");
        float precio = (float) object.getDouble("price");
        float precioBase = (float) object.getDouble("base_price");
        int cantidadVendida = object.getInt("sold_quantity");
        String condicion = object.getString("condition");
        String fechaPublicacion = object.getString("date_created");

        List<Atributo> atributos = parseJsonAListaAtributos(object.getJSONArray("attributes"));

        List<String> listaUrlsDeImagenes = Parser.getInstance().
                parseJsonToListaDeImagenes(object.getJSONArray("pictures"));

        String idVendedor = object.getString("seller_id");

        Publicacion publicacion = new Publicacion(idPublicacion, titulo, precioBase, condicion, urlImagen);
        publicacion.setPrecio(precio);
        publicacion.setCantidadVendida(cantidadVendida);
        publicacion.setAtributos(atributos);
        publicacion.establecerFechaPublicacion(fechaPublicacion);
        publicacion.setUrlsDeImagenes(listaUrlsDeImagenes);
        publicacion.agregarVendedor(idVendedor);

        return publicacion;
    }

    public String parseJsonADescripcion(JSONObject object) throws JSONException {
        return object.getString("plain_text");
    }

    public float parseJsonAReputacion(JSONObject object) throws JSONException {
        return (float) object.getDouble("rating_average");
    }

    public int parseJsonACantidadOpiniones(JSONObject object) throws JSONException {
        return object.getJSONObject("paging").getInt("total");
    }

    public List<Atributo> parseJsonAListaAtributos(JSONArray jsonArray) throws JSONException {
        List<Atributo> atributos = new ArrayList<>();

        for (int i = 0; i< jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            atributos.add(parseJsonAAtribto(object));
        }

        return atributos;
    }


    public List<Publicacion> parseJsonAListaPublicaciones(JSONObject jsonObject) throws JSONException {

        JSONArray jsonArray = jsonObject.getJSONArray("results");

        List<Publicacion> publicaciones = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject object  = jsonArray.getJSONObject(i);

            String idPublicacion = object.getString("id");
            String title = object.getString("title");
            float precioBase = (float) object.getDouble("price");
            String urlImagen = object.getString("thumbnail");
            String condicion = object.getString("condition");

            Publicacion publicacion = new Publicacion(
                    idPublicacion,
                    title,
                    precioBase,
                    condicion,
                    urlImagen
            );

            publicaciones.add(publicacion);
        }

        return publicaciones;
    }

    public List<String> parseJsonToListaDeImagenes(JSONArray jsonArray) throws JSONException {

        List<String> listaImagenes = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject object = jsonArray.getJSONObject(i);
            listaImagenes.add(object.getString("url"));
        }

        return listaImagenes;

    }


}
