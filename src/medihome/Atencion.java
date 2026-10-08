package medihome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Atención domiciliaria: une al paciente, al profesional y al servicio prestado,
 * y contiene las mediciones de signos vitales tomadas en la visita.
 */
public class Atencion {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private int numero;
    private LocalDateTime fechaHora;
    private Paciente paciente;
    private Profesional profesional;
    private ServicioDomiciliario servicio;
    private String motivoConsulta;
    private String diagnostico;
    private String observaciones;
    private EstadoAtencion estado;
    private List<MedicionSignosVitales> mediciones;

    public Atencion() {
        this.estado = EstadoAtencion.PROGRAMADA;
        this.mediciones = new ArrayList<>();
    }

    public Atencion(int numero, LocalDateTime fechaHora, Paciente paciente,
                    Profesional profesional, ServicioDomiciliario servicio, String motivoConsulta) {
        this.numero = numero;
        this.fechaHora = fechaHora;
        setPaciente(paciente);
        this.profesional = profesional;
        this.servicio = servicio;
        this.motivoConsulta = motivoConsulta;
        this.estado = EstadoAtencion.PROGRAMADA;
        this.mediciones = new ArrayList<>();
    }

    public void iniciar() {
        estado = EstadoAtencion.EN_CURSO;
        profesional.setDisponible(false);
    }

    public void registrarMedicion(MedicionSignosVitales medicion) {
        mediciones.add(medicion);
    }

    public void finalizar(String diagnostico, String observaciones) {
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
        this.estado = EstadoAtencion.FINALIZADA;
        profesional.setDisponible(true);
    }

    public double calcularValorTotal() {
        return servicio.getTarifa();
    }

    public String generarReporte() {
        String linea = "=".repeat(60) + "\n";
        String sub = "-".repeat(60) + "\n";
        StringBuilder sb = new StringBuilder();

        sb.append(linea);
        sb.append("        MEDIHOME - REPORTE DE ATENCIÓN DOMICILIARIA\n");
        sb.append(linea);
        sb.append(String.format("Atención N°: %d%n", numero));
        sb.append(String.format("Fecha y hora: %s%n", fechaHora.format(FORMATO)));
        sb.append(String.format("Estado: %s%n", estado));

        sb.append(sub).append("DATOS DEL PACIENTE\n").append(sub);
        sb.append(String.format("Nombre: %s%n", paciente.getNombreCompleto()));
        sb.append(String.format("Documento: %s%n", paciente.getDocumento()));
        sb.append(String.format("Edad: %d años%n", paciente.calcularEdad()));
        sb.append(String.format("Dirección: %s%n", paciente.getDireccion()));
        sb.append(String.format("Teléfono: %s%n", paciente.getTelefono()));
        sb.append(String.format("EPS: %s%n", paciente.getEps()));
        sb.append(String.format("Alergias: %s%n",
                paciente.getAlergias().isEmpty() ? "Ninguna" : String.join(", ", paciente.getAlergias())));

        sb.append(sub).append("PROFESIONAL\n").append(sub);
        sb.append(String.format("Nombre: %s%n", profesional.getNombreCompleto()));
        sb.append(String.format("Especialidad: %s%n", profesional.getEspecialidad()));
        sb.append(String.format("Registro profesional: %s%n", profesional.getRegistroProfesional()));

        sb.append(sub).append("SERVICIO DOMICILIARIO\n").append(sub);
        sb.append(String.format("Código: %s%n", servicio.getCodigo()));
        sb.append(String.format("Servicio: %s%n", servicio.getNombre()));
        sb.append(String.format("Descripción: %s%n", servicio.getDescripcion()));
        sb.append(String.format("Duración estimada: %d minutos%n", servicio.getDuracionMinutos()));

        sb.append(sub).append("SIGNOS VITALES\n").append(sub);
        if (mediciones.isEmpty()) {
            sb.append("No se registraron mediciones.\n");
        }
        for (MedicionSignosVitales m : mediciones) {
            sb.append(String.format("Medición %s%n", m.getFechaHora().format(FORMATO)));
            sb.append(String.format("  Presión arterial:        %s%n", m.getPresionArterial()));
            sb.append(String.format("  Frecuencia cardiaca:     %d lpm%n", m.getFrecuenciaCardiaca()));
            sb.append(String.format("  Frecuencia respiratoria: %d rpm%n", m.getFrecuenciaRespiratoria()));
            sb.append(String.format("  Temperatura:             %.1f °C%n", m.getTemperatura()));
            sb.append(String.format("  Saturación de oxígeno:   %d %%%n", m.getSaturacionOxigeno()));
            List<String> alertas = m.evaluarAlertas();
            sb.append(String.format("  Resultado: %s%n",
                    alertas.isEmpty() ? "Dentro de rangos normales" : "ALERTA -> " + String.join(", ", alertas)));
        }

        sb.append(sub).append("RESULTADO DE LA ATENCIÓN\n").append(sub);
        sb.append(String.format("Motivo de consulta: %s%n", motivoConsulta));
        sb.append(String.format("Diagnóstico: %s%n", diagnostico == null ? "Pendiente" : diagnostico));
        sb.append(String.format("Observaciones: %s%n", observaciones == null ? "N/A" : observaciones));

        sb.append(sub);
        sb.append(String.format("VALOR TOTAL: $%,.0f COP%n", calcularValorTotal()));
        sb.append(linea);
        return sb.toString();
    }

    // ---------- Getters y Setters ----------

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        if (this.paciente == paciente) {
            return;
        }
        if (this.paciente != null) {
            this.paciente.quitarAtencion(this);
        }
        this.paciente = paciente;
        if (paciente != null) {
            paciente.agregarAtencion(this);
        }
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public ServicioDomiciliario getServicio() {
        return servicio;
    }

    public void setServicio(ServicioDomiciliario servicio) {
        this.servicio = servicio;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public EstadoAtencion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtencion estado) {
        this.estado = estado;
    }

    public List<MedicionSignosVitales> getMediciones() {
        return mediciones;
    }

    public void setMediciones(List<MedicionSignosVitales> mediciones) {
        this.mediciones = mediciones;
    }
}
