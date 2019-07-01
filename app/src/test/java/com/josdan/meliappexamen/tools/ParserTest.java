package com.josdan.meliappexamen.tools;

import com.josdan.meliappexamen.dominio.Atributo;
import com.josdan.meliappexamen.dominio.Publicacion;
import com.josdan.meliappexamen.dominio.Vendedor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    Vendedor vendedorEsperado;

    @Before
    public void setUp(){
        vendedorEsperado = new Vendedor("idVendedor123");
        vendedorEsperado.setNombre("Daniel");
    }

    @Test
    public void comprobarParseJsonAVendedorConArgumentoValidosEsPositivo() {

        JSONObject object = new JSONObject();

        try {
            object.put("id", "idVendedor123");
            object.put("nickname", "Daniel");

            Vendedor vendedorResultado = Parser.getInstance().parseJsonAVendedor(object);

            assertEquals("id_vendedor", vendedorEsperado.getIdVendedor(), vendedorResultado.getIdVendedor());
            assertEquals("mensaje", vendedorEsperado.getNombre(), vendedorResultado.getNombre());

        } catch (JSONException e) {

        }
    }

    @Test
    public void comprobarParseJsonAVendedorConArgumentoInvalidoLanceUnJsonException(){

        try {

            JSONObject object = new JSONObject();

            object.put("id", "idVendedor123");
            object.put("nickname", "Daniel");

            Vendedor vendedorResultado = Parser.getInstance().parseJsonAVendedor(object);

        } catch (JSONException e) {

        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void comprobarParseJsonAAtribtoConArgumentosValidosEsPositivo() {

        JSONObject object = new JSONObject();

        try {
            object.put("name", "atributo1");
            object.put("value_name", "valor1");

            Atributo atributoResultado = Parser.getInstance().parseJsonAAtribto(object);

            String nameEsperado = "atributo1";
            String valueNameEsperado = "valor1";

            assertEquals(nameEsperado, atributoResultado.getName());
            assertEquals(valueNameEsperado, atributoResultado.getValor());

        } catch (JSONException e) {

        }

    }

    @Test
    public void comprobarParseJsonAAtribtoConArgumentosInvalidoLanceUnJsonException() {

        JSONObject object = new JSONObject();

        try {
            object.put("nameasdf", "atributo1");
            object.put("value_nameasdf", "valor1");

            Atributo atributoResultado = Parser.getInstance().parseJsonAAtribto(object);

            fail();

        } catch (JSONException e) {

        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void comprobarParseJsonADescripcionConArgumentoValidoEsPositivo() {

        try {

            JSONObject object = new JSONObject();

            object.put("plain_text", "descripcion");

            String descripcionResultado = Parser.getInstance().parseJsonADescripcion(object);

            String descripcionEsperada = "descripcion";

            assertEquals(descripcionEsperada, descripcionResultado);

        } catch (JSONException e) {

        }
    }

    @Test
    public void comprobarParseJsonADescripcionConArgumentoInvalidoLanceUnJsonException() {

        try {

            JSONObject object = new JSONObject();

            object.put("plain_textasdf", "descripcion");

            String descripcionResultado = Parser.getInstance().parseJsonADescripcion(object);

            fail();

        } catch (JSONException e) {

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void comprobarParseJsonAReputacionConArgumentoValidoEsPositivo(){

        try{
            JSONObject object = new JSONObject();

            object.put("rating_average","3.5");

            float reputacionResultado = Parser.getInstance().parseJsonAReputacion(object);

            float reputacionEsperada = 3.5f;

            assertEquals(reputacionEsperada, reputacionResultado, 1);

        } catch (JSONException e){

        }
    }

    @Test
    public void comprobarParseJsonAReputacionConArgumentoInvalidoLanceUnJsonException(){

        try{
            JSONObject object = new JSONObject();

            object.put("rating_aversdfadage","3.5");

            float reputacionResultado = Parser.getInstance().parseJsonAReputacion(object);

            fail();

        } catch (JSONException e){

        } catch (Exception e){
            fail();
        }
    }

    @Test
    public void comprobarParseJsonACantidadOpinionesConArgumentoValidoEsPositivo(){

        try{
            JSONObject object = new JSONObject();

            object.put("paging", new JSONObject().put("total", 23));

            int cantidadDeOpinionesResultado = Parser.getInstance().parseJsonACantidadOpiniones(object);

            int cantidadDeOpinionesEsperada = 23;

            assertEquals(cantidadDeOpinionesEsperada, cantidadDeOpinionesResultado);

        } catch (JSONException e){

        }
    }

    @Test
    public void comprobarParseJsonACantidadOpinionesConArgumentoInvalidoLanceUnJsonException(){

        try{
            JSONObject object = new JSONObject();

            object.put("pagingasdf", new JSONObject().put("total", 23));

            int cantidadDeOpinionesResultado = Parser.getInstance().parseJsonACantidadOpiniones(object);

            fail();

        } catch (JSONException e){

        } catch (Exception e){
            fail();
        }
    }


    @Test
    public void comprobarParseJsonAListaAtributosConArgumentoValidoEsPositivo(){

        try {

            JSONArray jsonArray = new JSONArray();

            List<Atributo> atributosEsperados = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                atributosEsperados.add(new Atributo("nombre" + i, "valor" + i));

                jsonArray.put(new JSONObject().put("name", "nombre" + i));
                jsonArray.put(new JSONObject().put("value_name", "valor" + i));

            }

            List<Atributo> atributosRespuesta = Parser.getInstance().parseJsonAListaAtributos(jsonArray);

            assertEquals(atributosEsperados, atributosRespuesta);

        } catch (JSONException e) {

        }
    }

    @Test
    public void comprobarParseJsonAListaAtributosConArgumentoInvalidoLanceUnJsonException() {

        try {

            JSONArray jsonArray = new JSONArray();


            for (int i = 0; i < 5; i++) {

                jsonArray.put(new JSONObject().put("nameasdf", "nombre" + i));
                jsonArray.put(new JSONObject().put("value_name", "valor" + i));

            }

            List<Atributo> atributosRespuesta = Parser.getInstance().parseJsonAListaAtributos(jsonArray);

            fail();


        } catch (JSONException e) {

        } catch (Exception e){
            fail();
        }
    }


    @Test
    public void comprobarJsonParseAListaPublicacionConArgumentoValidoEsPositivo(){

        try{

            List<Publicacion> publicacionesEsperadas = new ArrayList<>();

            JSONObject object = new JSONObject();

            JSONArray jsonArray = new JSONArray();

            for (int i = 0; i < 5; i++){

                publicacionesEsperadas.add(new Publicacion("id"+i, "titulo" +i, 1.1f+i, "condicio"+i, "url"+i));

                object.put("id", "id"+i);
                object.put("title", "title"+i);
                object.put("price", "price"+i);
                object.put("thumbnail", "url");
                object.put("condition", "condition"+i);

                jsonArray.put(object);

            }

            JSONObject object1 = new JSONObject();
            object1.put("results", jsonArray);

            List<Publicacion> publicacionesRespuesta = Parser.getInstance().parseJsonAListaPublicaciones(object1);

            assertTrue(publicacionesEsperadas.equals(publicacionesRespuesta));

        } catch (JSONException e){

        }

    }



    @Test
    public void comprobarJsonParseAListaPublicacionConArgumentoInvalidoLanceUnJsonException(){

        try{

            List<Publicacion> publicacionesEsperadas = new ArrayList<>();

            JSONObject object = new JSONObject();

            JSONArray jsonArray = new JSONArray();

            for (int i = 0; i < 5; i++){

                publicacionesEsperadas.add(new Publicacion("id"+i, "titulo" +i, 1.1f+i, "condicio"+i, "url"+i));

                object.put("id", "id"+i);
                object.put("title", "title"+i);
                object.put("price", "price"+i);
                object.put("thumbnail", "url");
                object.put("condition", "condition"+i);

                jsonArray.put(object);

            }

            JSONObject object1 = new JSONObject();
            object1.put("results", jsonArray);


            List<Publicacion> publicacionesRespuesta = Parser.getInstance().parseJsonAListaPublicaciones(object1);

            fail();

        } catch (JSONException e){

        } catch (Exception e){
            fail();
        }

    }

    @Test
    public void comprobarParseJsonToListaDeImagenesConArgumentoValidoEsPositivo(){

        try {

            List<String> urlsImagenesEsperadas = new ArrayList<>();

            JSONArray jsonArray = new JSONArray();

            for (int i = 0; i < 5; i++){

                urlsImagenesEsperadas.add("valorUrl"+i);
                jsonArray.put(new JSONObject().put("url", "valorUrl" + i));
            }

            List<String> urlsImagenesResultado = Parser.getInstance().parseJsonToListaDeImagenes(jsonArray);

            for (int i = 0; i < urlsImagenesEsperadas.size() ; i++){
                assertEquals(urlsImagenesEsperadas.get(i), urlsImagenesResultado.get(i));
            }

        } catch (JSONException e){

        }
    }


    @Test
    public void comprobarParseJsonToListaDeImagenesConArgumentoInvalidoLanceUnJsonException() {

        try {


            JSONArray jsonArray = new JSONArray();

            for (int i = 0; i < 5; i++){

                jsonArray.put(new JSONObject().put("sadfurl", "valorUrl" + i));
            }

            List<String> urlsImagenesResultado = Parser.getInstance().parseJsonToListaDeImagenes(jsonArray);

            fail();

        } catch (JSONException e){

        } catch (Exception e){
            fail();
        }

    }


    @Test
    public void comprobarParseJsonAPublicacionConArgumentoInvalidoLanceUnJsonException(){

        try{

            JSONObject object = new JSONObject();

            Publicacion publicacionRespuesta = Parser.getInstance().parseJsonAPublicacion(object);

            fail();

        } catch (JSONException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            fail();
        }
    }

}