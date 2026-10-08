package medihome;

/**
 * Clase abstracta que agrupa los datos comunes de las personas del sistema.
 */
public abstract class Persona {
    private String documento;
    private String nombres;
    private String apellidos;
    private String telefono;

    public Persona() {
    }

    public Persona(String documento, String nombres, String apellidos, String telefono) {
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    // ---------- Getters y Setters ----------

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
