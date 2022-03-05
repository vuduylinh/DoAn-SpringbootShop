package estore.repository;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Categories")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@NotBlank(message = "Không để trống tên loại")
	String name;
	@NotBlank(message = "Không để trống tên loại tiếng việt")
	@Column(name = "namevn")
	String nameVn;
	
	@OneToMany(mappedBy = "category")
	List<Product> products;
}