# QR-Coder-App
This app generates QR Code stickers using iText API's and stamps them on successive pages of a PDF Document. You can encode sensitive information in the QR Code and retrieve it via a QR Code scanner app(I use QR Reader) available via Apple iPhone App ecosystem.Once the QR code is applied it can be scanned back via a smart phone based scanner app right from your desktop screen and printed media likewise. 

I have included a testbed folder and the necessary JAR files from iText if you want to modify the code.
You can change the path to the source document and specify your stamped output. 
I have modified the code to have a scanner object take in input for a list of data like Name, Address and Phone Number field which you will find encoded within the barcode. Once encoded, find the resulting document in the test bed folder and use QR reader mobile app to decipher the encrypted information.

iText provides an API that allows us to create barcode images and apply these as a sticker over the PDF document of our choice. This is different from what Adobe Livecycle or any other barcoding solution does. Livecycle works by changing the document XML subassembly and embedding the barcode within the xml structure of the document rather than using a sticker approach(much easier way). iText is a cool API and QR Code barcodes are just one symbology of barcodes it supports. It does many others as well, PDF417, code39. etc. 
