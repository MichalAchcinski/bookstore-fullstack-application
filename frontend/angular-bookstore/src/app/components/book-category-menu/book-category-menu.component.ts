import { Component, OnInit } from '@angular/core';
import { BookCategory } from 'src/app/common/book-category/book-category';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-category-menu',
  templateUrl: './book-category-menu.component.html',
  styleUrls: ['./book-category-menu.component.css']
})
export class BookCategoryMenuComponent implements OnInit {

  bookCategories: BookCategory[];

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.listBookCategories();
  }

  listBookCategories() {
    
    this.bookService.getBookCategories().subscribe(
      data => {
        console.log('Product Categories =' + JSON.stringify(data));
        this.bookCategories = data;
      }
    )
  }

}
