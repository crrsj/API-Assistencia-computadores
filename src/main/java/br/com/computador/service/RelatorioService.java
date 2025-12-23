package br.com.computador.service;
// Componentes de escrita e arquivo
import br.com.computador.dto.AparelhoDTO;
import br.com.computador.entity.Aparelho;
import br.com.computador.entity.Cliente;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.colors.ColorConstants;

// Componentes de Layout (O que aparece na página)
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.borders.Border;

// Propriedades de alinhamento e dimensionamento
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;


@Service
public class RelatorioService {

    public byte[] gerarPdfAparelho(AparelhoDTO aparelho) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // 1. CABEÇALHO DA EMPRESA
            Table header = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();
            header.addCell(new Cell().add(new Paragraph("ASSISTÊNCIA TÉCNICA PROFISSIONAL")
                            .setBold().setFontSize(18).setFontColor(ColorConstants.DARK_GRAY))
                    .setBorder(Border.NO_BORDER));

         /*   header.addCell(new Cell().add(new Paragraph("Recibo de Entrega / OS")
                           .setTextAlignment(TextAlignment.RIGHT).setItalic())
                    .setBorder(Border.NO_BORDER));

          */

            // COLOQUE ESTAS NO LUGAR:
            String numeroOS = (aparelho.getOrdemServico() != null) ? aparelho.getOrdemServico().toString() : aparelho.getId().toString();

            header.addCell(new Cell().add(new Paragraph("Recibo de Entrega / OS nº: " + numeroOS)
                            .setTextAlignment(TextAlignment.RIGHT)
                            .setBold()
                            .setFontSize(14)
                            .setItalic())
                    .setBorder(Border.NO_BORDER));

            document.add(header);

            document.add(new Paragraph("______________________________________________________________________________")
                    .setFontColor(ColorConstants.LIGHT_GRAY));

            // 2. DADOS DO CLIENTE (Buscando do relacionamento)
            Cliente cliente = aparelho.getCliente();
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("DADOS DO CLIENTE").setBold());
            document.add(new Paragraph("Nome: " + cliente.getNome()));
            document.add(new Paragraph("Telefone: " + cliente.getTelefone()));
            document.add(new Paragraph("E-mail: " + cliente.getEmail()));

            document.add(new Paragraph("\n"));

            // 3. DETALHES DO SERVIÇO/APARELHO
            document.add(new Paragraph("DETALHES DO SERVIÇO").setBold());
            Table table = new Table(UnitValue.createPercentArray(new float[]{70, 30})).useAllAvailableWidth();

            // Cabeçalho da tabela
            table.addHeaderCell(new Cell().add(new Paragraph("Descrição do Defeito").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Valor").setBold()));

            // Linha do aparelho
            table.addCell(aparelho.getDescricao()); // ou aparelho.getModelo()
            table.addCell("R$ " + String.format("%.2f", aparelho.getTotal()));

            document.add(table);

            // 4. TERMO DE RESPONSABILIDADE
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("Termos: O aparelho será entregue mediante apresentação deste documento. " +
                    "Garantia de 90 dias sobre o serviço executado.")
                    .setFontSize(9).setItalic());

            // 5. ASSINATURAS
            document.add(new Paragraph("\n\n\n"));
            Table assinaturas = new Table(UnitValue.createPercentArray(new float[]{50, 50})).useAllAvailableWidth();

            assinaturas.addCell(new Cell().add(new Paragraph("___________________________\nAssinatura Técnico")
                    .setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));

            assinaturas.addCell(new Cell().add(new Paragraph("___________________________\nAssinatura Cliente")
                    .setTextAlignment(TextAlignment.CENTER)).setBorder(Border.NO_BORDER));

            document.add(assinaturas);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}
