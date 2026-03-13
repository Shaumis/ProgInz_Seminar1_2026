package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Category;
import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class ProgInzSeminar1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzSeminar1Application.class, args);

	}

	@Bean
	public CommandLineRunner saveDataInDB(IProductRepo prodRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				Product p1 = new Product("Ābols", 0.99f, "Salds un garšīgs", Category.computer, 10);
				Product p2 = new Product("Burkāns", 2.84f, "Oranžš", Category.medicine, 40);
				Product p3 = new Product("Vīnogas", 4.99f, "Violetas", Category.other, 3);

				prodRepo.save(p1);
				prodRepo.save(p2);
				prodRepo.save(p3);

				System.out.println("Ierakstu skaits :" + prodRepo.count());
				System.out.println("Prod ar id=1 " + prodRepo.findById(1).get());
				System.out.println("All: " + prodRepo.findAll());
				prodRepo.deleteById(1);
				System.out.println("All: " + prodRepo.findAll());
				Product productForUpdate = prodRepo.findById(2).get();
				productForUpdate.setPrice(9.99f);
				prodRepo.save(productForUpdate);
				System.out.println("All: " + prodRepo.findAll());
			}
		};
	}

}
