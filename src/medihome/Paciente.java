package medihome;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Paciente que recibe atención médica en su domicilio.
 */
public class Paciente extends Persona {
    private LocalDate fechaNacimiento;
    private String direccion;
    private String eps;
    private List<String> alergias;
    private List<Atencion> historialAtenciones;

    public Paciente() {
        super();
        this.alergias = new ArrayList<>();
        this.historialAtenciones = new ArrayList<>();
    }

    public Paciente(String documento, String nombres, String apellidos, String telefono,
                    LocalDate fechaNacimiento, String direccion, String eps) {
        super(documento, nombres, apellidos, telefono);
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.eps = eps;
        this.alergias = new ArrayList<>();
        this.historialAtenciones = new ArrayList<>();
    }

    public int calcularEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public void agregarAlergia(String alergia) {
        alergias.add(alergia);
    }

    public void agregarAtencion(Atencion atencion) {
        if (atencion != null && !historialAtenciones.contains(atencion)) {
            historialAtenciones.add(atencion);
        }
    }

    void quitarAtencion(Atencion atencion) {
        historialAtenciones.remove(atencion);
    }

    // ---------- Getters y Setters ----------

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public List<Atencion> getHistorialAtenciones() {
        return historialAtenciones;
    }

    public void setHistorialAtenciones(List<Atencion> historialAtenciones) {
        this.historialAtenciones = historialAtenciones;
    }
}
