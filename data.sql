create table customer (id bigint AUTO_INCREMENT PRIMARY KEY, name varchar(255), created_date datetime, account_owner bigint);
create table customer_rev (customer_id bigint, agg_rev bigint);