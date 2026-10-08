package medihome;

/**
 * Profesional de la salud (médico, enfermero, terapeuta) que presta el servicio.
 */
public class Profesional extends Persona {
    private String registroProfesional;
    private String especialidad;
    private boolean disponible;

    public Profesional() {
        super();
        this.disponible = true;
    }

    public Profesional(String documento, String nombres, String apellidos, String telefono,
                       String registroProfesional, String especialidad) {
        super(documento, nombres, apellidos, telefono);
        this.registroProfesional = registroProfesional;
        this.especialidad = especialidad;
        this.disponible = true;
    }

    // ---------- Getters y Setters ----------

    public String getRegistroProfesional() {
        return registroProfesional;
    }

    public void setRegistroProfesional(String registroProfesional) {
        this.registroProfesional = registroProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
