package ar.com.playmedia.model;

public class Platform {
  private Integer id;
  private String description;

  public Platform() {}

  public Platform(
      Integer id,
      String description
  ){
    this.id = id;
    this.description = description;
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
}