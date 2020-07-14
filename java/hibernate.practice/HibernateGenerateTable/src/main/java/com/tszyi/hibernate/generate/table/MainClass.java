package com.tszyi.hibernate.generate.table;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainClass {
  public static void main(String[] args) {
    Configuration cfg = new Configuration().configure();
    SessionFactory sf = cfg.buildSessionFactory();
    sf.close();
  }
}
