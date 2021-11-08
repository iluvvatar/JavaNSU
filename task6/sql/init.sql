create table currencies
(
    code     varchar(15) not null
        primary key,
    num_code int         not null,
    lot      int         not null,
    name     text        not null
);

create table history
(
    date          date        not null,
    currency_code varchar(15) not null,
    value         float       not null,
    primary key (date, currency_code),
    constraint history_currencies_code_fk
        foreign key (currency_code) references currencies (code)
);
