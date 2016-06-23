package com.metlife.pdfmain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.metlife.model.Claim;
import com.metlife.model.Claims;

public class PdfGenerator {

	public static final String RESULT = "first_table.pdf";
	
	public static final int numOfCols = 6;

/**
 * Main method.
 * @param    args    no arguments needed
 * @throws DocumentException 
 * @throws IOException
 */
public static void main(String[] args) throws IOException, DocumentException {
    new PdfGenerator().createPdf(RESULT);
}

/**
 * Creates a PDF with information about the movies
 * @param    filename the name of the PDF file that will be created.
 * @throws    DocumentException 
 * @throws    IOException
 */
public void createPdf(String filename)
    throws IOException, DocumentException {
	// step 1
    Document document = new Document();
    // step 2
    PdfWriter.getInstance(document, new FileOutputStream(filename));
    // step 3
    document.open();
    
    // step 4
    document.add(createTable());
    // step 5
    document.close();
}

/**
 * Creates our first table
 * @return our first table
 */
public static PdfPTable createTable() {
	// a table with three columns
    PdfPTable table = new PdfPTable(numOfCols);
    String[] headers = {"Claim Date","Claim Number", "Policy/Account#","Insured","Status","Amount"};
    
    int numOfCells = 5 * numOfCols;
    
   createHeader(table, headers);
    
   	// Make rest call and fetch claim history object.
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setMessageConverters(getMessageConverters());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    Claims claims = restTemplate.getForObject("http://172.26.60.13:3000/spi/v2/claims", Claims.class);
    
    System.out.println("Claims: "+claims.getItems().get(0).getItem());
    
    Object obj = null;
    
	fillInfo(table, claims, numOfCells, claims.getItems().size());
    
    // we add the four remaining cells with addCell()
    
    return table;
}

private static List<HttpMessageConverter<?>> getMessageConverters() {
    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
    converters.add(new MappingJackson2HttpMessageConverter());
    return converters;
}   

private static void fillInfo(PdfPTable table, Claims claims, int totalCells, int historySize) {
	PdfPTable locTable = table;
	int totRows = historySize; 
	for(int row=0; row < totRows; row++){
		for(int col = 0; col < numOfCols; col++){
			Claim eachClaim = claims.getItems().get(row).getItem();
			locTable.addCell(eachClaim.getSubmitDate());
			locTable.addCell(eachClaim.getNumber());
			locTable.addCell(eachClaim.getAccountNumber());
			locTable.addCell(eachClaim.getExtension().getInsured().getName().getFirstName());
			locTable.addCell("Processed");
			locTable.addCell(eachClaim.getClaimedAmount().getAmount());
		}
	}
	
}

private static void createHeader(PdfPTable table, String[] headers) {
	// TODO Auto-generated method stub
	PdfPTable locTable = table;
	
	for (int i = 0; i < headers.length; i++) {
		locTable.addCell(headers[i]);
	}
	
}

}
