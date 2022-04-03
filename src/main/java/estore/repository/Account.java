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
	String photo;
	boolean activated;
	
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
	public boolean hasRole(Role role) { // kiểm tra tài khoản có vai trò nào?
		if(this.authorities != null) {
			return this.authorities.stream() // nếu có authorities đổi sang stream() để dùng lambda
				.anyMatch(a -> a.getRole().getId().equals(role.getId()));
//				so sánh equals vai trò như nhau thì return true.
		}
		return false;
	}
}