-- we don't know how to generate root <with-no-name> (class Root) :(
create sequence hibernate_sequence;

create table utilisateur
(
    id bigint not null
        constraint utilisateur_pkey
            primary key,
    courriel varchar(255) not null,
    date timestamp,
    is_admin boolean,
    login varchar(60) not null,
    mot_de_passe varchar(60) not null,
    nom varchar(60),
    prenom varchar(60) not null
);


create table site
(
    id bigint not null
        constraint site_pkey
            primary key,
    date timestamp,
    departement varchar(255) not null,
    description varchar(256),
    localisation varchar(256),
    nom varchar(30) not null,
    orientation varchar(255) not null,
    type varchar(255) not null,
    utilisateur_id bigint
        constraint fkmftxiruitd4c42pomkfai2keo
            references utilisateur
);


create table secteur
(
    id bigint not null
        constraint secteur_pkey
            primary key,
    date timestamp,
    nom varchar(30) not null,
    site_id bigint
        constraint fkc5crh1gx05vjasl4jusc1mgh2
            references site
);


create table topo
(
    id bigint not null
        constraint topo_pkey
            primary key,
    date timestamp,
    nom varchar(30) not null,
    utilisateur_id bigint
        constraint fk7w124lr4xukv1ttq413hrsq60
            references utilisateur
);


create table commentaire
(
    id bigint not null
        constraint commentaire_pkey
            primary key,
    content varchar(255) not null,
    date timestamp,
    site_id bigint
        constraint fk7poewyx0vupiexwj3o3v5r746
            references site,
    topo_id bigint
        constraint fkaa6a80ne3ip3kms28ujmr6hbt
            references topo,
    utilisateur_id bigint
        constraint fkfkx1pegfdsd6e3cp2wblsc5jf
            references utilisateur
);


create table reservation
(
    id bigint not null
        constraint reservation_pkey
            primary key,
    date_debut date,
    date_fin date,
    topo_id bigint
        constraint fks6rhp8bjbn9p9imq1k5fcpb6a
            references topo,
    utilisateur_id bigint
        constraint fk7age5yb4rno7mnt26auu4403c
            references utilisateur
);


create table site_topo
(
    site_id bigint not null
        constraint fkcebh1aw4vj652vn2xcegcugh0
            references site,
    topo_id bigint not null
        constraint fka9ous62exibkr60xwbeb8ywex
            references topo
);


create table voie
(
    id bigint not null
        constraint voie_pkey
            primary key,
    cotation integer not null
        constraint voie_cotation_check
            check ((cotation <= 30) AND (cotation >= 1)),
    date timestamp,
    longueur integer not null
        constraint voie_longueur_check
            check ((longueur <= 1000) AND (longueur >= 1)),
    nom varchar(30) not null,
    secteur_id bigint
        constraint fkqlo061c6fgkuosv9chywpgn8t
            references secteur
);


