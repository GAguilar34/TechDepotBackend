package com.techdepot.backend.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {
    
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImages(@RequestParam("files") MultipartFile[] files) {
        List<String> imageUrls = new ArrayList<>();
        
        try {
            // Usar directorio temporal del sistema para Railway
            String uploadDir = System.getProperty("java.io.tmpdir") + "/uploads/products/";
            Path uploadPath = Paths.get(uploadDir);
            
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                
                // Validar que sea una imagen
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.badRequest().body("Solo se permiten archivos de imagen");
                }
                
                // Generar nombre único
                String fileName = UUID.randomUUID().toString() + "_" + 
                                 file.getOriginalFilename().replaceAll("[^a-zA-Z0-9.-]", "_");
                Path filePath = uploadPath.resolve(fileName);
                
                // Guardar archivo
                Files.copy(file.getInputStream(), filePath);
                
                // Devolver la URL completa de Railway
                String baseUrl = "https://techdepotbackend-production.up.railway.app";
                imageUrls.add(baseUrl + "/uploads/products/" + fileName);
            }
            
            return ResponseEntity.ok(imageUrls);
            
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                   .body("Error al subir imágenes: " + e.getMessage());
        }
    }
}