package com.josdan.meliappexamen.servicios;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.josdan.meliappexamen.tools.Parser;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiMercadoLibre implements IServicioExterno {

    private IServicioExternoObservador observador;
    private Context context;

    public ApiMercadoLibre(Context context){
        this.context = context;
    }

    @Override
    public void buscarProducto(String consulta, int limiteInferior, int cantidad) {

        String url = Urls.URL_BUSCAR.concat(consulta)
                .concat("&offset=")
                .concat(String.valueOf(limiteInferior))
                .concat("&limit=")
                .concat(String.valueOf(cantidad));

        Log.d("URL-BUSCAR", url);
        HttpRequest httpRequest = new HttpRequest(context, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("JSON-BUSCAR", response.toString());
                    observador.mostrarResultadosObtenidos(Parser.getInstance().parseJsonAListaPublicaciones(response));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ERROR", e.getMessage());
                    observador.mostrarResultadosObtenidos(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "onErrorResponse: error");
                observador.mostrarResultadosObtenidos(null);
            }
        });
            httpRequest.peticion(url);
    }

    @Override
    public void obtenerPublicacion(String idPublicacion)  {
        String url = Urls.URL_ITEM.concat(idPublicacion);

        Log.d("URL-ITEM", url);

        HttpRequest httpRequest = new HttpRequest(context, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.d("JSON-ITEM", response.toString());
                    observador.mostrarPublicacion(Parser.getInstance().parseJsonAPublicacion(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("PARSER", "ERROR");
                    observador.mostrarPublicacion(null);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                observador.mostrarPublicacion(null);
            }
        });
        httpRequest.peticion(url);

    }

    @Override
    public void obtenerVendedor(String idVendedor) {

        String url = Urls.URL_VENDEDOR.concat(idVendedor);

        Log.d("URL-VENDEDOR", url);

        HttpRequest httpRequest = new HttpRequest(context, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("JSON-VENDEDOR", response.toString());
                    observador.mostrarVendedor(Parser.getInstance().parseJsonAVendedor(response));
                } catch (JSONException e) {
                    Log.d("ERROR", "PARSER");
                    e.printStackTrace();
                    observador.mostrarVendedor(null);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                observador.mostrarVendedor(null);
            }
        });

        httpRequest.peticion(url);

    }

    @Override
    public void setObservador(IServicioExternoObservador observador) {
        this.observador = observador;
    }

    @Override
    public void obtenerReputacion(String idPublicacion) {
        String url = Urls.URL_REVIEW.concat(idPublicacion);
        Log.d("URL-REVIEW", url);

        HttpRequest httpRequest = new HttpRequest(context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("URL-REVIEW", response.toString());
                            observador.mostrarReputacion(Parser.getInstance().parseJsonAReputacion(response));
                            observador.mostrarCantidadDeOpiniones(Parser.getInstance().parseJsonACantidadOpiniones(response));
                        } catch (JSONException e) {
                            Log.d("ERROR", "parser");
                            e.printStackTrace();
                            observador.mostrarReputacion(0);
                            observador.mostrarCantidadDeOpiniones(0);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                observador.mostrarReputacion(0);
                observador.mostrarCantidadDeOpiniones(0);
            }
        });

        httpRequest.peticion(url);
    }

    @Override
    public void obtenerDescripcion(String idPublicacion) {
        String url = Urls.URL_DESCRIPCION.replace("@", idPublicacion);

        Log.d("URL_DESCRIPCION" ,url);
        HttpRequest httpRequest = new HttpRequest(context, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("JSON-DESCRIPCION", response.toString());
                    observador.mostrarDescripcion(Parser.getInstance().parseJsonADescripcion(response));
                } catch (JSONException e) {
                    Log.d("ERROR", "PARSER");
                    e.printStackTrace();
                    observador.mostrarDescripcion(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                observador.mostrarDescripcion(null);
            }
        });

        httpRequest.peticion(url);
    }

}
