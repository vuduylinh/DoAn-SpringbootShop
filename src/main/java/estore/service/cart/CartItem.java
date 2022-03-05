package estore.service.cart;

import estore.repository.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
	Product product;
	int qty;
	
	public double getAmount() {
		return qty * product.getPromotePrice();
	}

	public void increase() {
		this.qty++;
	}
}
