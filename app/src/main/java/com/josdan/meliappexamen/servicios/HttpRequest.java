package com.josdan.meliappexamen.servicios;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class HttpRequest {

    private Context context;
    private Response.Listener<JSONObject> responseListener;
    private Response.ErrorListener errorListener;

    HttpRequest(Context context, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener){
        this.context = context;
        this.responseListener = responseListener;
        this.errorListener = errorListener;
    }

    public void peticion(String url){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                responseListener,
                errorListener);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }
}
