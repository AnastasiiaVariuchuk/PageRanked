drop database PageRanked;
create database PageRanked;
USE PageRanked;

create table links
(
    id       int         not null
        primary key,
    linkHref varchar(45) not null,
    linkText varchar(45) not null
);

create table nodes
(
    id           int         not null
        primary key,
    quantity_In  int         not null,
    quantity_Out int         not null,
    name         varchar(45) not null,
    pageRank     double      not null
);

create table relations
(
    id       int not null
        primary key,
    Nodes_id int not null,
    Links_id int not null,
    constraint fk_Relations_Links1
        foreign key (Links_id) references links (id),
    constraint fk_Relations_Nodes
        foreign key (Nodes_id) references nodes (id)
);

create index fk_Relations_Links1_idx
    on relations (Links_id);

create index fk_Relations_Nodes_idx
    on relations (Nodes_id);

