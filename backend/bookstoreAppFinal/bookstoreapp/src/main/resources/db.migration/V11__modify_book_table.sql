alter table bookstorefullstack.book
    add category_id int null;

alter table bookstorefullstack.book
    add constraint book___fk
        foreign key (category_id) references book_category(id);
