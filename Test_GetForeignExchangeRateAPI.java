package DemoApp.com.demo.app;
import static com.jayway.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test_GetForeignExchangeRateAPI
{
		
	
	 @Test
		public void testgetDataOfExchangeRateAPI() {

			Response response = RestAssured.get("https://v6.exchangerate-api.com/v6/ffb58378b56829df0b0ec552/latest/USD");
			System.out.println(response.statusCode());
			System.out.println(response.asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.statusLine());

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);

		}
	 
	 @Test //Test validate data for positive case sample upon basecode
		public void testForConversionrateValuesOf_INR_BaseCode_Positive() {
		 RestAssured.baseURI = "https://v6.exchangerate-api.com/";
		 	
			Response response = RestAssured.get("/v6/ffb58378b56829df0b0ec552/latest/INR");
			System.out.println(response.statusCode());
			System.out.println(response.asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.statusLine());

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			
			
			 JsonPath jsonPathEvaluator = response.jsonPath();
				// Get specific element from JSON document 
				String documentation = jsonPathEvaluator.get("documentation");
				
				// Validate if the specific JSON element is equal to expected value
				Assert.assertTrue(documentation.equalsIgnoreCase("https://www.exchangerate-api.com/docs"));
				
				
			given().get("https://v6.exchangerate-api.com/v6/ffb58378b56829df0b0ec552/latest/INR").then().
			statusCode(200).
			body("result", equalTo("success")).
			body("base_code", equalTo("INR")).
			body("conversion_rates.INR", equalTo(1)).
			body("conversion_rates.FKP", is(new Float(0.01018))).
			body("conversion_rates.GBP", is(new Float(0.01018))).
			body("conversion_rates.USD", is(new Float(0.01309))).
			body("conversion_rates.BSD", is(new Float(0.01309))).
			body("conversion_rates.DKK", is(new Float(0.09039))).
			log().all();

		}
	 
	 @Test // test validate data for positive case up on basecode
		public void testForConversionrateValuesOf_USD_BaseCode_Positive() {
		 	
		 

			Response response = RestAssured.get("/v6/ffb58378b56829df0b0ec552/latest/USD");
			System.out.println(response.statusCode());
			System.out.println(response.asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.statusLine());

			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 200);
			
			
			 JsonPath jsonPathEvaluator = response.jsonPath();
				// Get specific element from JSON document 
				String documentation = jsonPathEvaluator.get("documentation");
				// Validate if the specific JSON element is equal to expected value
				Assert.assertTrue(documentation.equalsIgnoreCase("https://www.exchangerate-api.com/docs"));
			
			given().get("https://v6.exchangerate-api.com/v6/ffb58378b56829df0b0ec552/latest/USD").then().
			statusCode(200).
			body("result", equalTo("success")).
			body("base_code", equalTo("USD")).
			body("conversion_rates.USD", equalTo(1)).
			body("conversion_rates.FKP", is(new Float(0.7776))).
			body("conversion_rates.GBP", is(new Float(0.7777))).
			body("conversion_rates.INR", is(new Float(76.4236))).
			body("conversion_rates.BSD", is(new Float(1.0000))).
			body("conversion_rates.AED", is(new Float(3.6725))).
			log().all();

		}

	 
	 @Test // test validate data for negative case up on basecode passing incorrect data values
		public void testForConversionrateValuesOf_USD_BaseCode_Negative() {
		 	
		 try {
			 RestAssured.baseURI = "https://v6.exchangerate-api.com/";
		 			
			given().get("https://v6.exchangerate-api.com/v6/ffb58378b56829df0b0ec552/latest/USD").then().
			
			body("result", equalTo("success")).
			body("base_code", equalTo("USD")).
			body("conversion_rates.USD", is(null)).
	
			
			body("conversion_rates.FKP", not(new Float(null))).
			
			body("conversion_rates.GBP", is(new Float(null))).
			body("conversion_rates.INR", is(new Float(null))).
			body("conversion_rates.BSD", is(new Float(null))).
			body("conversion_rates.AED", is(new Float(null)));
			

		}catch(NullPointerException e)// check for case null data values, Should return Null pointer exception
		 {
			System.out.print("Null data test"+e);
		 }

	 }
	 

	 @Test // test validate data for negative case up on basecode passing incorrect data values
		public void testForConversionrateValuesOf_INR_BaseCode_Negative() {
		 	

			given().get("https://v6.exchangerate-api.com/v6/ffb58378b56829df0b0ec552/latest/INR").then().
			
			body("result", equalTo("success")).
			body("base_code", equalTo("INR")).
			body("conversion_rates.INR", not(10)).
	
			
			body("conversion_rates.FKP", not(new Float(0.7776089))).
			
			body("conversion_rates.GBP", not(new Float(0.77770890))).
			body("conversion_rates.USD", not(new Float(76.4236090))).
			body("conversion_rates.BSD", not(new Float(1.000019234))).
			body("conversion_rates.AED", not(new Float(3.67250679)));

	 }
	 

	 @Test // test validate data for negative case up on basecode passing incorrect data values, partial correct data value
		public void testForConversionrateValuesOf_INR_BaseCode_Neutral() {
		 	
			given().get("https://v6.exchangerate-api.com/v6/ffb58378b56829df0b0ec552/latest/INR").then().
			
			body("result", equalTo("success")).
			body("base_code", equalTo("INR")).
			body("conversion_rates.INR", is(1)).
			
			body("conversion_rates.FKP", not(new Float(0.8907770))).
			
			body("conversion_rates.GBP", not(new Float(-0.77770890))).
			body("conversion_rates.USD", is(new Float(0.01309))).
			body("conversion_rates.BSD", not(new Float(1.000019234))).
			body("conversion_rates.AED", not(new Float(-3.67250679))).
			body("conversion_rates.DKK", is(new Float(0.09039)));
			

	 }
	
	 @Test // test validate when given incorrect request URL - Not Found
		public void testForExchangerateAPI_DummyURL_Negative() {
		 	
		 
		 RestAssured.baseURI = "https://v6.exchangerate-api.com/";

			Response response = RestAssured.get("/v6/ffb58378b56829df0b0ec552/latest/USD10");
			
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 404);
			
			given().get("https://v6.exchangerate-api.com/v6/ffb58378b56829df0b0ec552/latest/USD10").then().
			statusCode(404);

		}
	 
	 @Test //Test validate access of GET call data, without passing API key in the request URL, Forbidden  
		public void testForExchangerateAPI_DummyAuth_Negative() {
		 	
		 
		 RestAssured.baseURI = "https://v6.exchangerate-api.com/";

			Response response = RestAssured.get("/v6/latest/USD");
			
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode, 403);
			
			given().get("https://v6.exchangerate-api.com/v6/latest/USD").then().
			statusCode(403);

		}
	}