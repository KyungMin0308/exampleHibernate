package exampleHibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestMain {
	
	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		
		//chained method
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		//객체(POJO) 생성
		Category category1 = new Category();
		category1.setName("컴퓨터");
		
		Category category2 = new Category();
		category2.setName("핸드폰");
		
		Product product1 = new Product();
		product1.setName("Notebook");
		product1.setPrice(2000);
		product1.setDescription("Good Notebook!");
		product1.setCategory(category1); //외래 키 설정 : Many[Product] To One[Category] - 단방향
		category1.getProducts().add(product1); //양방향
		
		Product product2 = new Product();
		product2.setName("Laptop");
		product2.setPrice(3000);
		product2.setDescription("Good Laptop!");
		product2.setCategory(category1); //외래 키 설정 : Many[Product] To One[Category] - 단방향
		category1.getProducts().add(product2); //양방향
		
		Product product3 = new Product();
		product3.setName("Phone");
		product3.setPrice(3000);
		product3.setDescription("Good Phone!");
		product3.setCategory(category2); //외래 키 설정 : Many[Product] To One[Category] - 단방향
		category2.getProducts().add(product3); //양방향
		
		//세션 생성
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction(); //트랜잭션 시작
		
		//양방향 : cascade에 의해 연관된 product도 함께 저장됨
		session.save(category1);
		session.save(category2);
		
		//단방향 : cascade에 의해 연관된 category도 함께 저장됨
		/*
		 * session.save(product1); //category1 함께 저장
		 * session.save(product2); //category1 함께 저장
		 * session.save(product3); //category2 함께 저장
		 */		
		

		/* 
		 * session.delete(product1); //객체 삭제 : CascadeType.ALL이기 때문에 category1도 함께 삭제됨 -> 오류 발생
		 * 
		 * [delete 주의사항]
		 * product1을 삭제하게 되면 CascadeType.ALL에 의해 category1도 함께 삭제되면 product2에서 오류가 발생
		 * -> product1과 product2가 category1과 연관되어 있기 때문에
		 * 따라서 CascadeType에 따라 객체의 삭제는 주의가 필요함
		 * 
		 * 아래와 같이 product1의 category를 null로 변경하고 삭제하면 category1은 영향을 받지 않음
		 * product1.setCategory(null);
		 * session.delete(product1);
		 */

		
		//저장된 객체 출력 방법1. get() 사용
		/*
		 * Serializable id1 = session.save(product1); //객체 저장, ID가 리턴됨
		 * savedProduct = session.get(Product.class, id1); //이때는 캐시에 저장된 product 객체 정보를 읽어오는 것 -> tx.commit() 이후 테이블에 저장됨
		 * System.out.println("saved Product" + savedProduct);
		 */
		
		//저장된 객체 출력 방법2. createQuery() 사용
		/*
		 * Query<Product> query = session.createQuery("from Product", Product.class);
		 * List<Product> products = query.getResultList(); 
		 * System.out.println(products);
		 */
		
		tx.commit(); //트랜잭션 종료
		
		session.close();
		sessionFactory.close();
	}

}
