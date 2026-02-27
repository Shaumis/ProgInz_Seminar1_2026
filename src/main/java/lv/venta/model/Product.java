package lv.venta.model;

public class Product {
	private int id;
	private float price;
	private String title;
	private Category category;
	private String description;
	private int quantity;
	private static int counter = 1;

	public int getId() {
		return id;
	}

	public void setId() {
		this.id = counter;
		counter++;

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

	public Product() {
		setId();
		setTitle("Riepu");
		setPrice(6.99f);
		setDescription("Laba");
		setCategory(Category.other);
		setQuantity(4);
	}

	public Product(String inputTitle, float inputPrice, String inputDescription, Category inputCategory,
			int inputQuality) {
		setId();
		setTitle(inputTitle);
		setPrice(inputPrice);
		setDescription(inputDescription);
		setCategory(inputCategory);
		setQuantity(inputQuality);
	}

	public String toString() {
		return id + ": " + title + ", " + price + " Eur, " + description + ", " + category + ", " + quantity;
	}
}
