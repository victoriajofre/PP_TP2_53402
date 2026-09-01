import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventoUniversitario {
    private final String Id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;

    private Sala sala;
    private List<Actividad> actividades;
    static {
        cantidadEventos = 0;
        System.out.println("Inicializador estático: se cargó la clase EventoUniversitario.");
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
            System.out.println("Sala: " + sala.getNombre());
        } else {
            System.out.println("Sala: sin asignar");
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
    public void asignarSala(Sala sala) {
        this.sala = sala;
    }
    public void crearActividad(
            int id,
            String titulo,
            int cupo,
            String tipoActividad) {

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

            default:

                System.out.println(
                        "Error: Tipo de actividad no reconocido."
                );
        }
    }
    public List<Actividad> getActividades() {
        return Collections.unmodifiableList(actividades);
    }
}
