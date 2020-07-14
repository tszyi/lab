# crud

## 問題
由entity生成表可能會遇到這個錯誤
```
Hibernate: 
    
    drop table if exists book
五月 03, 2018 12:28:39 上午 org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@3a12c404] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
Hibernate: 
    
    create table book (
       id integer not null auto_increment,
        isbn varchar(36) not null,
        author varchar(20) not null,
        name varchar(20) not null,
        primary key (id)
    ) type=MyISAM
五月 03, 2018 12:28:39 上午 org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@61a88b8c] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
五月 03, 2018 12:28:39 上午 org.hibernate.tool.schema.internal.ExceptionHandlerLoggedImpl handleException
WARN: GenerationTarget encountered exception accepting command : Error executing DDL via JDBC Statement
org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL via JDBC Statement
	at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:67)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.applySqlString(SchemaCreatorImpl.java:440)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.applySqlStrings(SchemaCreatorImpl.java:424)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.createFromMetadata(SchemaCreatorImpl.java:315)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.performCreation(SchemaCreatorImpl.java:166)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.doCreation(SchemaCreatorImpl.java:135)
	at org.hibernate.tool.schema.internal.SchemaCreatorImpl.doCreation(SchemaCreatorImpl.java:121)
	at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:155)
	at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:72)
	at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:313)
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.build(SessionFactoryBuilderImpl.java:452)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:889)
	at org.hibernate.jpa.HibernatePersistenceProvider.createEntityManagerFactory(HibernatePersistenceProvider.java:58)
	at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:79)
	at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:54)
	at com.tszyi.main.MainClass.main(MainClass.java:19)
Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'type=MyISAM' at line 7
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at com.mysql.jdbc.Util.handleNewInstance(Util.java:406)
	at com.mysql.jdbc.Util.getInstance(Util.java:381)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1030)
	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:956)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3491)
	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3423)
	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:1936)
	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2060)
	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2536)
	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2465)
	at com.mysql.jdbc.StatementImpl.execute(StatementImpl.java:734)
	at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:54)
	... 15 more

五月 03, 2018 12:28:39 上午 org.hibernate.tool.schema.internal.SchemaCreatorImpl applyImportSources
INFO: HHH000476: Executing import script 'org.hibernate.tool.schema.internal.exec.ScriptSourceInputNonExistentImpl@5dcd8c7a'
Hibernate: 
    insert 
    into
        book
        (isbn, author, name) 
    values
        (?, ?, ?)
五月 03, 2018 12:28:39 上午 org.hibernate.engine.jdbc.spi.SqlExceptionHelper logExceptions
WARN: SQL Error: 1146, SQLState: 42S02
五月 03, 2018 12:28:39 上午 org.hibernate.engine.jdbc.spi.SqlExceptionHelper logExceptions
ERROR: Table 'hibernate_test.book' doesn't exist
org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:149)
org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:157)
org.hibernate.internal.ExceptionConverterImpl.convert(ExceptionConverterImpl.java:164)
org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:789)
org.hibernate.internal.SessionImpl.persist(SessionImpl.java:767)
com.tszyi.main.MainClass.main(MainClass.java:23)
五月 03, 2018 12:28:39 上午 org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3306/hibernate_test]
```
上面的log有一段區塊:
```
create table book (
       id integer not null auto_increment,
        isbn varchar(36) not null,
        author varchar(20) not null,
        name varchar(20) not null,
        primary key (id)
    ) type=MyISAM
```
如果MySQL server version是5以後的版本將會在程式由entity生成表時出錯，因為SQL語法就沒通過了。
![dialect-error](https://github.com/tszyi/JPA-practice/blob/master/READMEpic/dialect-error.png)
解決辦法是在Persistence.xml配置文件裡修改方言成為版本5以後支援的，即可修正不報錯了
```
<property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5Dialect" />
```
![success](https://github.com/tszyi/JPA-practice/blob/master/READMEpic/success.png)