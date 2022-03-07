package com.example.LAB1Backend;

import com.example.LAB1Backend.dao.CategoryRepository;
import com.example.LAB1Backend.dao.ProductRepository;
import com.example.LAB1Backend.data.Category;
import com.example.LAB1Backend.data.Product;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class Lab1BackendApplication implements CommandLineRunner {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    @Override
    public void run(String... arg) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);
        categoryRepository.save(new Category(null, "computers", null, null));
        categoryRepository.save(new Category(null, "printers", null, null));
        categoryRepository.save(new Category(null, "smartphones", null, null));
        Random rnd = new Random();
        categoryRepository.findAll().forEach(category -> {
            for (int i = 0; i < 10; i++) {
                Product p = new Product();
                p.setName(RandomString.make(10));
                p.setCurrentPrice(100 + rnd.nextInt(10000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setCategory(category);
                productRepository.save(p);
            }
        });

    }




    public static void main(String[] args) {
        SpringApplication.run(Lab1BackendApplication.class, args);
    }

}
