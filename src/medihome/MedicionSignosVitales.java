package medihome;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Registro de signos vitales tomados durante una atención.
 */
public class MedicionSignosVitales {
    private LocalDateTime fechaHora;
    private int presionSistolica;       // mmHg
    private int presionDiastolica;      // mmHg
    private int frecuenciaCardiaca;     // lpm
    private int frecuenciaRespiratoria; // rpm
    private double temperatura;         // °C
    private int saturacionOxigeno;      // %

    public MedicionSignosVitales() {
    }

    public MedicionSignosVitales(LocalDateTime fechaHora, int presionSistolica, int presionDiastolica,
                                 int frecuenciaCardiaca, int frecuenciaRespiratoria,
                                 double temperatura, int saturacionOxigeno) {
        this.fechaHora = fechaHora;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.temperatura = temperatura;
        this.saturacionOxigeno = saturacionOxigeno;
    }

    /** Devuelve las alertas encontradas según rangos de referencia en adultos. */
    public List<String> evaluarAlertas() {
        List<String> alertas = new ArrayList<>();
        if (presionSistolica >= 140 || presionDiastolica >= 90)
            alertas.add("Presión arterial elevada");
        if (presionSistolica < 90 || presionDiastolica < 60)
            alertas.add("Presión arterial baja");
        if (frecuenciaCardiaca > 100) alertas.add("Taquicardia");
        if (frecuenciaCardiaca < 60) alertas.add("Bradicardia");
        if (frecuenciaRespiratoria > 20 || frecuenciaRespiratoria < 12)
            alertas.add("Frecuencia respiratoria fuera de rango");
        if (temperatura >= 38.0) alertas.add("Fiebre");
        if (temperatura < 35.0) alertas.add("Hipotermia");
        if (saturacionOxigeno < 92) alertas.add("Saturación de oxígeno baja");
        return alertas;
    }

    public boolean esNormal() {
        return evaluarAlertas().isEmpty();
    }

    public String getPresionArterial() {
        return presionSistolica + "/" + presionDiastolica + " mmHg";
    }

    // ---------- Getters y Setters ----------

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(int presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(int saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }
}
