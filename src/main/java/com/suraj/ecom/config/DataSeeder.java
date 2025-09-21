package com.suraj.ecom.config;

import com.suraj.ecom.entity.Brand;
import com.suraj.ecom.entity.Product;
import com.suraj.ecom.entity.Type;
import com.suraj.ecom.repository.BrandRepository;
import com.suraj.ecom.repository.ProductRepository;
import com.suraj.ecom.repository.TypeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Log4j2
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;

    public DataSeeder(ProductRepository productRepository, BrandRepository brandRepository, TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if the database is already populated
        if (productRepository.count() == 0) {
            log.info("Database is empty. Seeding initial data...");

            // --- Create Brands ---
            Brand nike = brandRepository.save(Brand.builder().name("Nike").build());
            Brand adidas = brandRepository.save(Brand.builder().name("Adidas").build());
            Brand puma = brandRepository.save(Brand.builder().name("Puma").build());

            // --- Create Types ---
            Type shoes = typeRepository.save(Type.builder().name("Shoes").build());
            Type apparel = typeRepository.save(Type.builder().name("Apparel").build());
            Type accessories = typeRepository.save(Type.builder().name("Accessories").build());

            // --- Create Products ---
            List<Product> products = List.of(
                    Product.builder().name("Air Max 90").description("Classic Nike running shoe.").price(12000L).pictureUrl("images/airmax90.png").brand(nike).type(shoes).build(),
                    Product.builder().name("Ultraboost 21").description("Comfortable Adidas running shoe.").price(18000L).pictureUrl("images/ultraboost.png").brand(adidas).type(shoes).build(),
                    Product.builder().name("Suede Classic").description("Iconic Puma lifestyle sneaker.").price(6500L).pictureUrl("images/pumasuede.png").brand(puma).type(shoes).build(),
                    Product.builder().name("Tech Fleece Hoodie").description("Lightweight and warm Nike hoodie.").price(9000L).pictureUrl("images/techfleece.png").brand(nike).type(apparel).build(),
                    Product.builder().name("Tiro 21 Pants").description("Adidas training pants.").price(4500L).pictureUrl("images/tiro21.png").brand(adidas).type(apparel).build(),
                    Product.builder().name("Pro Training Cap").description("Nike Dri-FIT cap.").price(2500L).pictureUrl("images/nikecap.png").brand(nike).type(accessories).build()
            );

            productRepository.saveAll(products);
            log.info("Finished seeding data. {} products added.", products.size());
        } else {
            log.info("Database already contains data. Skipping seeding.");
        }
    }
}