import javax.swing.*;

public class EnergiaHumana {
    private int numFuncionarios;
    private int diasInstalacao;
    private double energiaHumanaKWh;
    private double energiaHumanaJoules;
    private double precoInstalacao;

    public EnergiaHumana(int numFuncionarios, int diasInstalacao,double precoInstalacao, double energiaHumanaKWh, double energiaHumanaJoules ) {
        this.numFuncionarios = numFuncionarios;
        this.diasInstalacao = diasInstalacao;
        this.precoInstalacao = precoInstalacao;
        this.energiaHumanaKWh = energiaHumanaKWh;
        this.energiaHumanaJoules = energiaHumanaJoules;
    }

    public static EnergiaHumana criarEnergiaHumanaViaInterface(JFrame frame){
        int numFuncionarios = Integer.parseInt(JOptionPane.showInputDialog("Quantos funcionários foram responsáveis pela instalação?"));
        int diasInstalacao = Integer.parseInt(JOptionPane.showInputDialog("Quantos dias (8 horas por dia) foram necessários para a instalação?"));
        double precoInstalacao = Double.parseDouble(JOptionPane.showInputDialog("Quanto você pagou pela instalação do painel (R$)?"));


        double energiaHumanaKWh = numFuncionarios * diasInstalacao * 8 * 0.075;
        double energiaHumanaJoules = energiaHumanaKWh * 3600000;

        return new EnergiaHumana(numFuncionarios, diasInstalacao,precoInstalacao, energiaHumanaKWh, energiaHumanaJoules);
    }

    public int getNumFuncionarios() {
        return numFuncionarios;
    }

    public int getDiasInstalacao() {
        return diasInstalacao;
    }

    public double getEnergiaHumanaKWh() {
        return energiaHumanaKWh;
    }

    public double getEnergiaHumanaJoules() {
        return energiaHumanaJoules;
    }

    public double getPrecoInstalacao() {
        return precoInstalacao;
    }
}
