import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookshelfService } from 'app/services/bookshelf.service';
import { Bookshelf } from 'app/models/bookshelf.model';
import { AuthenticationService } from 'app/auth/auth.service';
import { AuthenticatedUser } from 'app/models/authenticateduser.model';

@Component({
  selector: 'app-bookshelf',
  templateUrl: './bookshelf.component.html',
  styleUrls: ['./bookshelf.component.css']
})
export class BookshelfComponent implements OnInit {
  bookshelfForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private bookshelfService: BookshelfService,
    private authService: AuthenticationService) {}

  ngOnInit() {
    this.bookshelfForm = this.formBuilder.group({
      title: ['', Validators.required]
    });
  }

  onSubmit() {
    const authUser: AuthenticatedUser = this.authService.getAuthenticatedUser();
    if (authUser == null) {

    }

    this.bookshelfService.addBookshelf(this.bookshelfForm.controls['title'].value, authUser.username);
  }
}
