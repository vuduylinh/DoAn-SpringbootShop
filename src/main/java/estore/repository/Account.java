package estore.repository;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Accounts")
public class Account {
	@Id
	String username;
	String password;
	String fullname;
	String mobile;
	String email;
	String address;
	String photo = "new.png";
	boolean activated;
	
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	public boolean hasRole(Role role) {
		if(this.authorities != null) {
			return this.authorities.stream()
					.anyMatch(a -> a.getRole().getId().equals(role.getId()));
		}
		return false;
	}
}