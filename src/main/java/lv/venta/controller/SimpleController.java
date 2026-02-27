package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.ProgInzSeminar1Application;
import lv.venta.model.Category;
import lv.venta.model.Product;

@Controller
public class SimpleController {

	private final ProgInzSeminar1Application progInzSeminar1Application;

	ArrayList<Product> allProducts = new ArrayList<>(
			Arrays.asList(new Product("Wafele", 100f, "Mēma", Category.other, 19000),
					new Product("Suns", 920.22f, "Labs", Category.other, 1),
					new Product("Āmurs", 22f, "Sit ok", Category.other, 4)));

	SimpleController(ProgInzSeminar1Application progInzSeminar1Application) {
		this.progInzSeminar1Application = progInzSeminar1Application;
	}

	@GetMapping("/showmessage")
	public String getShowMessage() {
		System.out.println("Controller ir izsaukts");
		return "msg"; // parāda msg.html lapu
	}

	@GetMapping("/showdata")
	public String getShowData(Model model) {
		System.out.println("Controller NR.2");
		Random rand = new Random();
		String text = "Mikusam ir " + rand.nextInt() + " dollaroos!";
		model.addAttribute("package", text);
		return "data";
	}

	@GetMapping("/getproduct")
	public String getGetProduct(Model model) {
		System.out.println("Reku Manta");
		Product product = new Product("Kompis", 1000f, "Kreizy", Category.computer, 10);
		model.addAttribute("package", product);
		return "prod";
	}

	@GetMapping("/getallproducts")
	public String getGetAllProducts(Model model) {
		model.addAttribute("package", allProducts);
		return "showall";
	}

	@GetMapping("/getallproducts/{id}")
	public String getGetAllProductsById(@PathVariable(name = "id") int id, Model model) {
		if (id < 0) {
			model.addAttribute("package", "nebuus");
			return "error-page";
		}
		for (Product tempP : allProducts) {
			if (tempP.getId() == id) {
				model.addAttribute("pacakge", tempP);
				return "show-one-product";
			}
		}
		model.addAttribute("package", "Produkts nava");
		return "error-page";
	}

	@GetMapping("/allproducts")
	public String getGetAllProducts2ById(@RequestParam(name = "id") int id, Model model) {
		if (id < 0) {
			model.addAttribute("package", "nebuus");
			return "error-page";
		}
		for (Product tempP : allProducts) {
			if (tempP.getId() == id) {
				model.addAttribute("pacakge", tempP);
				return "show-one-product";
			}
		}
		model.addAttribute("package", "Produkts nava");
		return "error-page";
	}

	@GetMapping("/add")
	public String getAddProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories",Category.values());
		return "add-one-product";
	}

	@PostMapping("/add")
	public String postAddProduct(Product product) {
		System.out.print(product);
		Product newProduct = new Product(product.getTitle(), product.getPrice(),product.getDescription(),product.getCategory(),product.getQuantity());
		allProducts.add(newProduct);
		return "redirect:/getallproducts";
	}
	@GetMapping("/update")
	public String getUpdateProductById(@RequestParam(name = "id")int id, Model model) {
		if (id < 0) {
			model.addAttribute("package", "nebuus");
			return "error-page";
		}
		for(Product tempP : allProducts) {
			if(tempP.getId()==id) {
				model.addAttribute("product",tempP);
				return "update-one-product";
			}
		}
		model.addAttribute("package","nuuuh");
		return "error-page";
	}
	@PostMapping("/update")
	public String postUpdateProductById(@RequestParam(name = "id")int id, Product product) {
		for(Product tempP : allProducts) {
			if(tempP.getId()==id) {
				tempP.setTitle(product.getTitle());
				tempP.setDescription(product.getDescription());
				tempP.setPrice(product.getPrice());
				tempP.setCategory(product.getCategory());
				tempP.setQuantity(product.getQuantity());
			}
		}
		return "redirect:/getallproducts";
	}
}
