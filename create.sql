
    create table `ORDER` (
       id integer not null auto_increment,
        instante datetime,
        client_id integer,
        endereco_Entrega_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table Address (
       id integer not null auto_increment,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        logradouro varchar(255),
        number varchar(255),
        city_id integer,
        client_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table Category (
       id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table City (
       id integer not null auto_increment,
        name varchar(255),
        state_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table Client (
       id integer not null auto_increment,
        cpfOuCnpj varchar(255),
        email varchar(255),
        name varchar(255),
        type integer,
        primary key (id)
    ) engine=InnoDB;

    create table fone (
       Client_id integer not null,
        fone varchar(255)
    ) engine=InnoDB;

    create table Order_Item (
       desconto double precision,
        preco double precision,
        quantidade integer,
        order_id integer not null,
        product_id integer not null,
        primary key (order_id, product_id)
    ) engine=InnoDB;

    create table Payment (
       Order_id integer not null,
        status integer,
        primary key (Order_id)
    ) engine=InnoDB;

    create table Payment_Billet (
       dataPagamento datetime,
        dataVencimento datetime,
        Order_id integer not null,
        primary key (Order_id)
    ) engine=InnoDB;

    create table Payment_Card (
       numeroDeParcelas integer,
        Order_id integer not null,
        primary key (Order_id)
    ) engine=InnoDB;

    create table Product (
       id integer not null auto_increment,
        name varchar(255),
        price double precision not null,
        primary key (id)
    ) engine=InnoDB;

    create table PRODUCT_CATEGORY (
       product_id integer not null,
        category_id integer not null
    ) engine=InnoDB;

    create table State (
       id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table Client 
       add constraint UK_f07ymtqaif0tbcawyb71l3one unique (email);

    alter table `ORDER` 
       add constraint FK65aulwi6fqlgang60wxjos4k4 
       foreign key (client_id) 
       references Client (id);

    alter table `ORDER` 
       add constraint FKs7anilscyw17r80qm5rv6lvnj 
       foreign key (endereco_Entrega_id) 
       references Address (id);

    alter table Address 
       add constraint FKpwa35mv5w9mb3syngd4m8fprw 
       foreign key (city_id) 
       references City (id);

    alter table Address 
       add constraint FKfo9a2lduvue8lwvl9cilqsr96 
       foreign key (client_id) 
       references Client (id);

    alter table City 
       add constraint FK19lu7ofio5d2w879ch76hemmw 
       foreign key (state_id) 
       references State (id);

    alter table fone 
       add constraint FKoo64oqsyfa0uv8ri70xpjuhpg 
       foreign key (Client_id) 
       references Client (id);

    alter table Order_Item 
       add constraint FKt8674ybhnxfsvy7msf3go5gej 
       foreign key (order_id) 
       references `ORDER` (id);

    alter table Order_Item 
       add constraint FKh1t7rij78anmp2vqaa9is77in 
       foreign key (product_id) 
       references Product (id);

    alter table Payment 
       add constraint FK8vhn63rj4g35f3lc79g3gi1kl 
       foreign key (Order_id) 
       references `ORDER` (id);

    alter table Payment_Billet 
       add constraint FKkumc54soaoq57shiyceqvt8yr 
       foreign key (Order_id) 
       references Payment (Order_id);

    alter table Payment_Card 
       add constraint FK8gjn81csngc10ulms2vk819k5 
       foreign key (Order_id) 
       references Payment (Order_id);

    alter table PRODUCT_CATEGORY 
       add constraint FK7h07ab4db1iqwop3mlar0vqfc 
       foreign key (category_id) 
       references Category (id);

    alter table PRODUCT_CATEGORY 
       add constraint FKcb9phudo005jp2dke1xtgt5d8 
       foreign key (product_id) 
       references Product (id);
