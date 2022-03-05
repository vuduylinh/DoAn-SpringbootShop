package estore.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String name;
	String image = "new.png";
	@Column(name = "unitprice")
	double unitPrice;
	double discount;
	int quantity = 1;
	@Column(name = "productdate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	Date productDate = new Date();
	String description;
	boolean special;
	@Column(name = "likecount")
	int likeCount = 0;
	boolean available = true;
	
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	
	@OneToMany(mappedBy = "product")
	List<Share> shares;
	
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;
	
	public double getPromotePrice() {
		return this.unitPrice * (1 - this.discount);
	}
}