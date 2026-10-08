package medihome;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // 1. Paciente (creado con setters)
        Paciente paciente = new Paciente();
        paciente.setDocumento("1085123456");
        paciente.setNombres("María Fernanda");
        paciente.setApellidos("Rosero Burbano");
        paciente.setTelefono("3104567890");
        paciente.setFechaNacimiento(LocalDate.of(1958, 3, 14));
        paciente.setDireccion("Calle 18 # 25-40, Barrio Las Cuadras, Pasto");
        paciente.setEps("Emssanar");
        paciente.agregarAlergia("Penicilina");

        // 2. Profesional
        Profesional profesional = new Profesional();
        profesional.setDocumento("1085987654");
        profesional.setNombres("Carlos Andrés");
        profesional.setApellidos("Muñoz Erazo");
        profesional.setTelefono("3201234567");
        profesional.setRegistroProfesional("RM-52-04567");
        profesional.setEspecialidad("Medicina General");

        // 3. Servicio domiciliario
        ServicioDomiciliario servicio = new ServicioDomiciliario();
        servicio.setCodigo("SD-001");
        servicio.setNombre("Consulta médica domiciliaria");
        servicio.setDescripcion("Valoración médica general y control de signos vitales en casa");
        servicio.setTarifa(85000);
        servicio.setDuracionMinutos(45);

        // 4. Atención
        Atencion atencion = new Atencion();
        atencion.setNumero(1001);
        atencion.setFechaHora(LocalDateTime.of(2026, 10, 7, 9, 30));
        atencion.setPaciente(paciente);
        atencion.setProfesional(profesional);
        atencion.setServicio(servicio);
        atencion.setMotivoConsulta("Control de hipertensión arterial");
        atencion.iniciar();

        // 5. Medición de signos vitales
        MedicionSignosVitales medicion = new MedicionSignosVitales();
        medicion.setFechaHora(LocalDateTime.of(2026, 10, 7, 9, 40));
        medicion.setPresionSistolica(145);
        medicion.setPresionDiastolica(92);
        medicion.setFrecuenciaCardiaca(78);
        medicion.setFrecuenciaRespiratoria(16);
        medicion.setTemperatura(36.6);
        medicion.setSaturacionOxigeno(96);
        atencion.registrarMedicion(medicion);

        atencion.finalizar("Hipertensión arterial no controlada",
                "Se ajusta dosis de antihipertensivo. Control en 15 días.");

        // 6. Reporte (usa los getters de todas las clases)
        System.out.println(atencion.generarReporte());

        // Ejemplo de uso directo de getters
        System.out.println("Paciente atendido: " + atencion.getPaciente().getNombreCompleto());
        System.out.println("Atendido por: " + atencion.getProfesional().getNombreCompleto()
                + " (" + atencion.getProfesional().getEspecialidad() + ")");
        System.out.println("Atenciones en el historial del paciente: "
                + paciente.getHistorialAtenciones().size());
    }
}
