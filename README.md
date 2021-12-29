# exampleHibernate

### **1. Description**
* **Hibernate**를 사용한 간단한 예제 프로그램


### **2. Environment**
* **Eclispe**
  + Maven Project
    - Create a simple project 선택
* **Database**
  + MySQL
  
  
### **3. Class**
* **Category**
  + 카테고리 정보를 저장하는 클래스
  + One side
* **Product**
  + 제품 정보를 저장하는 클래스
  + Many side
* **TestMain**
  + 프로그램 실행을 위한 메인 코드 작성
  + Product & Category 객체 생성
  + Uni-Direction & Bi-Direction 설정


### **4. 주요 Files**
* **pom.xml**
  + 프로젝트 기본 정보 및 Dependency 관리
  + 사용한 Dependency
    - hibernate-core
    - mysql-connector
    - lombok
* **hibernate.cfg.xml**
  + Hibernate를 사용하기 위한 기본 정보 작성
    - Database 관련 설정
    - Mapping Class 지정
