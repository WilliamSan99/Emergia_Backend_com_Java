import javax.swing.*;

public class Transporte {
    private double distanciaKM;
    private static double valorFrete;
    private double kmPorLitro = 2.5;
    private double precoPorLitroDiessel = 6.26;

    public Transporte(double distanciaKM, double valorFrete) {
        this.distanciaKM = distanciaKM;
        this.valorFrete = valorFrete;
    }

    public static Transporte criarTransporteViaInterface(JFrame frame){
        double valorFrete;
        String[] fretePago = {"Sim", "Não"};
        String freteEscolhida = (String) JOptionPane.showInputDialog(
                frame, "Pagou pelo frete?", "Frete",
                JOptionPane.QUESTION_MESSAGE, null, fretePago, fretePago[0]);
        if (freteEscolhida == "Sim"){
            valorFrete  = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do Frete"));
        }else{
            valorFrete = 0;
        }
        boolean fretePagoBool = freteEscolhida.equals("Sim");
        double distanciaKM = Double.parseDouble(JOptionPane.showInputDialog("Qual a distância do fabricante até sua residência? (km):"));

        return new Transporte(distanciaKM, valorFrete);
    }

    public double getDistanciaKM() {
        return distanciaKM;
    }

    public static double getValorFrete() {
        return valorFrete;
    }

    public double getKmPorLitro() {
        return kmPorLitro;
    }

    public double getPrecoPorLitroDiessel() {
        return precoPorLitroDiessel;
    }
}
