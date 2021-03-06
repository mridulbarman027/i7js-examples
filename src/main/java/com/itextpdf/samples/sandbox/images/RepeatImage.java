/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2020 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This code sample was written by Bruno Lowagie in answer to this question:
 * http://stackoverflow.com/questions/21720802/how-to-make-text-invisible-in-an-existing-pdf
 * <p>
 * In this example, we take an image that is present in the background,
 * and we add the same image (by its reference) to the foreground so that
 * it covers the OCR'd text.
 */
package com.itextpdf.samples.sandbox.images;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Image;

import java.io.File;

public class RepeatImage {
    public static final String DEST = "./target/sandbox/images/repeat_image.pdf";

    public static final String SRC = "./src/main/resources/pdfs/chinese.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();

        new RepeatImage().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
        PdfPage firstPage = pdfDoc.getFirstPage();
        Rectangle pageSize = firstPage.getPageSize();

        // Assume that there is a single XObject in the source document
        // and this single object is an image.
        PdfDictionary pageDict = firstPage.getPdfObject();
        PdfDictionary pageResources = pageDict.getAsDictionary(PdfName.Resources);
        PdfDictionary pageXObjects = pageResources.getAsDictionary(PdfName.XObject);
        PdfName imgRef = pageXObjects.keySet().iterator().next();
        PdfStream imgStream = pageXObjects.getAsStream(imgRef);
        PdfImageXObject imgObject = new PdfImageXObject(imgStream);
        Image image = new Image(imgObject);
        image.setFixedPosition(0, 0);
        image.setBorder(new SolidBorder(ColorConstants.BLACK, 5));
        image.scaleAbsolute(pageSize.getWidth(), pageSize.getHeight());
        doc.add(image);

        doc.close();
    }
}
