package com.camilo.puppyaplication.pojo;

public class MascotaPOJO {

    private String nombre;
    private int foto;
    private Integer cantidadLikes;
    private boolean liked;

    public MascotaPOJO(String nombre, int foto, int cantidadLikes) {
        this.nombre = nombre;
        this.foto = foto;
        this.cantidadLikes = cantidadLikes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Integer getCantidadLikes() {
        return cantidadLikes;
    }

    public void setCantidadLikes(Integer cantidadLikes) {
        this.cantidadLikes = cantidadLikes;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
