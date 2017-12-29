import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ViewBook } from 'app/models/viewbook.model';
import { AuthenticationService } from 'app/auth/auth.service';
import { AlertService } from 'app/services/alert.service';
import { BookService } from 'app/services/book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  isbn13: string;
  book: ViewBook;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private authService: AuthenticationService,
    private alertService: AlertService) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.isbn13 = params['id'];
      this.bookService.findBookByIsbn13(this.isbn13)
        .subscribe(book => {
          this.book = new ViewBook(book.isbn13, book.isbn10, book.title,
            book.published, book.publisher, book.edition,
             book.numPages, book.authors, book.tags, book.pictureUrl);
        },
        err => {
          this.alertService.error('The book can not be loaded.');
        });
    });
  }

}
