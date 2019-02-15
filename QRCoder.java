package com.Rohan;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by Rohan on 12/28/2016.
 */



public class QRCoder {

    /** The resulting PDF. */
    private static final String RESULT = "C://Users//Rohan//Desktop//itextTestBed+Jars/stampedQRCodedDocument3.pdf";
    private static final String SRC = "C://Users//Rohan//Desktop//itextTestBed+Jars/RohanPurohitResumeDec2016unprocessed.pdf";
    LinkedList<String> myList = new LinkedList<>();
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


        //String uuid=null;
        for(int i=1; i<= reader.getNumberOfPages(); i++){
            PdfContentByte canvas = stamper.getUnderContent(i);
            // uuid=getUUID();
            myList = buildQRCode("Rohan", "14307b brushwood way", "2022152482");
            canvas.beginText();
//          canvas.addImage(getImage(uuid));
           canvas.addImage(getImage2(myList));
         //   canvas.addImage(getImage2(myList)); // new method for Linkedlist
            //canvas.setFontAndSize(BaseFont.createFont(), 7);
            //  canvas.showTextAligned(Element.ALIGN_BOTTOM,uuid,10,650,90);
            canvas.endText();}
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
            return img;}


    String firstIndex;
    String secondIndex;
    String thirdIndex;

    public  LinkedList<String> buildQRCode(String firstIndex, String secondIndex, String thirdIndex){
        Scanner scanner = new Scanner(System.in);

        while ((this.firstIndex==null) &&(this.secondIndex ==null) && (this.thirdIndex==null) ) {
            System.out.println("Please specify Name for QR Code:");
            this.firstIndex = scanner.nextLine();


            System.out.println("Please specify Address for QR Code");
            this.secondIndex = scanner.nextLine();


            System.out.println("Please specify Phone number for QR Code");
            this.thirdIndex = scanner.nextLine();

            myList.add(0, this.firstIndex);
            myList.add(1, this.secondIndex);
            myList.add(2, this.thirdIndex);
        }
        return myList;
    }

    // Additional method to encode arraylist of contents
    private Image getImage2 (LinkedList<String> myList ) throws BadElementException{
      //default parameters.
        Image img2 = null;
        System.out.println("Get LinkedList with contents"+ myList.toString());
      //  buildQRCode("Rohan Purohit", "Address XYZ", "123456789");
        BarcodeQRCode qrcode2 = new BarcodeQRCode(myList.toString(),1,1,null);
        img2 = qrcode2.getImage();
        img2.setAbsolutePosition(10f, 735f);
        img2.setRotationDegrees(90);
        return img2;
    }

}



