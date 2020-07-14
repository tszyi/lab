package com.tszyi.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate4 4.3以前的HibernateUtil寫法.
 * 
 * @author tszyi
 * 
 */
public class HibernateUtil {
  private static SessionFactory sessionFactory;

  static {
    try {
      Configuration configuration = new Configuration();
      configuration.configure();
      ServiceRegistry serviceRegistry =
          new ServiceRegistryBuilder().applySettings(configuration.getProperties())
              .buildServiceRegistry();
      sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
