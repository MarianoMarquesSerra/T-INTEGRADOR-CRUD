package model;

public class Vinilos {
    private int idvinilo;
    private String nombre;
    private String autor;
    private int cantCanciones;
    private double precio;
    private int copias;

    public Vinilos(int idvinilo, String nombre, String autor, int cantCanciones, double precio, int copias) {
        this.idvinilo = idvinilo;
        this.nombre = nombre;
        this.autor = autor;
        this.cantCanciones = cantCanciones;
        this.precio = precio;
        this.copias = copias;
    }

    public Vinilos(String nombre, String autor, int cantCanciones, double precio, int copias) {
        this.nombre = nombre;
        this.autor = autor;
        this.cantCanciones = cantCanciones;
        this.precio = precio;
        this.copias = copias;
    }

    public int getIdvinilo() {
        return idvinilo;
    }

    public void setIdvinilo(int idlibro) {
        this.idvinilo = idvinilo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCantCanciones() {
        return cantCanciones;
    }

    public void setCantCanciones(int cantCanciones) {
        this.cantCanciones = cantCanciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    @Override
    public String toString() {
        return "Vinilo{" + "nombre=" + nombre + ", autor=" + autor + ", cantCanciones=" + cantCanciones + ", precio=" + precio + ", copias=" + copias + '}';
    }
}
