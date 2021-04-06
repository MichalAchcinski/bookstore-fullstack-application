import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/common/book/book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: Book[];
  currentCategoryId: number;
  searchMode: boolean;

  constructor(private bookService: BookService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listBooks();
    });

  }

  listBooks() {

    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if(this.searchMode) {
      this.handleSearchBooks();
    } else {
      this.handleListBooks();
    }

  }

  handleSearchBooks(){

    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');

    this.bookService.searchBooks(theKeyword).subscribe(
      data => {
        this.books = data;
      }
    );
  }

  handleListBooks() {

    // sprawdzamy czy parametr id został przekazany
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
    } else {
      this.currentCategoryId = 2;
    }

    this.bookService.getBooksList(this.currentCategoryId).subscribe(
      data => {
        this.books = data;
      }
    )
  }

}
