package com.techdepot.backend.product.service;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.customer.repository.CustomerRepository;
import com.techdepot.backend.product.model.Product;
import com.techdepot.backend.product.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service //Le dice a Spring boot que es parte de la logica de negocio
public class ProductService {

    private static final int MAX_PRODUCT_IMAGES = 20;

    ProductRepository productRepository;
    CustomerRepository customerRepository;

    public ProductService(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }
    
    private boolean hasValidImages(Product product) {
        return product.getImageUrls() != null
                && !product.getImageUrls().isEmpty()
                && product.getImageUrls().size() <= MAX_PRODUCT_IMAGES
                && product.getImageUrls().stream().allMatch(url -> url != null && !url.isBlank());
    }

    //Validar producto
    public void createProduct(Product product) {
        try {
            if (product.getNameProduct() == null || product.getDescription() == null
                    || product.getAmount() < 0 || product.getCategory() == null 
                    || product.getState() == null || product.getPrice() < 0 || !hasValidImages(product)) {
                throw new RuntimeException("Por favor llene todos los campos.");
            }

            if (product.getDescription().length() < 100) {
                throw new RuntimeException("La descripcion es demasiado corta.");
            }

            if (product.getNameProduct().length() < 4) {
                throw new RuntimeException("El nombre del producto es demasiado corto.");
            }

            if (product.getSeller() != null && product.getSeller().getId() != null) {
                Customer seller = customerRepository.findById(product.getSeller().getId())
                        .orElseThrow(() -> new RuntimeException("El vendedor no existe."));
                product.setSeller(seller);
            }

            productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el producto.");
        }
    }

    //Validar datos al actualizar un producto
    public void updateProduct(Long id, Product newData) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("No se pudo encontrar el producto.");
        } else {
            Product p = product.get();

            if (newData.getNameProduct() != null) {

                if (newData.getNameProduct().length() < 4) {
                    throw new RuntimeException("El nombre del producto es demasiado corto.");
                }
                p.setNameProduct(newData.getNameProduct());
            }

            if (newData.getDescription() != null) {
                if (newData.getDescription().length() < 100) {
                    throw new RuntimeException("La descripcion es demasiado corta.");
                }
                p.setDescription(newData.getDescription());
            }

            if (newData.getAmount() >= 0) {
                p.setAmount(newData.getAmount());
            }

            if (newData.getCategory() != null) {
                p.setCategory(newData.getCategory());
            }
            
            if (newData.getState() != null){
                p.setState(newData.getState());
            }

            if (newData.getPrice() >= 0) {
                p.setPrice(newData.getPrice());
            }

            if (newData.getImageUrls() != null) {
                if (!hasValidImages(newData)) {
                    throw new RuntimeException("El producto debe tener entre 1 y 20 imagenes validas.");
                }
                p.setImageUrls(newData.getImageUrls());
            }

            productRepository.save(p);
        }
    }

    //Validar producto al eliminarlo
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("No se pudo encontrar el producto.");
        } else {
            Product p = product.get();

            productRepository.delete(p);
        }
    }

    //Obtener productos ordenados por su id
    public List<Product> getAllProduct() {
        return productRepository.findAll(Sort.by("id"));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar el producto."));
    }

    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
}
