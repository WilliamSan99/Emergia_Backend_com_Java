import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeradorPDFSolar {
    public static void gerarPDF(
        int potencia,
        String tipoPainel,
        double eficiencia,
        String nomeClima,
        double irradiancia,
        double area,
        double tempo,
        double tempoTotal,
        int dias,
        double distanciaKM,
        double valorPainel,
        double valorFrete,
        double precoInstalacao,
        double energiaGastaTotal,
        String resolucaoGastoTotal,
        double energiaFaltaCompensar,
        double litrosDiesel,
        double custoDiesel,
        double energiaCaminhaoEletricoKWh,
        double custoEnergiaCaminhaoEletrico,
        double energiaSolarIncidente,
        double energiaGeradaJoules,
        double energiaGeradaKWh,
        double energiaHumanaJoules,
        double energiaHumanaKWh,
        int numFuncionarios,
        int diasInstalacao
    ){
        double custoTotalComDiesel = valorPainel + valorFrete + precoInstalacao + custoDiesel;
        double custoTotalComEletrico = valorPainel + valorFrete + precoInstalacao + custoEnergiaCaminhaoEletrico;

        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("relatorio.pdf"));
            documento.open();

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Font textoFont = new Font(Font.FontFamily.HELVETICA, 12);

            documento.add(new Paragraph("\n1. Dados da Simulação", tituloFont));
            documento.add(new Paragraph("\nPotência do painel: " + potencia + " W", textoFont));
            documento.add(new Paragraph("Tipo do painel: " + tipoPainel, textoFont));
            documento.add(new Paragraph("Eficiência: " + eficiencia + " %", textoFont));
            documento.add(new Paragraph("Área do painel: " + area + " m²", textoFont));
            documento.add(new Paragraph("Clima selecionado: " + nomeClima, textoFont));
            documento.add(new Paragraph("Irradiância solar: " + irradiancia + " W/m²", textoFont));
            documento.add(new Paragraph("Tempo de exposição ao sol: " + tempo + " hora(s) por dia", textoFont));
            documento.add(new Paragraph("Número de dias simulados: " + dias + " dia(s)", textoFont));
            documento.add(new Paragraph("Distância da fábrica até a residência: " + distanciaKM + " km", textoFont));

            documento.add(new Paragraph("\n2. Energia Solar e Elétrica", tituloFont));
            documento.add(new Paragraph("\nO que é energia solar incidente?", tituloFont));
            documento.add(new Paragraph("Energia solar incidente é a quantidade de luz do sol que atinge um painel solar. Pense no painel como uma \"esponja\" que absorve essa luz.", textoFont));

            documento.add(new Paragraph("\nComo é calculada?", tituloFont));
            documento.add(new Paragraph("Usamos a fórmula:", textoFont));
            documento.add(new Paragraph("Energia Solar Incidente = Irradiância × Área do Painel × Tempo em segundos", textoFont));

            documento.add(new Paragraph("\n- Irradiância: \"Força\" da luz solar. Em dias (" + nomeClima + "), usamos " + irradiancia + " W/m².", textoFont));
            documento.add(new Paragraph("- Área do painel: Quanto maior a área, mais luz é captada. Aqui, " + area + " m².", textoFont));
            documento.add(new Paragraph("- Tempo total (em horas): " + dias + " dia × " + tempo + " hora/dia = " + tempoTotal + " horas.", textoFont));

            documento.add(new Paragraph("\nCálculo:", tituloFont));
            documento.add(new Paragraph( irradiancia + " W/m² × " + area + " m² × " + tempoTotal + " x 3600s horas = " + String.format("%.2f", energiaSolarIncidente) + " J", textoFont));

            documento.add(new Paragraph("\nO que é energia elétrica gerada?", tituloFont));
            documento.add(new Paragraph("É a quantidade de eletricidade que o painel produz a partir da energia solar recebida.", textoFont));

            documento.add(new Paragraph("\nComo é calculada?", tituloFont));
            documento.add(new Paragraph("Multiplicamos Irradiância em kW (dividimos por 1000) × Área do Painel × Tempo em em horas x eficiência do painel", textoFont));
            documento.add(new Paragraph("Energia Gerada = (" + irradiancia + " W/m² / 1000) kWm² × " + area + " m² × " + tempoTotal + " x " + eficiencia + " = " + String.format("%.2f", energiaGeradaKWh) + " kWh", textoFont));

            documento.add(new Paragraph("\nConversão para J", tituloFont));
            documento.add(new Paragraph("Como 1 kWh = 3.600.000 J:", textoFont));
            documento.add(new Paragraph( String.format("%.2f", energiaGeradaKWh) + " kWh * 3.600.000 = " + String.format("%.2f", energiaGeradaJoules) + " J", textoFont));

            documento.add(new Paragraph("\n3. Comparativo de Entrega", tituloFont));

            documento.add(new Paragraph("\nEntrega com Caminhão a Diesel", tituloFont));
            documento.add(new Paragraph("- Eficiência do caminhão: 2,5 km/L", textoFont));
            documento.add(new Paragraph("- Distância percorrida: " + distanciaKM + " km", textoFont));
            documento.add(new Paragraph("\nComo é calculado o custo do diesel?", textoFont));
            documento.add(new Paragraph("Litros de diesel = Distância ÷ Eficiência = " + distanciaKM + " ÷ 2,5 = " + litrosDiesel + " L", textoFont));
            documento.add(new Paragraph("Custo do diesel = " + String.format("%.2f", litrosDiesel) + " × R$ 6,26 = R$ " + String.format("%.2f", custoDiesel), textoFont));

            documento.add(new Paragraph("\nEntrega com Caminhão Elétrico", tituloFont));
            documento.add(new Paragraph("- Consumo por km: 1,1 kWh/km", textoFont));
            documento.add(new Paragraph("- Distância percorrida: " + distanciaKM + " km", textoFont));
            documento.add(new Paragraph("\nComo é calculado?", textoFont));
            documento.add(new Paragraph("Energia consumida = " + distanciaKM + " × 1,1 = " + String.format("%.2f", energiaCaminhaoEletricoKWh) + " kWh", textoFont));
            documento.add(new Paragraph("Custo com energia = " + String.format("%.2f", energiaCaminhaoEletricoKWh) + " × R$ 0,65 = R$ " + String.format("%.2f", custoEnergiaCaminhaoEletrico), textoFont));

            documento.add(new Paragraph("\n4. Energia Humana (Instalação)", tituloFont));
            documento.add(new Paragraph("\nComo calcular a energia humana consumida?", textoFont));
            documento.add(new Paragraph("Energia (kWh) = Funcionários × Dias × Horas/dia × Consumo/hora", textoFont));
            documento.add(new Paragraph("- Funcionários: " + numFuncionarios, textoFont));
            documento.add(new Paragraph("- Dias: " + diasInstalacao, textoFont));
            documento.add(new Paragraph("- Horas/dia: 8h", textoFont));
            documento.add(new Paragraph("- Consumo por hora: 0,075 kWh", textoFont));

            documento.add(new Paragraph("\nCálculo:", tituloFont));
            documento.add(new Paragraph( numFuncionarios + " × " + diasInstalacao + " × 8h × 0,075kWh = " + String.format("%.2f", energiaHumanaKWh) + " kWh", textoFont));

            documento.add(new Paragraph("\nConversão para Joules:", tituloFont));
            documento.add(new Paragraph( String.format("%.2f", energiaHumanaKWh) + " × 3.600.000 = " + String.format("%.2f", energiaHumanaJoules) + " J", textoFont));

            documento.add(new Paragraph("\n5. Custos Totais", tituloFont));
            documento.add(new Paragraph("\nPainel Solar: R$ " + String.format("%.2f", valorPainel), textoFont));
            documento.add(new Paragraph("Frete (Simulado): R$ " + String.format("%.2f", valorFrete), textoFont));
            documento.add(new Paragraph("Instalação: R$ " + String.format("%.2f", precoInstalacao), textoFont));
            documento.add(new Paragraph("Entrega com Diesel: R$ " + String.format("%.2f", custoDiesel), textoFont));
            documento.add(new Paragraph("Entrega com Caminhão Elétrico: R$ " + String.format("%.2f", custoEnergiaCaminhaoEletrico), textoFont));
            documento.add(new Paragraph("Custo Total (com diesel): R$ " + String.format("%.2f", custoTotalComDiesel), textoFont));
            documento.add(new Paragraph("Custo Total (com caminhão elétrico): R$ " + String.format("%.2f", custoTotalComEletrico), textoFont));

            documento.add(new Paragraph("\n6. Compensação Energética", tituloFont));
            documento.add(new Paragraph("\nEnergia Gasta Total: " + String.format("%.2f", energiaSolarIncidente) + " J", textoFont));
            documento.add(new Paragraph("Energia Elétrica Gerada: " + String.format("%.2f", energiaHumanaJoules) + " J", textoFont));
            documento.add(new Paragraph(resolucaoGastoTotal + String.format("%.2f", energiaFaltaCompensar) + " J", textoFont));

            documento.add(new Paragraph("\n7. Resumo da Simulação", tituloFont));
            documento.add(new Paragraph("\nEnergia Solar Incidente: " + String.format("%.2f", energiaSolarIncidente) + " J", textoFont));
            documento.add(new Paragraph("Energia Elétrica Gerada em J: " + String.format("%.2f", energiaHumanaJoules) + " J", textoFont));
            documento.add(new Paragraph("Energia Gerada em kWh: " + String.format("%.2f", energiaGeradaKWh) + " kWh", textoFont));
            documento.add(new Paragraph("Energia Humana Utilizada: " + String.format("%.2f", energiaHumanaJoules) + " J (" + String.format("%.2f", energiaHumanaKWh) + " kWh)", textoFont));

            documento.add(new Paragraph("\n8. Considerações Finais", tituloFont));
            documento.add(new Paragraph("\nA simulação mostra como a energia solar pode ser convertida de forma eficiente em eletricidade com apenas uma hora de sol. Ao mesmo tempo, a comparação entre transporte tradicional e sustentável, bem como a análise dos custos e consumo humano, evidencia os benefícios econômicos e ecológicos da energia solar e de tecnologias limpas.", textoFont));

            documento.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}