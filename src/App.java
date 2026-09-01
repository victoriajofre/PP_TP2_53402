import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // =========================================================
        // EJERCICIO 1
        // Clases, objetos, constructor de copia, static y final
        // =========================================================

        System.out.println("======================================");
        System.out.println("           EJERCICIO 1");
        System.out.println("======================================");

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


        // =========================================================
        // EJERCICIO 2
        // Relaciones entre objetos y colecciones
        // Estudiantes - Sala - Evento - Inscripciones
        // =========================================================

        System.out.println();
        System.out.println("======================================");
        System.out.println("           EJERCICIO 2");
        System.out.println("======================================");

        // Se construye una lista de estudiantes

        List<Estudiante> estudiantes = new ArrayList<>();

        Estudiante estudiante1 =
                new Estudiante("53402", "Victoria");

        Estudiante estudiante2 =
                new Estudiante("50001", "Juan");

        Estudiante estudiante3 =
                new Estudiante("50002", "Sofía");

        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);

        System.out.println(
                "Cantidad de estudiantes registrados: "
                        + estudiantes.size()
        );


        // Se construye otro evento

        EventoUniversitario eventoPrincipal =
                new EventoUniversitario(
                        "EVT-1",
                        "Jornada de Programación",
                        10000,
                        false
                );


        // AGREGACIÓN:
        // La Sala se crea independientemente del Evento

        Sala sala1 =
                new Sala(
                        1,
                        "Aula Magna"
                );

        eventoPrincipal.asignarSala(sala1);

        System.out.println("Sala creada y asignada al evento: "
                + sala1.getNombre());


        // =========================================================
        // EJERCICIO 3
        // Herencia, abstracción y polimorfismo
        // Charla y Taller son subclases de Actividad
        // =========================================================

        System.out.println();
        System.out.println("======================================");
        System.out.println("           EJERCICIO 3");
        System.out.println("======================================");


        // COMPOSICIÓN:
        // Las actividades son creadas por el propio evento

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


        // INSCRIPCIONES
        // Concepto incorporado en el Ejercicio 2
        // y utilizado ahora con Charla y Taller

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


        // RESUMEN DEL EVENTO
        // Aquí también se evidencia el polimorfismo

        System.out.println();
        System.out.println("RESUMEN DEL EVENTO");

        eventoPrincipal.mostrarDatos();


        // CONTADOR STATIC

        System.out.println();
        System.out.println(
                "Total de objetos EventoUniversitario creados: "
                        + EventoUniversitario.getCantidadEventos()
        );
    }
}