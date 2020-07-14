# 不需要cfg配置文件的範例程式

```
Configuration cfg = new Configuration();
    cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate_test")
      .setProperty("hibernate.connection.username", "tszyi")
      .setProperty("hibernate.connection.password", "tszyi")
      .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect")
      .setProperty("hibernate.driver_class", "com.mysql.jdbc.Driver")
      .setProperty("hibernate.hbm2ddl.auto", "update")
      .setProperty("hibernate.show_sql", "true")
      .setProperty("hibernate.format_sql", "true")
      .addAnnotatedClass(Author.class)
      .addAnnotatedClass(Book.class);
SessionFactory sf = cfg.buildSessionFactory();
```

## `Configuration`類別
此類別專門處理應用程式連接到DB的配置，常使用的方法如：
  - `setProperty(key, value)`
方法設置`hibernate.cfg.xml`的那些`<property name=key>value</property>`
  - `addClass(persistentClass)`
本方法適用於以`hbm.xml`配置entity的那些類別(`persistentClass`)
  - `addAnnotatedClass(annotatedClass)` 
本方法適用於以annotation配置entity的那些類別

基本上，用法跟撰寫`hibernate.cfg.xml`很像，只是把配置檔裡的property及class mapping用hard-code方式寫在程式碼裡，然後完成這兩個主要配置後就調用`buildSessionFactory()`來根據前面設置好給`Configuration`實例建立`Session`工廠。