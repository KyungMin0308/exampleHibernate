package exampleHibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
@Table(name="product")
public class Product { //Many Side

	@Id
	@GeneratedValue
	@Column(name="product_id")
	private int id; //기본 키(pk)
	
	private String name; //제품명
	
	private int price; //가격
	
	private String description; //제품 설명
	
	//@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER) //Product에서 Category를 가리킴(단방향)
	@ManyToOne //양방향에서는 One Side에서 Cascade를 설정
	@JoinColumn(name="category_id")
	private Category category; //외래 키(fk)
	
}
