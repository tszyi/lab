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
