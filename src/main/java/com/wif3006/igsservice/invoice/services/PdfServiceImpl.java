package com.wif3006.igsservice.invoice.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final Configuration freemarkerConfig;

    @Override
    public byte[] generatePdf(String templateName, Map<String, Object> data) {

        log.info(data + "");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Process Freemarker template
            Template template = freemarkerConfig.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            String htmlContent = writer.toString();

            // Convert the HTML content to a PDF using iText
            Document document = new Document();
            PdfWriter pdfWriter =  PdfWriter.getInstance(document, outputStream);
            document.open();

            InputStream inputStream = new ByteArrayInputStream(htmlContent.getBytes("UTF-8"));
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, inputStream, null, Charset.forName("UTF-8"));
            document.close();

            return outputStream.toByteArray();
        } catch (TemplateException | IOException | DocumentException e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
