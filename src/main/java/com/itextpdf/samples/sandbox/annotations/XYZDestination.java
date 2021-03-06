/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written to create a sample file for use in code that answers the following question:
 * http://stackoverflow.com/questions/22828782/all-links-of-existing-pdf-change-the-action-property-to-inherit-zoom-itext-lib
 */
package com.itextpdf.samples.sandbox.annotations;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfDestination;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;

public class XYZDestination {
    public static final String DEST = "./target/sandbox/annotations/xyz_destination.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new XYZDestination().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        for (int i = 0; i < 10; i++) {
            doc.add(new Paragraph("Test"));
            doc.add(new AreaBreak());
        }

        for (int i = 1; i < 11; i++) {

            // Create a link destination to the page, specified in the 1st argument.
            PdfDestination d = PdfExplicitDestination.createXYZ(pdfDoc.getPage(i), 36, 806, 0);
            Paragraph c = new Paragraph(new Link("Goto page " + i, d));
            doc.add(c);
        }

        doc.close();
    }
}
