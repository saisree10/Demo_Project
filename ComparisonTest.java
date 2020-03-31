package DemoApp.Demo_Project;
import java.io.BufferedReader;
import java.io.File;
import java.util.List;
/*
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.eclipse.jetty.client.HttpRequest;
*/

import org.testng.annotations.Test;
import java.io.IOException;
//import com.github.miachm.sods.Range;
import java.io.InputStreamReader;
import java.net.*;

import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;
//import com.google.common.base.Equivalence.Wrapper;


import org.jopendocument.dom.spreadsheet.MutableCell;

public class ComparisonTest{
		
	@Test
       public static void getDataOfFile1_Test() {
		
		/* Approach #2 - Trying to read .ods file content directly from sheet cell values instead in the way of getting or reading file content as in range
		
		Online link for reference : https://stackoverflow.com/questions/46468272/how-to-read-ods-document-by-java-and-jopendocument-package
		
		
	//    SpreadSheet spreadsheet;
	    try {
	         //Getting the 0th sheet for manipulation| pass sheet name as string
	    	
	    	String file = "/Users/saisri/eclipse-workspace/Demo_Project/src/test/java/resources/File1.ods";
	    	
	    	SpreadSheet spread = new SpreadSheet(new File(file));


	         spread = SpreadSheet.createFromFile(file);

	         //Get row count and column count
	         int nColCount = spread.getSheet(0).getColumnCount();
	         int nRowCount = spread.getSheet(0).getRowCount();

	         System.out.println("Rows :"+nRowCount);
	         System.out.println("Cols :"+nColCount);
	         //Iterating through each row of the selected sheet
	         MutableCell cell = null;
	         for(int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++)
	         {
	           //Iterating through each column
	           for(int nColIndex = 0; nColIndex < nColCount; nColIndex++)
	           {
	             cell = spread.getSheet(0).getCellAt(nColIndex, nRowIndex);
	             System.out.print(cell.getValue()+ " ");
	            }
	            System.out.println();
	          }
		
		
		*/
		
		
		
		
		// Approach #1 : Current approach of reading file content as in range from .odf file that prints urls as in values including additional information relating file information
		try {
            SpreadSheet spread = new SpreadSheet(new File("/Users/saisri/eclipse-workspace/Demo_Project/src/test/java/resources/File1.ods"));
            System.out.println("Number of sheets: " + spread.getNumSheets());

            List<Sheet> sheets = spread.getSheets();

            for (Sheet sheet : sheets) {
                System.out.println("In sheet " + sheet.getName());

                com.github.miachm.sods.Range range = sheet.getDataRange();

               
                
                
                /*  Method -1  for processing request url from values when using approach #2
                 
                Object urlString[][] = sheet.getDataRange().getValues();
                		
                	//	"http://localhost:8080/";
                
                // create the url
                URL url = new URL(urlString);
                
                // open the url stream, wrap it an a few "readers"
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

                // write the output to stdout
                String line;
                while ((line = reader.readLine()) != null)
                {
                  System.out.println(line);
                }

                // close our reader
                reader.close();

                */
                
                
               System.out.println(range.toString());
                
                
                
            }
        } catch (IOException e){
            e.printStackTrace();
        }

	}
	
	
	@Test
    public static void getDataOfFile2_Test() {
		
		try {
         SpreadSheet spread = new SpreadSheet(new File("/Users/saisri/eclipse-workspace/Demo_Project/src/test/java/resources/File2.ods"));
         System.out.println("Number of sheets: " + spread.getNumSheets());

         List<Sheet> sheets = spread.getSheets();

         for (Sheet sheet : sheets) {
        	 
             System.out.println("In sheet " + sheet.getName());

             com.github.miachm.sods.Range range = sheet.getDataRange();
             System.out.println(range.toString());
         }
     } catch (IOException e){
         e.printStackTrace();
     }

	}
	
	// For comparing corresponding lines of file1 & file2  line by line  
	@Test
    public static void verifyFileDataComparisonTest() 
	{
		
	}
	
	//For comparing JSON response of request URL of each line in file1 across corresponding lines of file2 
	@Test
	public static void verifyJSONresponseComparisonTest()
	{
		
	}
	
	

	/*	public Wrapper<X, Y> getData(File File1, File File2)
	{
        

	}
	
}*/  




	
}







