package com.techdepot.backend.favorites.controller;

import com.techdepot.backend.favorites.dto.FavoriteRequest;
import com.techdepot.backend.favorites.model.Favorite;
import com.techdepot.backend.favorites.service.FavoriteService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@CrossOrigin
public class FavoriteController {

    FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody FavoriteRequest request) {
        try {
            service.addFavorite(request);
            return ResponseEntity.ok("Producto agregado a favoritos.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        try {
            service.removeFavorite(id);
            return ResponseEntity.ok("Producto eliminado de favoritos.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getByCustomer(@PathVariable Long customerId) {
        try {
            List<Favorite> favorites = service.getFavoritesByCustomer(customerId);
            return ResponseEntity.ok(favorites);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}