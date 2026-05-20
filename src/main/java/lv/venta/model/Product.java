package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Table(name = "ProductTable")
@Entity
public class Product {
	
	@Column(name = "Id")
	@Id //šī kolonna būs kā PK
	@GeneratedValue(strategy = GenerationType.AUTO)//auto increment datubāzē izveidos nunmuru pec kartas un unikalu id
	private int id;
	
	@Min(0)
	@Max(100000)
	@Column(name = "Price")
	private float price;
	
	@Column(name = "Title", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-ZĒŪĪĻĶĢŠĀŽČŅ]{1}[a-zēūīļķģšāžčņ]{2,20}")
	private String title;
	
	@Column(name = "Category")
	@Enumerated(EnumType.STRING)
	@NotNull
	private Category category;
	
	@Column(name = "Description")
	@NotNull
	@NotEmpty
	private String description;
	
	@Column(name = "Quantity")
	@Min(0)
	@Max(100000)
	private int quantity;
	

	public int getId() {
		return id;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	
	//TODO Izmantot validāciju anotācijas
	public void setTitle(String title) {
		this.title = title;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product()
	{
		/*setId();
		setTitle("Galds");
		setPrice(78.99f);
		setDescription("Brūns un liels");
		setCategory(Category.other);
		setQuantity(4);*/
	}
	
	public Product(String inputTitle, float inputPrice, String inputDescription, Category inputCategory,
			int inputQuantity) {
		setTitle(inputTitle);
		setPrice(inputPrice);
		setDescription(inputDescription);
		setCategory(inputCategory);
		setQuantity(inputQuantity);
	}
	
	public String toString() {
		return id + ": " + title + ", " + price + " EUR, " + description + ", " + category + ", " + quantity; 
	}
	
	
	
	
	
	
	
	
	
	
}
