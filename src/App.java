// Entrega TP2
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.Estudiante;
import modelo.actividades.Actividad;
import modelo.Inscripcion;
import modelo.certificacion.Certificable;
import excepciones.CupoExcedidoException;
import modelo.actividades.Charla;
import modelo.actividades.Taller;
import modelo.actividades.Curso;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // =========================================================
        // TP2 - EJERCICIO 1
        // Modularización, excepciones y persistencia
        // =========================================================


        EventoUniversitario eventoEj1 =
                new EventoUniversitario(
                        "EVT-EJ1",
                        "Evento Universitario Simple",
                        10000,
                        false
                );

        EventoUniversitario copiaEventoEj1 =
                new EventoUniversitario(eventoEj1);

        System.out.println();
        System.out.println("EVENTO ORIGINAL");
        eventoEj1.mostrarDatos();

        System.out.println();
        System.out.println("COPIA DEL EVENTO");
        copiaEventoEj1.mostrarDatos();

        System.out.println();
        System.out.println(
                "Eventos creados hasta el momento: "
                        + EventoUniversitario.getCantidadEventos()
        );


        List<Estudiante> estudiantes = new ArrayList<>();

        Estudiante estudiante1 = new Estudiante("53402", "Victoria");
        Estudiante estudiante2 = new Estudiante("50001", "Juan");
        Estudiante estudiante3 = new Estudiante("50002", "Sofia");
        Estudiante estudiante4 = new Estudiante("50003", "Martina");
        Estudiante estudiante5 = new Estudiante("50004", "Lucia");
        Estudiante estudiante6 = new Estudiante("50005", "Pedro");

        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);
        estudiantes.add(estudiante4);
        estudiantes.add(estudiante5);
        estudiantes.add(estudiante6);

        EventoUniversitario eventoPrincipal =
                new EventoUniversitario(
                        "EVT-1",
                        "Jornada de Programacion",
                        10000,
                        false
                );

        Sala sala1 = new Sala(1, "Aula Magna");
        eventoPrincipal.asignarSala(sala1);

        eventoPrincipal.crearActividad(
                1,
                "Introduccion a Java",
                5,
                "charla"
        );

        Actividad actividadPrueba =
                eventoPrincipal.getActividades().get(0);

        System.out.println();
        System.out.println("======================================");
        System.out.println("   PRUEBA DE EXCEPCION POR CUPO");
        System.out.println("======================================");

        try {

            actividadPrueba.inscribir(estudiante1);

            System.out.println(
                    "Inscripcion realizada correctamente para "
                            + estudiante1.getNombre()
            );

        } catch (CupoExcedidoException e) {

            System.out.println(
                    "Error de inscripcion: "
                            + e.getMessage()
            );
        }

        try {

            actividadPrueba.inscribir(estudiante2);
            actividadPrueba.inscribir(estudiante3);
            actividadPrueba.inscribir(estudiante4);
            actividadPrueba.inscribir(estudiante5);

            System.out.println(
                    "Se completo el cupo de la actividad."
            );

            actividadPrueba.inscribir(estudiante6);

        } catch (CupoExcedidoException e) {

            System.out.println(
                    "Caso fallido controlado: "
                            + e.getMessage()
            );
        }

        System.out.println(
                "Cantidad de estudiantes registrados: "
                        + estudiantes.size()
        );
        eventoPrincipal.crearActividad(
                2,
                "Actividad para Persistencia",
                10,
                "taller"
        );
        System.out.println();
        System.out.println("======================================");
        System.out.println("      PRUEBA DE PERSISTENCIA");
        System.out.println("======================================");

        Actividad actividadPersistencia =
                eventoPrincipal.getActividades().get(1);

        try {

            actividadPersistencia.inscribir(estudiante4);

            System.out.println(
                    "Inscripción realizada correctamente."
            );

            eventoPrincipal.persistirEvento("evento.dat");

            System.out.println(
                    "Evento guardado correctamente."
            );

            EventoUniversitario eventoRecuperado =
                    EventoUniversitario.recuperarEvento("evento.dat");

            System.out.println(
                    "Evento recuperado correctamente."
            );

            eventoRecuperado.mostrarDatos();

        } catch (CupoExcedidoException e) {

            System.out.println(
                    "Error de inscripción: "
                            + e.getMessage()
            );

        } catch (IOException e) {

            System.out.println(
                    "Error de persistencia: "
                            + e.getMessage()
            );

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "Error al recuperar el evento: "
                            + e.getMessage()
            );

        } finally {

            System.out.println(
                    "Fin del proceso."
            );
        }
        System.out.println();
        System.out.println("======================================");
        System.out.println("         TP2 - EJERCICIO 2");
        System.out.println("     INTERFACES Y CERTIFICADOS");
        System.out.println("======================================");

        // Se construye otro evento
        eventoPrincipal = new EventoUniversitario(
                "EVT-1",
                "Jornada de Programación",
                10000,
                false
        );


        // AGREGACIÓN:
        // La modelo.Sala se crea independientemente del Evento

        sala1 = new Sala(
                1,
                "Aula Magna"
        );

        eventoPrincipal.asignarSala(sala1);

        System.out.println("modelo.Sala creada y asignada al evento: "
                + sala1.getNombre());


        eventoPrincipal.crearActividad(
                1,
                "Introducción a Java",
                20,
                "charla"
        );

        eventoPrincipal.crearActividad(
                2,
                "Programación Orientada a Objetos",
                15,
                "taller"
        );
        eventoPrincipal.crearActividad(
                3,
                "Java Avanzado",
                15,
                "curso"
        );


        // INSCRIPCIONES
        // Concepto incorporado en el Ejercicio 2
        // INSCRIPCIONES EN CHARLA, TALLER Y CURSO


        try {

            eventoPrincipal.getActividades()
                    .get(0)
                    .inscribir(estudiante1);

            eventoPrincipal.getActividades()
                    .get(0)
                    .inscribir(estudiante2);

            eventoPrincipal.getActividades()
                    .get(1)
                    .inscribir(estudiante2);

            eventoPrincipal.getActividades()
                    .get(1)
                    .inscribir(estudiante3);

            eventoPrincipal.getActividades()
                    .get(2)
                    .inscribir(estudiante4);

            eventoPrincipal.getActividades()
                    .get(2)
                    .inscribir(estudiante5);

            System.out.println("Inscripciones realizadas correctamente.");

        } catch (CupoExcedidoException e) {

            System.out.println(
                    "Error al realizar una inscripción: "
                            + e.getMessage()
            );
        }


        System.out.println();
        System.out.println("======================================");
        System.out.println("      CERTIFICADOS EMITIDOS");
        System.out.println("======================================");

        for (Actividad actividad : eventoPrincipal.getActividades()) {

            if (actividad instanceof Certificable certificable) {

                System.out.println();
                System.out.println(
                        "Actividad: " + actividad.getTitulo()
                );

                for (Inscripcion inscripcion : actividad.getInscripciones()) {

                    String certificado =
                            certificable.generarCertificado(
                                    inscripcion.getEstudiante()
                            );

                    System.out.println(certificado);
                }
            }
        }



        // RESUMEN DEL EVENTO
        // Aquí también se evidencia el polimorfismo

        System.out.println();
        System.out.println("RESUMEN DEL EVENTO");
        eventoPrincipal.mostrarDatos();

        System.out.println();
        System.out.println(
                "Total de objetos EventoUniversitario creados: "
                        + EventoUniversitario.getCantidadEventos()
        );


        System.out.println();
        System.out.println("======================================");
        System.out.println("       TP2 - EJERCICIO 3");
        System.out.println("       GENERICS Y WILDCARDS");
        System.out.println("======================================");

        List<Charla> charlas =
                eventoPrincipal.filtrarActividadesPorTipo(Charla.class);

        List<Taller> talleres =
                eventoPrincipal.filtrarActividadesPorTipo(Taller.class);

        List<Curso> cursos =
                eventoPrincipal.filtrarActividadesPorTipo(Curso.class);

        System.out.println(
                "Charlas encontradas: " + charlas.size()
        );

        System.out.println(
                "Talleres encontrados: " + talleres.size()
        );

        System.out.println(
                "Cursos encontrados: " + cursos.size()
        );

        System.out.println();

        System.out.println(
                "Costo de materiales de talleres: $"
                        + eventoPrincipal.calcularCostoMateriales(talleres)
        );

        System.out.println(
                "Costo de materiales de cursos: $"
                        + eventoPrincipal.calcularCostoMateriales(cursos)
        );

        System.out.println(
                "Costo de materiales de todas las actividades: $"
                        + eventoPrincipal.calcularCostoMateriales(
                        eventoPrincipal.getActividades()
                )
        );


        }

    }
