
    create table address (
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

    create table category (
       id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table city (
       id integer not null auto_increment,
        name varchar(255),
        state_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table client (
       id integer not null auto_increment,
        cpf_ou_cnpj varchar(255),
        email varchar(255),
        name varchar(255),
        type integer,
        primary key (id)
    ) engine=InnoDB;

    create table fone (
       client_id integer not null,
        fone varchar(255)
    ) engine=InnoDB;

    create table order_item (
       desconto double precision,
        preco double precision,
        quantidade integer,
        product_id integer not null,
        request_id integer not null,
        primary key (product_id, request_id)
    ) engine=InnoDB;

    create table payment (
       request_id integer not null,
        status integer,
        primary key (request_id)
    ) engine=InnoDB;

    create table payment_billet (
       data_pagamento datetime,
        data_vencimento datetime,
        request_id integer not null,
        primary key (request_id)
    ) engine=InnoDB;

    create table payment_card (
       numero_de_parcelas integer,
        request_id integer not null,
        primary key (request_id)
    ) engine=InnoDB;

    create table product (
       id integer not null auto_increment,
        name varchar(255),
        price double precision not null,
        primary key (id)
    ) engine=InnoDB;

    create table product_category (
       product_id integer not null,
        category_id integer not null
    ) engine=InnoDB;

    create table request (
       id integer not null auto_increment,
        instante datetime,
        client_id integer,
        endereco_enrega_id integer,
        primary key (id)
    ) engine=InnoDB;

    create table state (
       id integer not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table client 
       add constraint UK_bfgjs3fem0hmjhvih80158x29 unique (email);

    alter table address 
       add constraint FKpo044ng5x4gynb291cv24vtea 
       foreign key (city_id) 
       references city (id);

    alter table address 
       add constraint FK7156ty2o5atyuy9f6kuup9dna 
       foreign key (client_id) 
       references client (id);

    alter table city 
       add constraint FK6p2u50v8fg2y0js6djc6xanit 
       foreign key (state_id) 
       references state (id);

    alter table fone 
       add constraint FKm0w8ku83uviclkeagqh9b9n2n 
       foreign key (client_id) 
       references client (id);

    alter table order_item 
       add constraint FK551losx9j75ss5d6bfsqvijna 
       foreign key (product_id) 
       references product (id);

    alter table order_item 
       add constraint FK91goudsyw5vtk7nmkerv3nxm7 
       foreign key (request_id) 
       references request (id);

    alter table payment 
       add constraint FK5pgyup82pk40rm2mo6jeqrbi7 
       foreign key (request_id) 
       references request (id);

    alter table payment_billet 
       add constraint FK9334roolx7fpmc6wa40nbpmww 
       foreign key (request_id) 
       references payment (request_id);

    alter table payment_card 
       add constraint FK7sbihchr8oqq8yqt79ugnbiob 
       foreign key (request_id) 
       references payment (request_id);

    alter table product_category 
       add constraint FKkud35ls1d40wpjb5htpp14q4e 
       foreign key (category_id) 
       references category (id);

    alter table product_category 
       add constraint FK2k3smhbruedlcrvu6clued06x 
       foreign key (product_id) 
       references product (id);

    alter table request 
       add constraint FKdayt1j0e3kc0j52bn9b78dav 
       foreign key (client_id) 
       references client (id);

    alter table request 
       add constraint FK94ft45nof2878gmo53vwalpv0 
       foreign key (endereco_enrega_id) 
       references address (id);
