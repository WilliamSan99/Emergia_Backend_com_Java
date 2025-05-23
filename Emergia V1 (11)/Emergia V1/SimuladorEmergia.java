import javax.swing.*;

public class SimuladorEmergia {
    public static void main(String[] args) {
        double energiaGastaTotal, energiaFaltaCompensar;
        JFrame frame = new JFrame("Calculadora de Energia Solar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PainelSolar painel = PainelSolar.criarPainelViaInterface(frame);

        EnergiaHumana humana = EnergiaHumana.criarEnergiaHumanaViaInterface(frame);

        Transporte transporte = Transporte.criarTransporteViaInterface(frame);


        double litrosDiesel = transporte.getDistanciaKM() / transporte.getKmPorLitro();
        double custoDiesel = litrosDiesel * transporte.getPrecoPorLitroDiessel();
        double energiaCaminhaoEletricoKWh = transporte.getDistanciaKM()*1.1;
        double custoEnergiaCaminhaoEletrico = energiaCaminhaoEletricoKWh*0.65;


        String[] climas = {"Ensolarado", "Chuvoso", "Neblina"};
        String climaEscolhido = (String) JOptionPane.showInputDialog(
                frame, "Escolha o clima atual:", "Clima",
                JOptionPane.QUESTION_MESSAGE, null, climas, climas[0]);

        Clima clima = switch (climaEscolhido) {
            case "Chuvoso" -> new Clima("Chuvoso", 200);
            case "Neblina" -> new Clima("Neblina", 300);
            default -> new Clima("Ensolarado", 1000);
        };

        double tempo;
        do {
            String input = JOptionPane.showInputDialog("Informe o tempo de exposição ao sol por dia (em horas, máximo 12h):");

            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "O valor não pode estar em branco.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                tempo = Double.parseDouble(input);
                if (tempo > 12 || tempo <= 0) {
                    JOptionPane.showMessageDialog(frame, "O tempo deve ser entre 1 e 12 horas.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);


        int dias = Integer.parseInt(JOptionPane.showInputDialog("Informe o número de dias de exposição:"));
        double tempoTotal = tempo * dias;


        //deveria esta em clima
        double energiaSolarIncidente = clima.getIrradiancia() * painel.getArea() * tempoTotal * 3600;
        double energiaGeradaKWh = painel.energiaGeradaKWh(clima, tempoTotal);
        double energiaGeradaJoules = energiaGeradaKWh * 3600000;


        // Calcular a emergia
        EmergiaCalculator emergia = new EmergiaCalculator(
                painel.getValorPainel(), humana.getPrecoInstalacao(), transporte.getValorFrete(),
                humana.getNumFuncionarios(), humana.getDiasInstalacao(), transporte.getDistanciaKM()
        );

        energiaGastaTotal = CompensacaoEnergetica.CalcularEnergiaTotal(humana.getEnergiaHumanaJoules(),energiaCaminhaoEletricoKWh*3600000);

        energiaFaltaCompensar = CompensacaoEnergetica.CalcularEnergiaFaltaCompensar(energiaGastaTotal, energiaGeradaJoules);

        String resolucaoGastoTotal = (energiaGastaTotal<energiaFaltaCompensar) ? "Energia incidente da compensação:" : "Energia que falta compensar:";

        energiaFaltaCompensar = (energiaFaltaCompensar<0 )? -energiaFaltaCompensar: energiaFaltaCompensar;//caso o valor de negativo coverte para mostrar oque ainda faltaria para compensar com um valor positivo

        String resultado = String.format("""
    🔷 Dados Fornecidos 🔷
    • Potência do painel escolhida: %d W
    • Eficiência do painel (%s): %.2f %%
    • Clima selecionado: %s
    • Irradiância com base no clima: %.0f W/m²
    • Área do painel: %.2f m²
    • Tempo de exposição ao sol: %.2f horas por dia
    • Número de dias: %d dias
    • Distância do fabricante até residência: %.2f km

    🔷 Gastos 🔷
    • Valor pago no painel: R$ %.2f
    • Frete pago: %s
    • Preço da instalação: R$ %.2f
    
    🔷 Compesação Energética🔷
    • Energia Gasta Total: %.2f J
    • Energia elétrica gerada: %.2f J
    • %s %.2f J

    🔷 Entrega feita por caminhão Diesel 🔷
    • Litros de Diesel necessários: %.2f L
    • Valor gasto com Diesel: R$ %.2f

    🔷 Entrega feita por caminhão elétrico 🔷
    • Caminhão elétrico - Energia gasta no percurso: %.2f kWh
    • Gasto com energia para o caminhão elétrico: R$ %.2f

    🔷 Emergia 🔷
    • Energia solar incidente: %.2f J
    • Energia elétrica gerada: %.2f J
    • Energia elétrica gerada em kWh: %.2f kWh
    • Energia humana gerada: %.2f J
    • Energia humana gerada em kWh: %.2f kWh
    """,
                //🔷 Dados Fornecidos 🔷
                painel.getPotencia(),
                painel.getTipoEscolhido(), painel.getEficiencia() * 100,
                clima.getNome(),
                clima.getIrradiancia(),
                painel.getArea(),
                tempo,
                dias,
                transporte.getDistanciaKM(),
                //🔷 Gastos 🔷
                painel.getValorPainel(),
                transporte.getValorFrete(),
                humana.getPrecoInstalacao(),

                //🔷 Compesação Energética🔷
                energiaGastaTotal,
                energiaGeradaJoules,
                resolucaoGastoTotal,
                energiaFaltaCompensar,

                //🔷 Entrega feita por caminhão Diesel 🔷
                litrosDiesel,
                custoDiesel,

                //🔷 Entrega feita por caminhão elétrico 🔷
                energiaCaminhaoEletricoKWh,
                custoEnergiaCaminhaoEletrico,

                //🔷 Emergia 🔷
                energiaSolarIncidente,
                energiaGeradaJoules,
                energiaGeradaKWh,
                humana.getEnergiaHumanaJoules(),
                humana.getEnergiaHumanaKWh()
        );

        JOptionPane.showMessageDialog(frame, resultado, "Resumo Completo", JOptionPane.INFORMATION_MESSAGE);

        GeradorPDFSolar.gerarPDF(
            painel.getPotencia(),
            painel.getTipoEscolhido(),
            painel.getEficiencia() * 100,
            clima.getNome(),
            clima.getIrradiancia(),
            painel.getArea(),
            tempo,
            tempoTotal,
            dias,
            transporte.getDistanciaKM(),
            painel.getValorPainel(),
            transporte.getValorFrete(),
            humana.getPrecoInstalacao(),
            
            //🔷 Dados Fornecidos 🔷
            energiaGastaTotal,
            resolucaoGastoTotal,
            energiaFaltaCompensar,
            
            //🔷 Entrega feita por caminhão Diesel 🔷
            litrosDiesel,
            custoDiesel,
            
            //🔷 Entrega feita por caminhão elétrico 🔷
            energiaCaminhaoEletricoKWh,
            custoEnergiaCaminhaoEletrico,
            
            //🔷 Emergias 🔷
            energiaSolarIncidente,
            energiaGeradaJoules,
            energiaGeradaKWh,
            humana.getEnergiaHumanaJoules(),
            humana.getEnergiaHumanaKWh(),
            humana.getNumFuncionarios(),
            humana.getDiasInstalacao()
            
        );

    }
}
