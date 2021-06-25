package p2;

public class ProductoBD{
    private int id;
    private String nombre;
    private String descripcion;
    private int pokedex;
    private float precio;
    private int stock;
    private String imagen;
    private int evolucion;
    private int iv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPokedex() {
        return pokedex;
    }

    public void setPokedex(int pokedex) {
        this.pokedex = pokedex;
    }

    public int getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(int evolucion) {
        this.evolucion = evolucion;
    }

    public int getIv() {
        return iv;
    }

    public void setIv(int iv) {
        this.iv = iv;
    }
    
}

