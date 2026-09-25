package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

public class Taller extends Actividad implements modelo.certificacion.Certificable {

    private boolean requiereNotebook;

    public Taller(int id, String titulo, boolean requiereNotebook, int cupo) {
        super(id, titulo, cupo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {

        if (requiereNotebook) {
            return 5000.0;
        }

        return 2000.0;
    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
    @Override
    public String generarCertificado(Estudiante estudiante) {

        return "Certificado emitido por "
                + ENTIDAD_EMISORA
                + " para "
                + estudiante.getNombre()
                + " por completar el taller "
                + getTitulo();
    }
}
