import javax.swing.*;

public class PainelSolar {
    private int potencia; // em watts
    private double eficiencia; // valor entre 0 e 1
    private double area; // em m²
    private String tipoEscolhido;
    private double valorPainel;

    public PainelSolar(int potencia, String tipoEscolhido, double eficiencia, double area, double valorPainel) {
        this.potencia = potencia;
        this.eficiencia = eficiencia;
        this.area = area;
        this.tipoEscolhido = tipoEscolhido;
        this.valorPainel = valorPainel;
    }

    public static PainelSolar criarPainelViaInterface(JFrame frame) {
        String[] opcoesPotencia = {"305", "310", "320", "330", "335", "340", "345", "350", "355",
                "405", "435", "450", "460", "500", "550", "590"};

        int potencia = Integer.parseInt((String) JOptionPane.showInputDialog(
                frame, "Escolha a potência do painel (W):", "Potência",
                JOptionPane.QUESTION_MESSAGE, null, opcoesPotencia, opcoesPotencia[0]));

        String[] tipos = {"Monocristalino", "Policristalino"};
        String tipoEscolhido = (String) JOptionPane.showInputDialog(
                frame, "Escolha o tipo de painel:", "Tipo",
                JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        double eficiencia = tipoEscolhido.equals("Monocristalino") ? 0.20 : 0.15;

        String[] opcoesArea = {"2m²", "4m²"};
        String areaEscolhida = (String) JOptionPane.showInputDialog(
                frame, "Escolha o tamanho do painel:", "Área",
                JOptionPane.QUESTION_MESSAGE, null, opcoesArea, opcoesArea[0]);

        double area = areaEscolhida.equals("2m²") ? 2.0 : 4.0;

        double valorPainel = Double.parseDouble(JOptionPane.showInputDialog("Quanto você pagou no painel solar (R$)?"));

        return new PainelSolar(potencia,tipoEscolhido ,eficiencia, area, valorPainel);
    }

    public double energiaGeradaKWh(Clima clima, double tempoHoras) {
        return eficiencia * clima.getIrradiancia() * area * tempoHoras / 1000;
    }

    public int getPotencia() {
        return potencia;
    }


    public double getEficiencia() {
        return eficiencia;
    }

    public String getTipoEscolhido() {
        return tipoEscolhido;
    }

    public double getValorPainel() {
        return valorPainel;
    }

    public double getArea() {
        return area;
    }
}
