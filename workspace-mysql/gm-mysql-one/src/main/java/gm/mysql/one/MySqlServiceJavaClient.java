package gm.mysql.one;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
 
import org.json.JSONObject;

public class MySqlServiceJavaClient {
	
	public static void main(String[] args) {
		String jsonString = "";
		try {
 
			// Step1: Let's 1st read file from fileSystem
			// Change MySqlCreateDbRequestJson.txt path here
			InputStream inputStream = new FileInputStream("/tmp/MySqlCreateDbRequestJson.txt");
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(reader);
			String line;
			
			while ((line = br.readLine()) != null) {
				jsonString += line + "\n";
			}
 
			System.out.println("JSON String provided is - \n" + jsonString + "\n");
			
			JSONObject jsonObject = new JSONObject(jsonString);
			System.out.println("JSON object created is - \n" + jsonObject + "\n");

			// Step2: Now pass JSON File Data to REST Service
			try {
				URL url = new URL("http://localhost:8080/gm-mysql-services/MySqlService/json/createdb");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				//while (in.readLine() != null) {
				//}
				
				String inputLine;
				System.out.println("URL Response is - ");
				
				while ((inputLine = in.readLine()) != null) {
		            System.out.println(inputLine);
				}
				
				System.out.println("\nMySql REST Service Invoked Successfully..");
				in.close();
				
			} catch (Exception e) {
				System.out.println("\nError while calling MySql REST Service");
				System.out.println(e);
			}
 
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}