public class Clima {
    private String nome;
    private double irradiancia; // W/m²

    public Clima(String nome, double irradiancia) {
        this.nome = nome;
        this.irradiancia = irradiancia;
    }

    public String getNome() {
        return nome;
    }

    public double getIrradiancia() {
        return irradiancia;
    }
}
