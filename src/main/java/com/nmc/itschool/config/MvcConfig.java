package com.nmc.itschool.config;

import com.nmc.itschool.controller.rest.FileUploadController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

//    private static final String UPLOAD_DIR =  System.getProperty("user.dir") + "/uploads";
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/uploads/**")
//                .addResourceLocations("file:" + UPLOAD_DIR + "/");
//    }
    @Value("${app.image.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osName = System.getProperty("os.name").toLowerCase();
        log.info("osName operating system: {}", osName);
        if (osName.contains("win")) {
            registry.addResourceHandler("/resource/**")
                    .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/");
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
            registry.addResourceHandler("/resource/**")
                    .addResourceLocations("file:" + uploadDir + "/");
        } else {
            log.info("Unknown operating system: {}", osName);
        }

    }
}