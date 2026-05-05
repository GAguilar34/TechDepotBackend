package com.techdepot.backend.customer.service;

import com.techdepot.backend.customer.model.Customer;
import com.techdepot.backend.customer.model.UserType;
import com.techdepot.backend.customer.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service //Le dice a Spring boot que es parte de la logica de negocio

public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Validar password
    public boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("La contraseña no puede estar vacia.");
        }

        boolean containsCharacters = password.matches(".*[#@$%^&*]*.");
        boolean containsNumbers = password.matches(".*[123456789]*.");
        boolean eldestAeight = password.length() >= 8;

        boolean validPassword = containsCharacters && containsNumbers && eldestAeight;

        return validPassword;
    }

    //Validamos el correo
    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("El email no puede estar vacio.");
        }

        boolean containsAt = email.contains("@");
        boolean containsPoint = email.contains(".");
        boolean eldestAsix = email.length() > 6;

        int postAt = email.indexOf("@");
        int postDot = email.indexOf(".");

        boolean orderEmail = postAt > 0 && postDot > postAt + 1 && postDot < email.length() - 1;

        boolean isValid = containsAt && containsPoint && eldestAsix && orderEmail;

        return isValid;
    }

    //Validaciones al crear un cliente
    public void createCustomer(Customer customer) {

        try {
            if (customer.getId() == null || customer.getAge() == 0 || customer.getName() == null || customer.getEmail() == null
                    || customer.getGender() == null || customer.getPhone() == null || customer.getPassword() == null) {
                throw new RuntimeException("Ningun campo puede estar vacio.");
            }

            if (customer.getAge() < 18) {
                throw new RuntimeException("El registro debe hacerlo alguien mayor de edad.");
            }

            if (customer.getPhone().length() < 10 || customer.getPhone().length() > 10) {
                throw new RuntimeException("El numero telefonico excede o le faltan caracteres.");
            }

            boolean validate = validateEmail(customer.getEmail());

            boolean validatePass = validatePassword(customer.getPassword());

            if (!validate) {
                throw new RuntimeException("EL correo no es valido.");
            }

            if (!validatePass) {
                throw new RuntimeException("Contraseña invalida.");
            }

            if (customer.getUserType() == UserType.VENDEDOR
                    && (customer.getAccountNumber() == null || customer.getAccountNumber().isEmpty())) {
                throw new RuntimeException("Los vendedores deben ingresar su numero de cuenta CLABE.");
            }

            Optional<Customer> existing = customerRepository.findByEmail(customer.getEmail());
            if (existing.isPresent()) {
                throw new RuntimeException("El correo ya esta registrado.");
            }

            customerRepository.save(customer);

        } catch (Exception e) {
            throw new RuntimeException("No se pudo registrar el cliente" + e.getMessage());
        }
    }

    //Validaciones al actualizar un cliente
    public void updateCustomer(Long id, Customer newData) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new RuntimeException("No se pudo encontrar el cliente");
        } else {
            Customer c = customer.get();

            if (newData.getName() != null) {
                c.setName(newData.getName());
            }

            if (newData.getEmail() != null) {

                boolean validate = validateEmail(newData.getEmail());

                if (!validate) {
                    throw new RuntimeException("EL correo no es valido.");
                }

                c.setEmail(newData.getEmail());
            }

            if (newData.getAge() != 0) {

                if (newData.getAge() < 18) {
                    throw new RuntimeException("El registro debe hacerlo alguien mayor de edad.");
                }
                c.setAge(newData.getAge());
            }

            if (newData.getGender() != null) {
                c.setGender(newData.getGender());
            }

            if (newData.getPhone() != null) {

                if (newData.getPhone().length() < 10 || newData.getPhone().length() > 10) {
                    throw new RuntimeException("El numero telefonico excede o le faltan caracteres.");
                }

                c.setPhone(newData.getPhone());
            }

            if (newData.getPassword() != null) {
                boolean validatePass = validatePassword(newData.getPassword());
                if (!validatePass) {
                    throw new RuntimeException("Contraseña invalida.");
                }
                c.setPassword(newData.getPassword());
            }

            if (newData.getAccountNumber() != null) {
                c.setAccountNumber(newData.getAccountNumber());
            }
            
            customerRepository.save(c);
        }
    }

    //Validaciones al eliminar un cliente
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new RuntimeException("No se pudo encontrar el cliente");
        } else {
            Customer c = customer.get();

            customerRepository.delete(c);
        }
    }

    //Obtener Clientes ordenados por su Id 
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll(Sort.by("id"));
    }

    public Customer login(String email, String password) {
        // Buscamos el cliente por email
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isEmpty()) {
            throw new RuntimeException("El correo no esta registrado.");
        }

        Customer c = customer.get();

        // Verificamos la contraseña
        if (!c.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta.");
        }

        return c;
    }
}
