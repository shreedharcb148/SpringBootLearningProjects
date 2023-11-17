package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiraApplication.class, args);
	
	
		String jiraUrl = "https://rb-tracker.bosch.com/tracker08/secure/RapidBoard.jspa?rapidView=43085&projectKey=DASYTWOHW"; // Replace with your Jira API URL
        String gitToken = "E/opBabyMV1"; // Replace with your Git token
        String userName = "IXL1KOR@bosch.com";
        
        /*
        RestTemplate restTemplate = new RestTemplate();
        boolean connected = false;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(userName, gitToken);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        while (!connected) {
            try {
                ResponseEntity<String> response = restTemplate.exchange(jiraUrl, HttpMethod.GET, requestEntity, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    connected = true;
                    System.out.println("Connected to the URL successfully.");
                } else {
                    System.out.println("Failed to connect. Retrying...");
                    Thread.sleep(5000); // Wait for 5 seconds before retrying
                }
            } catch (Exception e) {
                System.out.println("Failed to connect. Retrying...");
                e.printStackTrace();
                try {
                    Thread.sleep(5000); // Wait for 5 seconds before retrying
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    
	*/
        /*
		
		 try {
	            
	           

	            // Encode the username and password in Base64
	            String base64Credentials = Base64.getEncoder().encodeToString((userName + ":" + gitToken).getBytes());

	            
	            // Create a URL object with the API URL
	            URL url = new URL(jiraUrl);

	            // Open a connection to the URL
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	            // Set the request method to GET
	            conn.setRequestMethod("GET");

	            // Set the "Authorization" header with the Base64-encoded credentials
	            conn.setRequestProperty("Authorization", "Basic " + base64Credentials);

	            // Get the response code
	            int responseCode = conn.getResponseCode();
	            System.out.println("Response ---"+responseCode);
	            if (responseCode == 200) {
	                // If the response code is 200 (OK), read and print the response
	                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                String inputLine;
	                StringBuilder response = new StringBuilder();

	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }

	                in.close();

	                System.out.println("Response from Bitbucket API:\n" + response.toString());
	            } else {
	                System.out.println("HTTP GET request failed with response code: " + responseCode);
	            }

	            conn.disconnect();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        	*/
       

        // Encode the username and password in Base64
        String base64Credentials = Base64.getEncoder().encodeToString((userName + ":" + gitToken).getBytes());

        // URL of the Bitbucket API endpoint you want to access
        
        int maxRetries = 3; // Maximum number of retries
        int retries = 0;

        URL url = null;
        HttpURLConnection conn = null;

        try {
            url = new URL(jiraUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (retries < maxRetries) {
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Basic " + base64Credentials);
                int responseCode = conn.getResponseCode();

                if (responseCode == 200 || responseCode == 302) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    in.close();

                    System.out.println("Response from Bitbucket API:\n" + response.toString());
                    break; // Exit the loop if status is 200 or 302
                } else {
                    System.out.println("HTTP GET request failed with response code: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            retries++;
            

            if (retries < maxRetries) {
                System.out.println("Retrying connection...");
                try {
                    // Wait for a few seconds before the next retry
                    Thread.sleep(5000); // Adjust the sleep duration as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (retries >= maxRetries) {
            System.out.println("Failed to connect to the Bitbucket API after " + maxRetries + " retries.");
        }
	
	}
	}


