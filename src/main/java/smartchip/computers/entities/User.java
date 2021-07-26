package smartchip.computers.entities;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
@Entity
@Table(name = "user")
public class User implements UserDetails{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name ="Prénom", nullable = false)
	@NotBlank(message = "Veillez remplir ce champ")
	private String fisrtname;
	
	@Column(name ="Nom", nullable = false)
	@NotBlank(message = "Veillez remplir ce champ")
	private String lastname;
	
	@Column(name ="Adresse", nullable = false)
	@NotBlank(message = "Veillez remplir ce champ")
	private String address;
	
	@Column(name ="Email", nullable = false)
	@NotBlank(message = "Veillez remplir ce champ")
	private String mail;
	
	@Column(name ="Téléphone", nullable = false)
	@NotBlank(message = "Veillez remplir ce champ")
	private String phone;

	@Column(name ="Utilisateur")
    private String username;

	@Column(name ="Mot_de_Passe")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private ShoppingCart shoppingCart;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserShipping> userShippingList;

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
	public String getFisrtname() {
		return fisrtname;
	}
	public void setFisrtname(String fisrtname) {
		this.fisrtname = fisrtname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	public List<UserShipping> getUserShippingList() {
		return userShippingList;
	}
	public void setUserShippingList(List<UserShipping> userShippingList) {
		this.userShippingList = userShippingList;
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
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled();
	}

	@SuppressWarnings("unused")
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		return null;
	}
	
}