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
import { ConfigurationContainer } from 'app/dynamic-form/models/configcontainer';

@Component({
  selector: 'app-bookshelfcontent',
  templateUrl: './bookshelfcontent.component.html',
  styleUrls: ['./bookshelfcontent.component.css']
})
export class BookshelfcontentComponent implements AfterViewInit {

  id: string;
  bookshelf: Bookshelf;

  @ViewChild(DynamicFormComponent) form: DynamicFormComponent;

  config: FieldConfig[] = [
    {
      id: 'isbn13',
      order: 1,
      type: 'input',
      label: 'ISBN 13',
      name: 'isbn13',
      placeholder: 'ISBN 13',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      id: 'isbn10',
      order: 2,
      type: 'input',
      label: 'ISBN 10',
      name: 'isbn10',
      placeholder: 'ISBN 10',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      id: 'title',
      order: 3,
      type: 'input',
      label: 'Title',
      name: 'title',
      placeholder: 'Title',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      id: 'published',
      order: 4,
      type: 'input',
      label: 'Published',
      name: 'published',
      placeholder: 'Published',
      validation: [Validators.required],
      inputType: 'date'
    },
    {
      id: 'publisher',
      order: 5,
      type: 'input',
      label: 'Publisher',
      name: 'publisher',
      placeholder: 'Publisher',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      id: 'edition',
      order: 6,
      type: 'input',
      label: 'Edition',
      name: 'edition',
      placeholder: 'Edition',
      validation: [Validators.required],
      inputType: 'number'
    },
    {
      id: 'numPages',
      order: 7,
      type: 'input',
      label: 'Number of pages',
      name: 'numPages',
      placeholder: 'Number of pages',
      validation: [Validators.required],
      inputType: 'number'
    },
    {
      id: 'author1',
      order: 8,
      type: 'input',
      label: 'Author',
      name: 'author',
      placeholder: 'Author',
      validation: [Validators.required],
      inputType: 'text',
      index: 1
    },
    {
      id: 'addAuthor',
      order: 100,
      label: 'Add author',
      name: 'addAuthor',
      type: 'button',
      inputType: 'button',
      class: 'btn btn-secondary',
      clickFunc: () => {
        console.log('addAuthor: clickFunc');
        this.config.push(ConfigurationContainer.spawnInputField('author', this.config));
        this.config.sort((a, b) => { return (a.order - b.order); });
      }
    },
    {
      id: 'submitButton',
      order: 101,
      label: 'Submit',
      name: 'submit',
      type: 'button',
      inputType: 'submit',
      class: 'btn btn-primary'
    }
  ];

  constructor(
    private route: ActivatedRoute,
    private bookshelfService: BookshelfService,
    private authService: AuthenticationService) {

      route.params.subscribe(params => {
        this.id = params['id'];
        this.bookshelfService.getBookshelf(this.id).subscribe(x => {
          this.bookshelf = x;
        });
      });
  }

  ngAfterViewInit() {
    let previousValid = this.form.valid;
    this.form.changes.subscribe(() => {
      if (this.form.valid !== previousValid) {
        previousValid = this.form.valid;
        this.form.setDisabled('submit', !previousValid);
      }
    });
  }

  submit(value: {[name: string]: any}) {
    const book: Book = new Book(value['isbn13'], value['isbn10'], value['title'],
      value['published'], value['publisher'], value['edition'],
      value['numPages'], null, null);
    console.log('BookshelfcontentComponent: submit: value: ', value);
    console.log('BookshelfcontentComponent: submit: book: ', book);
  }

  addAuthorField(): void {
    ;
  }

}
