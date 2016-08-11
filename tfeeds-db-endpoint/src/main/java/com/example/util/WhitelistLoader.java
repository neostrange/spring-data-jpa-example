package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Loads whitelist
 * 
 * @author needle
 *
 */
@Component
public class WhitelistLoader {

	public static List<String> whitelist = null;

	private static Semaphore mutex = new Semaphore(1);

	@Value(value = "${ti.user}")
	private String username;
	@Value(value = "${ti.password}")
	private String password;

	public void fetchWhitelist(String url) {
		try {
			mutex.acquire();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		URL whitelistUrl = null;

		try {
			HttpURLConnection connection = null;
			String userCredentials = username + ":" + password;
			String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
			int code = 0, page = 0;
			boolean last = false;
			String line = null;
			JsonNode node = null;
			BufferedReader reader = null;
			System.out.println("Getting response...");
			while (!last) {
				try {
					whitelistUrl = new URL(url + page);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				connection = (HttpURLConnection) whitelistUrl.openConnection();
				connection.setRequestProperty("Authorization", basicAuth);
				connection.setUseCaches(false);

				code = connection.getResponseCode();
				if (code == 200) {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while ((line = reader.readLine()) != null) {
						node = new ObjectMapper().readValue(line, JsonNode.class);
					}

					// whether it's the last page
					last = node.get("last").asBoolean();

					if (node.has("content")) {
						whitelist = new ArrayList<String>();
						System.out.println(node.toString());
						node.get("content").findValues("ip").forEach(n -> whitelist.add(n.asText()));
					}
				} else {
					System.out.println("Error Code: " + code);
					System.out.println(connection.getResponseMessage());
				}
				page++;
			} 
			mutex.release();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<String> getWhitelist() {
		do {
			System.out.println("Checking to see if mutex is available.");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!(mutex.availablePermits() > 0));
		System.out.println("Whitelist size: " + whitelist.size());
		return whitelist;
	}
	
	public static String getWhitelist(Boolean tostring) {
		do {
			System.out.println("Checking to see if mutex is available.");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!(mutex.availablePermits() > 0));
		System.out.println("Whitelist size: " + whitelist.size());
		return whitelist.toString();
	}

	public void setWhitelist(List<String> whitelist) {
		WhitelistLoader.whitelist = whitelist;
	}

}
