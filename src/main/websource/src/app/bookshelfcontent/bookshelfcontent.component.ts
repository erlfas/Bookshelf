import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookshelfService } from 'app/services/bookshelf.service';
import { Bookshelf } from 'app/models/bookshelf.model';
import { AuthenticationService } from 'app/auth/auth.service';
import { AuthenticatedUser } from 'app/models/authenticateduser.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-bookshelfcontent',
  templateUrl: './bookshelfcontent.component.html',
  styleUrls: ['./bookshelfcontent.component.css']
})
export class BookshelfcontentComponent implements OnInit {

  id: string;
  bookshelf: Bookshelf;
  bookForm: FormGroup;

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

  ngOnInit() {
    this.bookForm = this.formBuilder.group({
      isbn13: ['', Validators.required],
      isbn10: ['', Validators.required],
      title: ['', Validators.required],
      published: ['', Validators.required],
      publisher: ['', Validators.required],
      edition: ['', Validators.required],
      numPages: ['', Validators.required],
    });
  }

  onSubmit(): void {

  }

}
