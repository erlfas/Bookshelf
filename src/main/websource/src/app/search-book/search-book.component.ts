import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { DynamicFormComponent } from 'app/dynamic-form/containers/dynamic-form/dynamic-form.component';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';
import { BookService } from 'app/services/book.service';
import { AuthenticationService } from 'app/auth/auth.service';
import { AlertService } from 'app/services/alert.service';
import { ViewBook } from 'app/models/viewbook.model';

@Component({
  selector: 'app-search-book',
  templateUrl: './search-book.component.html',
  styleUrls: ['./search-book.component.css']
})
export class SearchBookComponent implements AfterViewInit, OnInit {

  books: ViewBook[];

  @ViewChild(DynamicFormComponent) form: DynamicFormComponent;

    config: FieldConfig[] = [
      {
        group: 'title',
        order: 1,
        type: 'input',
        label: 'Search by title',
        name: 'title',
        placeholder: 'Search by title',
        validation: [Validators.required],
        inputType: 'text'
      },
      {
        group: 'submitButton',
        order: 101,
        label: 'Submit',
        name: 'submit',
        type: 'button',
        inputType: 'submit',
        class: 'btn btn-primary',
        clickFunc: () => {}
      }
    ];

    constructor(
      private route: ActivatedRoute,
      private bookService: BookService,
      private authService: AuthenticationService,
      private alertService: AlertService) {
    }

    ngOnInit(): void {
      console.log('SearchBookComponent: ngOnInit');
    }

    ngAfterViewInit() {
      console.log('SearchBookComponent: ngAfterViewInit');
    }

    submit(value: {[name: string]: any}) {
      this.bookService
        .findBookByTitle(value['title'])
        .subscribe(
          booklist => {
            if (booklist == null || booklist === undefined
              || booklist.books == null || booklist.books === undefined
              || booklist.books.length <= 0) {
              this.alertService.error('Found no books.');
            } else {
              this.books = booklist.books.map(x => {
                return new ViewBook(x.isbn13, x.isbn10, x.title,
                  x.published, x.publisher, x.edition,
                  x.numPages, x.authors, x.tags, x.pictureUrl);
              });
            }
          },
          err => {
            this.alertService.error('An error occured while searching for books.');
          });
    }

}
