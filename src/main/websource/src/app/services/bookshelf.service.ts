import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Bookshelf } from 'app/models/bookshelf.model';

@Injectable()
export class BookshelfService {

    constructor(
        private http: HttpClient,
        private router: Router) {}

    addBookshelf(_title: string, _username: string) {
        const url = `http://localhost:8080/Bookshelf/webresources/bookshelf`;
        const header = new HttpHeaders({'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*'});
        const body = {
            title: _title,
            username: _username
        };

        // https://angular.io/guide/http#reading-the-full-response
        this.http
            .post(url, JSON.stringify(body), {
                headers: header,
                observe: 'response'
            })
            .subscribe(
                data => {
                    console.log('addBookshelf: Status: ', data);
                },
                err => {
                    console.log('addBookshelf: Error: ', err);
                },
                () => {
                    console.log('addBookshelf: done');
                }
            );
    }

}
