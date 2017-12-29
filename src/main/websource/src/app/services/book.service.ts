import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Book } from 'app/models/book.model';
import { Books } from 'app/models/books.model';

@Injectable()
export class BookService {

    constructor(
        private http: HttpClient,
        private router: Router) {}

    findBookByIsbn13(isbn13: string): Observable<Book> {
        const url = `http://localhost:8080/Bookshelf/webresources/book/id/${isbn13}`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

        console.log('BookService: findBook');

        return this.http.get<Book>(url, {headers: header});
    }

    findBookByTitle(title: string): Observable<Book[]> {
        const url = `http://localhost:8080/Bookshelf/webresources/book?title=${title}`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

        console.log('BookService: findBook');

        return this.http.get<Book[]>(url, {headers: header});
    }

    registerBook(book: Book): Observable<HttpResponse<Object>> {
        const url = `http://localhost:8080/Bookshelf/webresources/book`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

        console.log('BookService: registerBook: ', JSON.stringify(book));

        return this.http.post(url, JSON.stringify(book), {headers: header, observe: 'response'});
    }

}
