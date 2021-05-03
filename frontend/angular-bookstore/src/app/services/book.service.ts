import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../common/book/book';
import { map } from 'rxjs/operators'
import { BookCategory } from '../common/book-category/book-category';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseUrl = 'http://localhost:8080/books';

  private categoryUrl ='http://localhost:8080/bookCategories';

  constructor(private httpClient: HttpClient) { }

  getBook(theProductId: number): Observable<Book> {

    const bookUrl = `${this.baseUrl}/${theProductId}`;

    return this.httpClient.get<Book>(bookUrl);
  }

  getWholeBooksList(): Observable<Book[]> {

    const booksUrl = `${this.baseUrl}`;

    return this.getBooks();
  }

  getBooksListWithCategory(theCategoryId: number): Observable<Book[]> {

    const searchUrl= `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;

    return this.getBooksSearch(searchUrl);
  }

  getBookCategories(): Observable<BookCategory[]> {

    return this.httpClient.get<GetResponseBookCategory>(this.categoryUrl).pipe(
      map(response => response._embedded.bookCategories) 
    );

  }

  searchBooks(theKeyword: string): Observable<Book[]> {
    const searchUrl= `${this.baseUrl}/search/findByTitleContaining?title=${theKeyword}`;

    return this.getBooksSearch(searchUrl);
  }


  private getBooksSearch(searchUrl: string): Observable<Book[]> {
    return this.httpClient.get<GetResponseBooks>(searchUrl).pipe(
      map(response => response._embedded.books)
    );
  }

  private getBooks(): Observable<Book[]> {
    return this.httpClient.get<GetResponseBooks>(this.baseUrl).pipe(
      map(response => response._embedded.books)
    );
  }
}

interface GetResponseBooks {
  _embedded: {
    books: Book[];
  }  
}

interface GetResponseBookCategory {
  _embedded: {
    bookCategories: BookCategory[];
  }  
}
