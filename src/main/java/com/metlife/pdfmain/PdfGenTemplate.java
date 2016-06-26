package com.metlife.pdfmain;

import java.io.FileOutputStream;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.metlife.model.Claim;
import com.metlife.model.Claims;
import com.metlife.utils.RestUtil;

public class PdfGenTemplate {
	
	public static final int NUM_OF_COLS = 6;
	public static final String TEMPLATE_PATH = "stationery.pdf";
	public static final String TARGET_PATH = "ClaimHistoryDetails.pdf";
	public static final String[] headers = {"Claim Date","Claim Number", "Policy/Account#","Insured","Status","Amount"};
	
	public static void main(String[] args) {
		try{
			
			PdfReader reader = new PdfReader(TEMPLATE_PATH);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(TARGET_PATH));
			
			PdfContentByte cb = stamper.getOverContent(1);
		    
	        ColumnText ct = new ColumnText(cb);
	        ct.setSimpleColumn(36, 100, 500, 406);
	        
	        Claims claims = (Claims) RestUtil.makeGetCall("http://172.26.60.13:3000/spi/v2/claims");
	        
			int numOfRows = claims.getItems().size();
			System.out.println("Claim History Size: "+numOfRows);
			
			writeElementsToExistingPDF(stamper, reader, ct, TEMPLATE_PATH, headers, claims, numOfRows);
			
			stamper.close();
			reader.close();
						

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	private static void writeElementsToExistingPDF(PdfStamper stamper, PdfReader reader, ColumnText ct,
			String templatePath, String[] headers, Object serviceResult, int numOfRows) throws DocumentException {
		int pageCount = 1;
		Claims claims = (Claims)serviceResult;
		Rectangle pagesize = reader.getPageSize(1);
		PdfPTable table = new PdfPTable(NUM_OF_COLS);
		// Adding header at Pg1 
		table = addHeaderToTable(table, headers);
		PdfImportedPage newPage = null;
		int totRows = numOfRows; 
		for(int row=0; row < totRows; row++){
			
			if(pageCount != 1){
				table = addHeaderToTable(table, headers);
			}
			
			Claim eachClaim = claims.getItems().get(row).getItem();
			table.addCell(eachClaim.getSubmitDate());
			table.addCell(eachClaim.getNumber());
			table.addCell(eachClaim.getAccountNumber());
			table.addCell(eachClaim.getExtension().getInsured().getName().getFirstName());
			table.addCell("Processed");
			table.addCell(eachClaim.getClaimedAmount().getAmount());
				
			
			ct.addElement(table);
			
			ct.go();   // Prints to the output whenever the columns reaches NUM_OF_COLS (here 6)
			table = new PdfPTable(NUM_OF_COLS);
			
			// logic to check if content overflows to next page
			if (ColumnText.hasMoreText(ct.go())) {
				   System.out.println("Creating a new page at row: "+row);
	        	   newPage = loadPage(newPage, reader, stamper);
	               triggerNewPage(stamper, pagesize, newPage, ct, ++pageCount);
	        }
			
		}
		
		
		
	}
	
	 private static PdfImportedPage loadPage(PdfImportedPage page, PdfReader reader, PdfStamper stamper) {
	       if (page == null) {
	           return stamper.getImportedPage(reader, 1);
	       }
	       return page;
	   }

	 private static void triggerNewPage(PdfStamper stamper, Rectangle pagesize, PdfImportedPage page, ColumnText column, int pagecount) throws DocumentException {
	       stamper.insertPage(pagecount, pagesize);
	       PdfContentByte canvas = stamper.getOverContent(pagecount);
	       canvas.addTemplate(page, 0, 0);
	       column.setCanvas(canvas);
	       column.setSimpleColumn(36, 100, 500, 406);
	       column.go();
	   } 


	/**
	 * Creates our first table
	 * @return our first table
	 *//*
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
	}*/


	/*private static void fillInfo(PdfPTable table, Claims claims, int totalCells, int historySize) {
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
		
	}*/

	/**
	 * Returns Table with just one row with Header Text as column values.
	 * 
	 * @param headers
	 * @return
	 */
	private static PdfPTable addHeaderToTable(PdfPTable table, String[] headers) {
		// TODO Auto-generated method stub
		PdfPTable locTable = table;
		
		for (int i = 0; i < headers.length; i++) {
			locTable.addCell(headers[i]);
		}
		
		return locTable;
		
	}
	
	
	
	
	

}


/*PdfReader reader = new PdfReader("ClaimsHistoryTemplate_New.pdf");
PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("ClaimHistoryData.pdf"));
PdfContentByte cb = stamper.getOverContent(1);
ColumnText ct = new ColumnText(cb);
ct.setSimpleColumn(36, 100, 500, 406);


ct.addElement(createTable());
ct.go();
if(ColumnText.hasMoreText(ct.go())){
	   System.
}

stamper.close();
reader.close();*/