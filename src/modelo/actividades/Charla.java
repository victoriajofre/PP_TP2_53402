package modelo.actividades;

public class Charla extends Actividad {

    private String disertante;

    public Charla(int id, String titulo, String disertante, int cupo) {
        super(id, titulo, cupo);
        this.disertante = disertante;
    }

    @Override
    public double calcularCostoMateriales() {
        return 0.0;
    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
}
