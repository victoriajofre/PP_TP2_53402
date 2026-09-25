package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;
import modelo.actividades.Curso;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class EventoUniversitario implements Serializable {
    private final String Id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;

    private Sala sala;
    private List<Actividad> actividades;
    static {
        cantidadEventos = 0;
        System.out.println("Inicializador estático: se cargó la clase modelo.EventoUniversitario.");
    }
    public EventoUniversitario(String id, String nombre, double costo, boolean esGratuito) {

        this.Id = id;
        setTitulo(nombre);
        this.gratuito = esGratuito;
        this.costoBase = gratuito ? 0 : costo;

        this.actividades = new ArrayList<>();

        cantidadEventos++;
    }
    public void setTitulo(String nombre) {

        if (nombre != null && !nombre.isBlank()) {
            this.titulo = nombre;
        }
    }
    public EventoUniversitario(EventoUniversitario otroEvento) {
        this(
                otroEvento.Id + "-COPIA",
                otroEvento.titulo,
                otroEvento.costoBase,
                otroEvento.gratuito
        );
    }
    public double calcularCostoEstimado() {

        if (this.gratuito) {
            return 0.0;
        }

        double costoTotal = costoBase;

        for (Actividad actividad : actividades) {
            costoTotal += actividad.calcularCostoMateriales();
        }

        return costoTotal * 1.21;
    }
    public void mostrarDatos() {

        System.out.println("ID: " + Id);
        System.out.println("Título: " + titulo);
        System.out.println("Costo estimado: $" + calcularCostoEstimado());

        if (sala != null) {
            System.out.println("modelo.Sala: " + sala.getNombre());
        } else {
            System.out.println("modelo.Sala: sin asignar");
        }

        System.out.println("Cantidad de actividades: " + actividades.size());

        for (Actividad actividad : actividades) {

            actividad.mostrarIdentificacion();
            actividad.mostrarInscripciones();
        }
    }
    public static int getCantidadEventos() {
        return cantidadEventos;
    }
    public void persistirEvento(String nombreArchivo) throws IOException {

        try (ObjectOutputStream salida =
                     new ObjectOutputStream(
                             new FileOutputStream(nombreArchivo))) {

            salida.writeObject(this);
        }

    }
    public static EventoUniversitario recuperarEvento(String nombreArchivo)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream entrada =
                     new ObjectInputStream(
                             new FileInputStream(nombreArchivo))) {

            return (EventoUniversitario) entrada.readObject();
        }
    }
    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(
            int id,
            String titulo,
            int cupo,
            String tipoActividad) {
        Scanner scanner = new Scanner(System.in);
        switch (tipoActividad.toLowerCase()) {

            case "charla":

                java.util.Scanner scannerCharla =
                        new java.util.Scanner(System.in);

                System.out.print(
                        "Ingrese el nombre del disertante para la charla "
                                + titulo
                                + ": "
                );

                String disertante =
                        scannerCharla.nextLine();

                Actividad charla =
                        new Charla(
                                id,
                                titulo,
                                disertante,
                                cupo
                        );

                this.actividades.add(charla);

                break;

            case "taller":

                java.util.Scanner scannerTaller =
                        new java.util.Scanner(System.in);

                System.out.print(
                        "¿El taller requiere notebook? (true/false): "
                );

                boolean requiereNotebook =
                        scannerTaller.nextBoolean();

                Actividad taller =
                        new Taller(
                                id,
                                titulo,
                                requiereNotebook,
                                cupo
                        );

                this.actividades.add(taller);

                break;

            case "curso":
                System.out.print(
                        "Ingrese el nivel del curso (1, 2 o 3): "
                );

                int nivel =
                        scanner.nextInt();

                Actividad curso = new Curso(
                                id,
                                titulo,
                                cupo,
                                nivel
                        );

                actividades.add(curso);

                break;

            default:

                System.out.println(
                        "Error: Tipo de actividad no reconocido."
                );
        }
    }
    public List<Actividad> getActividades() {
        return Collections.unmodifiableList(actividades);
    }
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {

        List<T> resultado = new ArrayList<>();

        for (Actividad actividad : actividades) {

            if (tipo.isInstance(actividad)) {

                resultado.add(
                        tipo.cast(actividad)
                );
            }
        }

        return resultado;
    }
    public double calcularCostoMateriales(
            List<? extends Actividad> actividades
    ) {

        double total = 0;

        for (Actividad actividad : actividades) {
            total += actividad.calcularCostoMateriales();
        }

        return total;
    }
}
