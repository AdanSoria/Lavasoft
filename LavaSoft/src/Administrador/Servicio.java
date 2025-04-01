package Administrador;

public class Servicio {
    private int idServicio;
    private String tipoServicio;
    private String descripcion;
    private float precioUnitario;
    
    // Constructor completo
    public Servicio(int idServicio, String tipoServicio, String descripcion, float precioUnitario) {
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }
    
    // Constructor mínimo (para creación)
    public Servicio(String tipoServicio, float precioUnitario) {
        this(0, tipoServicio, "", precioUnitario);
    }
    
    // Getters y Setters
    public int getIdServicio() {
        return idServicio;
    }
    
    public String getTipoServicio() {
        return tipoServicio;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public float getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return tipoServicio + " ($" + String.format("%.2f", precioUnitario) + ")";
    }
}