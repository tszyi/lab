package com.tszyi.jpa.practice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.tszyi.entity.Student;

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
