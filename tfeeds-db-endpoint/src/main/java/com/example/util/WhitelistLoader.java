package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	public static LocalDateTime nextUpdate = null;
	
	public static HashSet<String> whitelist = new HashSet<String>();

	private static final Logger logger = LoggerFactory.getLogger(WhitelistLoader.class);

	private static String username;

	private static String password;

	private static String url;

	@Autowired
	public WhitelistLoader(@Value("${whitelist.url}") String url, @Value("${ti.user}") String username,
			@Value("${ti.password}") String password) {
		WhitelistLoader.username = username;
		WhitelistLoader.password = password;
		WhitelistLoader.url = url;
	}

	/**
	 * @param url
	 */
	@PostConstruct
	public synchronized static void fetchWhitelist() {
		URL whitelistUrl = null;
		try {
			logger.info("Trying to get whitelist...");
			HttpURLConnection connection = null;
			String userCredentials = username + ":" + password;
			String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
			int code = 0, page = 0;
			boolean last = false;
			String line = null;
			JsonNode node = null;
			BufferedReader reader = null;
			HashSet<String> tempList = new HashSet<String>();

			while (!last) {
				try {
					whitelistUrl = new URL(url + page);
				} catch (MalformedURLException e1) {
					logger.error("", e1);
					break;
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

					if (node.get("first").asBoolean()) {
						nextUpdate = LocalDateTime.now().plusMinutes(15);
					}

					// whether it's the last page
					last = node.get("last").asBoolean();

					if (node.has("content")) {
						node.get("content").findValues("ip").forEach(n -> tempList.add(n.asText()));
					}

				} else {
					logger.error("Error code [{}] encountered when trying to fetch Whitelist.", code);
					break;
				}
				page++;
			}

			if (tempList != null) {
				whitelist.clear();
				whitelist.addAll(tempList);
			}
		} catch (IOException e) {
			logger.error("", e);
		}

	}

	/**
	 * 
	 * @return
	 */
	public static HashSet<String> getWhitelist() {
		// if whitelist is not set or whitelist was updated more than 15 mins
		// ago
		
		try {
			if (nextUpdate.isBefore(LocalDateTime.now())) {
				fetchWhitelist();
				whitelist.add("127.0.0.1");
				logger.info("Whitelist next update after [{}]", nextUpdate.toLocalTime());
			}
		} catch (NullPointerException ex) {
			fetchWhitelist();
		}
		return whitelist;
	}

	/**
	 * 
	 * @param whitelist
	 */
	public void setWhitelist(HashSet<String> whitelist) {
		WhitelistLoader.whitelist = whitelist;
	}

}
