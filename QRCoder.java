package com.Rohan;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.UUID;

/**
 * Created by Rohan on 12/28/2016.
 */



public class QRCoder {

        /** The resulting PDF. */
        private static final String RESULT = "C://Users//Rohan//Desktop//itext jars/stampedQRCodedDocument.pdf";
        private static final String SRC = "C://Users//Rohan//Desktop//itext jars/RohanPurohitResumeDec2016unprocessed.pdf";
        /**
         * Generates a PDF file with different types of barcodes.
         *
         * @param args
         *            no arguments needed here
         * @throws DocumentException
         * @throws IOException
         */
        public static void main(String[] args) throws IOException,
                DocumentException {
            QRCoder ab= new QRCoder();
            byte[] inputByteArray=ab.getBytesFromFile(SRC);
            ab.writeBytestoFile(RESULT, ab.stampPdf(inputByteArray));
        }

        private byte[] getBytesFromFile(String src) throws IOException{
            FileInputStream fs=new FileInputStream(src);
            byte[] b= new byte[fs.available()];
            fs.read(b);
            return b;
        }

        private void writeBytestoFile(String dest, byte[] b) throws IOException{
            FileOutputStream fs=new FileOutputStream(dest);
            fs.write(b);
            fs.close();
        }


        public byte[] stampPdf(byte[] byteArrayInput) throws IOException, DocumentException{
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayInput);
            ByteArrayOutputStream baos =new ByteArrayOutputStream();
            PdfReader reader = new PdfReader(bais);
            PdfStamper stamper = new PdfStamper(reader, baos);

            String uuid=null;
            for(int i=1; i<= reader.getNumberOfPages(); i++){

                PdfContentByte canvas = stamper.getUnderContent(i);
                uuid=getUUID();
                canvas.beginText();
                canvas.addImage(getImage(uuid));
                canvas.setFontAndSize(BaseFont.createFont(), 7);
                canvas.showTextAligned(Element.ALIGN_BOTTOM,uuid,10,650,90);
                canvas.endText();
            }
            stamper.close();
            return baos.toByteArray();
        }

        private String getUUID(){
            return UUID.randomUUID().toString();
        }

        private Image getImage(String uuid) throws BadElementException{
            Image img=null;
            System.out.println("get barcode with uuid  "+uuid);
            BarcodeQRCode qrcode = new BarcodeQRCode(uuid, 1, 1, null);
            img = qrcode.getImage();

            //this line is to position barcode
            img.setAbsolutePosition(10f, 735f);
            img.setRotationDegrees(90);
            return img;

        }

    }

