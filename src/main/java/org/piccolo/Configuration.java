package org.piccolo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.text.WordUtils;

public class Configuration {
	public enum Setting {
		
		AUTH_SERVICE_URI("http://172.16.68.52:8080/piccoloapi");
		
		private final String defaultValue;
		
		private Setting(String defaultValue) {
			this.defaultValue = defaultValue;
		}
	};
	
	private Properties properties;
	
	public Configuration() {
		this.properties = new Properties();
		String filePath = "piccolo.properties";
		File file = new File(new File("."), filePath);
		try {
			filePath = file.getCanonicalPath();
			InputStream inputStream = new FileInputStream(file);
			try {
				properties.load(inputStream);
			} finally {
				inputStream.close();
			}
		} catch (IOException ioException) {
			System.out.println("Could not load configuration from "+filePath+" - using defaults");
		}
	}
	public String getSetting(Setting setting) {
		return ObjectUtils.defaultIfNull((String)this.properties.get(WordUtils.capitalizeFully(setting.name(), '_')), setting.defaultValue);
	}
}
