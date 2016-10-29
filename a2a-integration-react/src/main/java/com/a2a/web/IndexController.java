package com.a2a.web;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base controller returning index file
 *
 */
@Controller
public class IndexController {

    @Autowired
    private ServletContext context;
    
    @Autowired
    private ApplicationConfig config;
    
    private String indexContent;

    /**
     * Prepares index.html file
     */
    @PostConstruct
    public void init() {
        try (InputStream is = getClass().getResourceAsStream("./index.html")) {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int length;
            while ((length = is.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            
            //force JS reload
            String version = config.isDevelopmentMode() ? String.valueOf(System.currentTimeMillis()) : config.getApplicationVersion();
            
            indexContent = result.toString("UTF-8");
            indexContent = indexContent.replace("<%ROOT%>", context.getContextPath()).replace("<%VERSION%>", version);
            
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read index.html file");
        }
    }

    
    @RequestMapping("/")
    public ResponseEntity<String> serveIndex() {
        return new ResponseEntity<>(indexContent, HttpStatus.OK);
    }
    
    

}
