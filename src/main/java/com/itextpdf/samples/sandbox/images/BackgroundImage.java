/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/21731027/backgroundimage-in-landscape-and-cover-whole-pdf-with-itextsharp
 */
package com.itextpdf.samples.sandbox.images;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;

public class BackgroundImage {
    public static final String DEST = "./target/sandbox/images/background_image.pdf";

    public static final String IMAGE = "./src/main/resources/img/berlin2013.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new BackgroundImage().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        PageSize pageSize = PageSize.A4.rotate();
        Document doc = new Document(pdfDoc, pageSize);

        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
        canvas.addImage(ImageDataFactory.create(IMAGE), pageSize, false);

        doc.add(new Paragraph("Berlin!"));

        doc.close();
    }
}