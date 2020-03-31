package ar.com.playmedia.model;

public class Product {
    private Integer id;
    private String description;
    private Float price;
    private Integer quantity;
    private Integer category;
    private Integer platform;

    public Product() {}
	
	public Product (
		Integer id,
		String description,
		Float price,
		Integer quantity,
		Integer category,
        Integer platform
	) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
        this.platform = platform;
	}

    public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

    public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

    public Float getPrice() {
		return price;
	}

	
	public void setPrice(Float price) {
		this.price = price;
	}

    public Integer getQuantity() {
		return quantity;
	}

	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

    public Integer getCategory() {
		return category;
	}

	
	public void setCategory(Integer category) {
		this.category = category;
	}

    public Integer getPlatform() {
		return platform;
	}

	
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}


}