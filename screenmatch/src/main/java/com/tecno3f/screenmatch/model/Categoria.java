package com.tecno3f.screenmatch.model;

public enum Categoria {
    ACCION("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIMEN("Crime");

    private String categoriaImdb;

    Categoria(String categoriaImdb){
        this.categoriaImdb = categoriaImdb;
    }
    public static Categoria fromString(String text){
        for( Categoria categoria: Categoria.values()){
            if( categoria.categoriaImdb.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw  new IllegalArgumentException( "Ninguna categoria es correcta "+ text);
    }
}
