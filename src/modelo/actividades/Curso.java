package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

public class Curso extends Actividad implements Certificable {
    private int nivel;

    public Curso(
            int id,
            String titulo,
            int cupo,
            int nivel
    ) {
        super(id, titulo, cupo);
        this.nivel = nivel;
    }

    @Override
    public double calcularCostoMateriales() {

        return switch (nivel) {
            case 1 -> 1000;
            case 2 -> 2000;
            case 3 -> 3000;
            default -> 0;
        };
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {

        return "Certificado emitido por "+ ENTIDAD_EMISORA + " para "+ estudiante.getNombre() + " por completar el curso " + getTitulo();
    }

    public int getNivel() {
        return nivel;
    }
}

