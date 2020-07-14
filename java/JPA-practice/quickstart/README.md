# Quickstart
快速入門

## 環境
 - JDK 1.8
 - MAVEN 3.5.3
 - JPA 2.2 (搭配Hibernate-entitymanager)

## JPA的快速入門
`JpaDemo.java`簡單示範插入一筆新紀錄到表裡，使用起來很像在操作Hibernate的`SessionFactory`、`Session`。
```
/**
 * 使用JPA的示範程式.
 * 
 * @author tszyi
 * 
 */
public class JpaDemo {
  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("demo");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    // 新增一筆紀錄
    Student student = new Student();
    student.setName("Leo");
    student.setNo("B003040059");
    student.setPhone("0900088889");
    entityManager.persist(student);

    entityManager.getTransaction().commit();
    entityManager.close();
    entityManagerFactory.close();
  }
}
```
### student model
```
package com.tszyi.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO對DB的表.
 * 
 * @author tszyi
 * 
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
  private static final long serialVersionUID = -7394263595908860228L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "no")
  private String no;
  @Column(name = "phone")
  private String phone;

  public Student() {
    super();
  }

  public Student(Long id, String name, String no, String phone) {
    super();
    this.id = id;
    this.name = name;
    this.no = no;
    this.phone = phone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", no=" + no + ", phone=" + phone + "]";
  }

}
```

### pom.xml
需要的依賴如
```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.6</version>
</dependency>
<dependency>
    <groupId>javax.persistence</groupId>
    <artifactId>javax.persistence-api</artifactId>
    <version>2.2</version>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.2.12.Final</version>
</dependency>
```

### Persistence.xml
**Persistence.xml須放置在`META-INF`目錄裡**，參考配置如下
```
<?xml version="1.0" encoding="UTF-8"?>

<persistence version="2.0"
	xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence 
   http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">

	<persistence-unit name="demo"
		transaction-type="RESOURCE_LOCAL">
		<provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
		<class>package com.tszyi.entity.Student</class>

        <!-- 以MySQL示範 -->
		<properties>
			<property name="hibernate.connection.url" value="jdbc:mysql://{dbhost}:{port}/{dbname}" />
			<property name="hibernate.connection.user" value="{dbuser}" />
			<property name="hibernate.connection.password" value="{dbpassword}" />
			<property name="hibernate.connection.driver_class" value="com.mysql.jdbc.Driver" />
			<property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
		</properties>

	</persistence-unit>
</persistence>
```