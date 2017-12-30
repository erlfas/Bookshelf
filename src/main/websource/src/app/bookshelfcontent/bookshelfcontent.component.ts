import { Component, OnInit } from '@angular/core';
import { BookshelfService } from 'app/services/bookshelf.service';
import { Bookshelf } from 'app/models/bookshelf.model';
import { AuthenticationService } from 'app/auth/auth.service';
import { AuthenticatedUser } from 'app/models/authenticateduser.model';
import { ActivatedRoute } from '@angular/router';
import { ViewBook } from 'app/models/viewbook.model';
import { AlertService } from 'app/services/alert.service';

@Component({
  selector: 'app-bookshelfcontent',
  templateUrl: './bookshelfcontent.component.html',
  styleUrls: ['./bookshelfcontent.component.css']
})
export class BookshelfcontentComponent implements OnInit {

  id: string;
  bookshelf: Bookshelf;
  books: ViewBook[];

  constructor(
    private route: ActivatedRoute,
    private bookshelfService: BookshelfService,
    private authService: AuthenticationService,
    private alertService: AlertService) {

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.bookshelfService
        .getBookshelf(this.id)
        .subscribe(
          data => {
            this.bookshelf = data;
            this.books = data.books.map(x => {
              return new ViewBook(x.isbn13, x.isbn10, x.title,
                x.published, x.publisher, x.edition,
                 x.numPages, x.authors, x.tags, x.reviews, x.pictureUrl);
            });
          },
          err => {
            this.alertService.error('The bookshelf can not be loaded.');
          });
    });
  }

}
