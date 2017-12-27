import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Bookshelf } from 'app/models/bookshelf.model';
import { Bookshelves } from 'app/models/bookshelves.model';
import { Book } from 'app/models/book.model';

@Injectable()
export class BookshelfService {

    constructor(
        private http: HttpClient,
        private router: Router) {}

    addBookToBookshelf(bookshelfId: string, book: Book): Observable<HttpResponse<Object>> {
        const url = `http://localhost:8080/Bookshelf/webresources/bookshelf/id/${bookshelfId}/addbook/`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

        console.log('BookshelfService: addBookToBookshelf: url: ', url);
        console.log(JSON.stringify(book));

        return this.http.post(url, JSON.stringify(book), {headers: header, observe: 'response'});
    }

    getBookshelf(id: string): Observable<Bookshelf> {
        const url = `http://localhost:8080/Bookshelf/webresources/bookshelf/id/${id}`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

        console.log('BookshelfService: getBookshelf: url: ', url);

        // https://angular.io/guide/http#reading-the-full-response
        return this.http.get<Bookshelf>(url, {headers: header});
    }

    getAllBookshelves(_username: string): Observable<Bookshelves> {
        const url = `http://localhost:8080/Bookshelf/webresources/bookshelf/all?username=${_username}`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});

        return this.http.get<Bookshelves>(url, {headers: header});
    }

    addBookshelf(_title: string, _username: string): Observable<Bookshelf> {
        const url = `http://localhost:8080/Bookshelf/webresources/bookshelf`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});
        const body = {
            title: _title,
            username: _username
        };

        // https://angular.io/guide/http#reading-the-full-response
        return this.http.post<Bookshelf>(url, JSON.stringify(body), {headers: header});
    }

}
