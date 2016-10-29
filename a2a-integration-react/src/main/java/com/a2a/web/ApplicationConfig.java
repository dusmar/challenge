package com.a2a.web;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class ApplicationConfig {
    
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);
    
    @Autowired
    private ServletContext servletContext;

    @Value("${configurationType:}")
    private String configurationType;
    
    private String appVersion;
    
    @PostConstruct
    public void init() {
        Map<String, String> manifest = readManifest();
        
        appVersion = Objects.toString(manifest.get("appVersion"), "N/A");
    }
    
    /**
     * Indicates whether this application is run in development mode.
     * (Flag configurationType=development)
     * 
     * @return
     */
    public boolean isDevelopmentMode() {
        return "development".equalsIgnoreCase(configurationType);
    }


    /**
     * Returns the application version
     * 
     * @return
     */
    public String getApplicationVersion() {
        return appVersion;
    }



    private Map<String, String> readManifest() {
        //read manifest
        Map<String, String> manifest = new HashMap<>();
        try {
            URL url = servletContext.getResource("/META-INF/MANIFEST.MF");
            log.info(String.format("Trying to read manifest %s", url));
            try {
                Properties p = new Properties();
                p.load(url.openStream());

                p.forEach((key, value) -> manifest.put(String.valueOf(key), String.valueOf(value)));

            } catch (IOException e) {
                log.error(String.format("Cannot read manifest %s", url), e);
            }
            
        } catch (Exception e) {
            log.error("Cannot read manifest", e);
        }
        return manifest;
    }
}
