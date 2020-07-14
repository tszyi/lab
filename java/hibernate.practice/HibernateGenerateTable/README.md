# 由pojo生成db表

## Hibernate輸出
```
五月 11, 2018 11:37:04 下午 org.hibernate.tool.schema.extract.internal.InformationExtractorJdbcDatabaseMetaDataImpl processGetTableResults
INFO: HHH000262: Table not found: author
五月 11, 2018 11:37:04 下午 org.hibernate.tool.schema.extract.internal.InformationExtractorJdbcDatabaseMetaDataImpl processGetTableResults
INFO: HHH000262: Table not found: author
Hibernate: 
    create table author (
        id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id)
    ) ENGINE=InnoDB
五月 11, 2018 11:37:04 下午 org.hibernate.tool.schema.extract.internal.InformationExtractorJdbcDatabaseMetaDataImpl processGetTableResults
INFO: HHH000262: Table not found: book
五月 11, 2018 11:37:04 下午 org.hibernate.tool.schema.extract.internal.InformationExtractorJdbcDatabaseMetaDataImpl processGetTableResults
INFO: HHH000262: Table not found: book
Hibernate: 
    create table book (
        id bigint not null auto_increment,
        isbn varchar(255) not null,
        title varchar(255) not null,
        author_id bigint,
        primary key (id)
    ) ENGINE=InnoDB
Hibernate: 
    alter table book 
        add constraint FKklnrv3weler2ftkweewlky958 
        foreign key (author_id) 
        references author (id)
五月 11, 2018 11:37:05 下午 org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://localhost:3306/hibernate_test]
```