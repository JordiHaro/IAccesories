package com.example.iaccesories;

public class Noticia {

    private String Nombre, Descripcion, mUid;

    public Noticia(String nombre, String descripcion, String mUid) {
        Nombre = nombre;
        Descripcion = descripcion;
        this.mUid = mUid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "Nombre='" + Nombre + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", mUid='" + mUid + '\'' +
                '}';
    }
}
