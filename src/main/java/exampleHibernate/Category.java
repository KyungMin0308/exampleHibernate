package exampleHibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
@Table(name="category")
public class Category { //One Side
	
	@Id
	@GeneratedValue
	@Column(name="category_id")
	private int id; //기본 키(pk)
	
	private String name; //카테고리 이름
	
	//하나의 Category는 여러개의 Product를 가질 수 있기 때문에 Set을 사용
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, fetch=FetchType.LAZY) //Category에서 Product를 가리킴(양방향)
	private Set<Product> products = new HashSet<Product>(); //외래 키(fk)

}
