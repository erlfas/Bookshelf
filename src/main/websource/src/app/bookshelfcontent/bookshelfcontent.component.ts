import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookshelfService } from 'app/services/bookshelf.service';
import { Bookshelf } from 'app/models/bookshelf.model';
import { AuthenticationService } from 'app/auth/auth.service';
import { AuthenticatedUser } from 'app/models/authenticateduser.model';
import { ActivatedRoute } from '@angular/router';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';
import { DynamicFormComponent } from 'app/dynamic-form/containers/dynamic-form/dynamic-form.component';
import { Book } from 'app/models/book.model';

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
      type: 'input',
      label: 'ISBN 13',
      name: 'isbn13',
      placeholder: 'ISBN 13',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      type: 'input',
      label: 'ISBN 10',
      name: 'isbn10',
      placeholder: 'ISBN 10',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      type: 'input',
      label: 'Title',
      name: 'title',
      placeholder: 'Title',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      type: 'input',
      label: 'Published',
      name: 'published',
      placeholder: 'Published',
      validation: [Validators.required],
      inputType: 'date'
    },
    {
      type: 'input',
      label: 'Publisher',
      name: 'publisher',
      placeholder: 'Publisher',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      type: 'input',
      label: 'Edition',
      name: 'edition',
      placeholder: 'Edition',
      validation: [Validators.required],
      inputType: 'number'
    },
    {
      type: 'input',
      label: 'Number of pages',
      name: 'numPages',
      placeholder: 'Number of pages',
      validation: [Validators.required],
      inputType: 'number'
    },
    {
      type: 'input',
      label: 'Author',
      name: 'author',
      placeholder: 'Author',
      validation: [Validators.required],
      inputType: 'text'
    },
    {
      label: 'Submit',
      name: 'submit',
      type: 'button'
    }
  ];

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
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
