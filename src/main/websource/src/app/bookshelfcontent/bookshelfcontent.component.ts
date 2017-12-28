import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { BookshelfService } from 'app/services/bookshelf.service';
import { Bookshelf } from 'app/models/bookshelf.model';
import { AuthenticationService } from 'app/auth/auth.service';
import { AuthenticatedUser } from 'app/models/authenticateduser.model';
import { ActivatedRoute } from '@angular/router';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';
import { DynamicFormComponent } from 'app/dynamic-form/containers/dynamic-form/dynamic-form.component';
import { Book } from 'app/models/book.model';
import { Spawner } from 'app/dynamic-form/models/spawner';
import { Author } from 'app/models/author.model';
import { AddBookConstants } from 'app/dynamic-form/models/addbook.constants';
import { ViewBook } from 'app/models/viewbook.model';
import { ChangeDetectorRef } from '@angular/core/src/change_detection/change_detector_ref';

@Component({
  selector: 'app-bookshelfcontent',
  templateUrl: './bookshelfcontent.component.html',
  styleUrls: ['./bookshelfcontent.component.css']
})
export class BookshelfcontentComponent implements AfterViewInit, OnInit {

  id: string;
  bookshelf: Bookshelf;
  books: ViewBook[];

  @ViewChild(DynamicFormComponent) form: DynamicFormComponent;

  config: FieldConfig[] = [
    {
      group: 'isbn13',
      order: 1,
      type: 'input',
      label: 'ISBN 13',
      name: 'isbn13',
      placeholder: 'ISBN 13',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      group: 'isbn10',
      order: 2,
      type: 'input',
      label: 'ISBN 10',
      name: 'isbn10',
      placeholder: 'ISBN 10',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      group: 'title',
      order: 3,
      type: 'input',
      label: 'Title',
      name: 'title',
      placeholder: 'Title',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      group: 'published',
      order: 4,
      type: 'input',
      label: 'Published',
      name: 'published',
      placeholder: 'Published',
      validation: [Validators.required],
      inputType: 'date'
    },
    {
      group: 'publisher',
      order: 5,
      type: 'input',
      label: 'Publisher',
      name: 'publisher',
      placeholder: 'Publisher',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      group: 'edition',
      order: 6,
      type: 'input',
      label: 'Edition',
      name: 'edition',
      placeholder: 'Edition',
      validation: [Validators.required],
      inputType: 'number'
    },
    {
      group: 'numPages',
      order: 7,
      type: 'input',
      label: 'Number of pages',
      name: 'numPages',
      placeholder: 'Number of pages',
      validation: [Validators.required],
      inputType: 'number'
    },
    {
      group: 'pictureUrl',
      order: 8,
      type: 'input',
      label: 'URL to picture',
      name: 'pictureUrl',
      placeholder: 'URL to picture',
      validation: [],
      inputType: AddBookConstants.INPUT_TYPE_TEXT
    },
    {
      group: 'author',
      order: 9,
      type: 'input',
      label: 'Author',
      name: 'author',
      placeholder: 'Author',
      validation: [Validators.required],
      inputType: 'text',
      index: 1
    },
    {
      group: 'addAuthor',
      order: 100,
      label: 'Add author',
      name: 'addAuthor',
      type: 'button',
      inputType: 'button',
      class: 'btn btn-secondary',
      clickFunc: () => {
        console.log('addAuthor: clickFunc');
        if (this.maxIsReached(this.config)) {
          console.log('addAuthor: clickFunc: max is reached');
          return;
        }
        const newField: FieldConfig = Spawner.spawnInputField('author', this.config);
        this.config.push(newField);
        this.config.sort((a, b) => { return (a.order - b.order); });
        this.form.addControl(newField);
      }
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
    private bookshelfService: BookshelfService,
    private authService: AuthenticationService) {

  }

  ngOnInit(): void {
    console.log('BookshelfcontentComponent: ngOnInit');
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.bookshelfService
        .getBookshelf(this.id)
        .subscribe(
          data => {
            console.log('BookshelfcontentComponent: ngOnInit: bookshelfService: getBookshelf: subscribe: data: ', data);
            this.bookshelf = data;
            this.books = data.books.map(x => {
              return new ViewBook(x.isbn13, x.isbn10, x.title,
                x.published, x.publisher, x.edition,
                 x.numPages, x.authors, x.tags, x.pictureUrl);
            });
          },
          err => {
            console.log('BookshelfcontentComponent: ngOnInit: bookshelfService: getBookshelf: subscribe: error', err);
          });
    });
  }

  ngAfterViewInit() {
    console.log('BookshelfcontentComponent: ngAfterViewInit');
  }

  maxIsReached(config: FieldConfig[]): boolean {
    let count = 0;
    for (const c of config) {
      if (c.group === 'author') {
        count++;
      }
    }

    return count >= 5;
  }

  getAuthor(s: string): Author {
    const elm = s.split(',');
    if (elm !== null && elm.length === 2) {
      return new Author(elm[1].trim(), elm[0].trim());
    }
    return null;
  }

  push(name: string, authors: Array<Author>, value: {[name: string]: any}) {
    const val = value[name];
    if (val !== null && val !== undefined) {
      const author = this.getAuthor(val);
      if (author !== null && author !== undefined) {
        authors.push(this.getAuthor(val));
      }
    }
  }

  getAuthors(value: {[name: string]: any}): Array<Author> {
    const authors: Array<Author> = new Array();

    this.push('author', authors, value);
    this.push('author2', authors, value);
    this.push('author3', authors, value);
    this.push('author4', authors, value);
    this.push('author5', authors, value);

    return authors;
  }

  submit(value: {[name: string]: any}) {
    const book: Book = new Book(value['isbn13'], value['isbn10'], value['title'],
      value['published'], value['publisher'], value['edition'],
      value['numPages'], this.getAuthors(value), null, value['pictureUrl']);
    console.log('BookshelfcontentComponent: submit: book: ', book);
    this.bookshelfService
      .addBookToBookshelf(this.id, book)
      .subscribe(
        data => {
          console.log('BookshelfcontentComponent: submit: addBookToBookshelf: status of result: ', data.status);
          if (data.status === 200 || data.status === 201) {
            this.bookshelf.books.push(book);
            if (this.books === null || this.books === undefined) {
              this.books = [];
            }
            this.books.push(new ViewBook(book.isbn13, book.isbn10, book.title,
              book.published, book.publisher, book.edition,
              book.numPages, book.authors, book.tags, book.pictureUrl));
            this.form.reset();
            console.log('BookshelfcontentComponent: submit: OK');
          } else {
            console.log('BookshelfcontentComponent: submit: status: ', data.status);
          }
        },
        err => {
          console.log('addBookToBookshelf: result: error', err);
        },
        () => {}
      );
  }

}
