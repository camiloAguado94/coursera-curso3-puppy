package com.camilo.puppyaplication.pojo;

public class PerfilPOJO {

    private int imagen;
    private Integer cantidadLikes;

    public PerfilPOJO(int imagen, Integer cantidadLikes) {
        this.imagen = imagen;
        this.cantidadLikes = cantidadLikes;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Integer getCantidadLikes() {
        return cantidadLikes;
    }

    public void setCantidadLikes(Integer cantidadLikes) {
        this.cantidadLikes = cantidadLikes;
    }
}
