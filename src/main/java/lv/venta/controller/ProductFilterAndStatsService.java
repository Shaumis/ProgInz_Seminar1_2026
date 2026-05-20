package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Category;
import lv.venta.model.Product;
import lv.venta.service.IProductFilterAndStatsService;

@Controller
@RequestMapping("/product/filter")
public class ProductFilterAndStatsService {
//TODO uztaisīs 4 getmapping kontrolieru funkcijas - katru savai filtracijas servisa funkcijai
	
	@Autowired
	private IProductFilterAndStatsService service;
	
	@GetMapping("/price/{threshold}") //localhost:8080/product/filter/price/5.7
	public String getControllerFilterByPrice(@PathVariable(name = "threshold") float threshold,
			Model model) {
		try
		{
			ArrayList<Product> resultFromDB = service.filterByPriceLessThan(threshold);
			model.addAttribute("package", resultFromDB);
			model.addAttribute("info", "Produkti, kuru cena ir mazāka par " + threshold + " eur");
			return "show-all-products";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/category/{category}")//localhost:8080/product/filter/category/fruit
	public String getControllerFilterByCategory(@PathVariable(name = "category") Category category,
			Model model) {
		
		try
		{
			ArrayList<Product> resultFromDB = service.filterByCategory(category);
			model.addAttribute("package", resultFromDB);
			model.addAttribute("info", "Produkti, kuri ietilpst " + category + " kategorijā");
			return "show-all-products";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
		
	}
	
	
	@GetMapping("/keyword/{keyword}")//localhost:8080/product/filter/keyword/Ora
	public String getControllerFilterByKeyword(@PathVariable(name = "keyword") String keyword,
			Model model) {
		try
		{
			ArrayList<Product> resultFromDB = service.filterByKeyword(keyword);
			model.addAttribute("package", resultFromDB);
			model.addAttribute("info", "Produkti, kuri atbilst " + keyword + " atslēgas vārdam");
			return "show-all-products";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/avgprice")//localhost:8080/product/filter/avgprice
	public String getControllerCalculateAVGPrice(Model model) {
		try
		{
			float resultFromDB = service.calculateAVGPrice();
			model.addAttribute("package", "Vidējā cena ir " + resultFromDB + " eur");
			return "data";
			
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	
}
