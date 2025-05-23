public class EmergiaCalculator {
    private double valorPainelReais;
    private double valorInstalacaoReais;
    private double valorFrete;
    private int funcionarios;
    private int dias;
    private double distanciaKM;

    private static final double EMERGIA_POR_REAL = 1e12; // sej por R$
    private static final double EMERGIA_POR_HORA_TRABALHO = 5e11; // sej por hora de trabalho
    private static final double EMERGIA_POR_KM = 8e11; // sej por km (exemplo)

    public EmergiaCalculator(double valorPainelReais, double valorInstalacaoReais, double valorFrete,
                             int funcionarios, int dias, double distanciaKM) {
        this.valorPainelReais = valorPainelReais;
        this.valorInstalacaoReais = valorInstalacaoReais;
        this.valorFrete = valorFrete;
        this.funcionarios = funcionarios;
        this.dias = dias;
        this.distanciaKM = distanciaKM;
    }



}
