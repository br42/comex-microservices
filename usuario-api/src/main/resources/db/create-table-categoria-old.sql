create table categoria (
    
    id bigint not null generated always as identity primary key,
    
    nome varchar(255),
    status boolean
    
);
