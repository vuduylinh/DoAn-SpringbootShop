package estore.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Orderdetails")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@Column(name = "unitprice")
	double unitPrice;
	int quantity;
	double discount;
	
	@ManyToOne
	@JoinColumn(name = "Orderid")
	Order order;
	
	@ManyToOne
	@JoinColumn(name = "Productid")
	Product product;
	
	public OrderDetail(Order order, Product product, int quantity) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		// copy unit price and discount from product
		this.unitPrice = product.getUnitPrice();
		this.discount = product.getDiscount();
	}
}