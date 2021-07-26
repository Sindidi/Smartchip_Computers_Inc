package smartchip.computers.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Produit")
public class Produit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "quantité", nullable = false)
	@Min(value = 1, message = "La  quantité ne doit pas être inférieur à 1")
	//@NotBlank(message = "Veuillez remplir ce champ")
	private int qty;
	
	@Column(name = "Designation", nullable = false)
	@NotBlank(message = "Veuillez remplir ce champ")
	private String design;
	
	@Column(name = "Marque", nullable = false)
	@NotBlank(message = "Veuillez remplir ce champ")
	private String marque;

	@Column(name = "Description", nullable = false)
	@Size(min = 10, max = 65, message = "La description doit comporter minimun 10 et maximun 65 caractères ")
	private String desc;
	
	@Column(name = "Détails", nullable = false)
	@Size(min = 10, max = 255, message = "Les details doit comporter minimun 10 et maximun 255 caractères")
	private String detail;
	

	@Min(value = 100, message = "Le prix ne doit pas être inférieur à 100")
	@Column(name = "Prix",nullable = false)
    private double prix;
	
	@Column(name = "Etat",nullable = true)
    private boolean active = true;
	
	@Column(name = "Catégorie", nullable = false)
	@NotBlank(message = "Veuillez choisir une catégorie")
	private String category;
	
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;
	
	@Column(name = "Image_name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}
	
	
}
