package com.tecno3f.screenmatch.model;

public enum Categoria {
    ACCION("Action","accion"),
    ROMANCE("Romance","romance"),
    COMEDIA("Comedy","comedia"),
    DRAMA("Drama","drama"),
    CRIMEN("Crime","crimen");

    private String categoriaImdb;
    private String categoriaEspanol;

    Categoria(String categoriaImdb,String categoriaEspanol){
        this.categoriaImdb = categoriaImdb;
        this.categoriaEspanol = categoriaEspanol;
    }
    public static Categoria fromString(String text){
        for( Categoria categoria: Categoria.values()){
            if( categoria.categoriaImdb.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw  new IllegalArgumentException( "Ninguna categoria es correcta "+ text);
    }
    public static Categoria fromEspanol(String text){
        for( Categoria categoria: Categoria.values()){
            if( categoria.categoriaEspanol.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw  new IllegalArgumentException( "Ninguna categoria es correcta "+ text);
    }
}
