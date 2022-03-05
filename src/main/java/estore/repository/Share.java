package estore.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Shares")
public class Share {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String sender;
	String receiver;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sentdate")
	Date sentDate = new Date();
	String subject;
	String text;
	
	@ManyToOne
	@JoinColumn(name="productid")
	Product product;
}