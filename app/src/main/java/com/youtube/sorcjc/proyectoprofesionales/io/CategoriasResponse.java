package com.youtube.sorcjc.proyectoprofesionales.io;

import com.google.gson.annotations.SerializedName;
import com.youtube.sorcjc.proyectoprofesionales.domain.Category;

import java.util.ArrayList;

public class CategoriasResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("error")
    private String error;

    @SerializedName("response")
    private ArrayList<Category> response;

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public ArrayList<Category> getResponse() {
        return response;
    }

}
