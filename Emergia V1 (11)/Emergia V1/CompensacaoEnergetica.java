import javax.swing.*;

public class CompensacaoEnergetica {

    public static double CalcularEnergiaTotal(double energiaHumana, double energiaCaminhaoEletrico){//Calcula a energia total
        double energiaGastaTotal = energiaHumana + energiaCaminhaoEletrico;
        return energiaGastaTotal;
    }
    public static double CalcularEnergiaFaltaCompensar(double energiaTotal, double energiaGeradaJoules){//Calcula a energia total
        double energiaFaltaCompensar = energiaGeradaJoules -energiaTotal;

        return energiaFaltaCompensar;
    }

}
