package medihome;

/**
 * Servicio que ofrece MediHome en el domicilio del paciente.
 */
public class ServicioDomiciliario {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double tarifa;
    private int duracionMinutos;

    public ServicioDomiciliario() {
    }

    public ServicioDomiciliario(String codigo, String nombre, String descripcion,
                                double tarifa, int duracionMinutos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifa = tarifa;
        this.duracionMinutos = duracionMinutos;
    }

    // ---------- Getters y Setters ----------

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
}
