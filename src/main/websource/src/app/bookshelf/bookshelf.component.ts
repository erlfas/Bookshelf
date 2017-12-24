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
  bookshelves: Array<Bookshelf>;

  constructor(
    private formBuilder: FormBuilder,
    private bookshelfService: BookshelfService,
    private authService: AuthenticationService) {}

  ngOnInit() {
    this.bookshelfForm = this.formBuilder.group({
      title: ['', Validators.required]
    });
    this.bookshelves = [];

    const authUser: AuthenticatedUser = this.authService.getAuthenticatedUser();

    if (authUser != null && authUser.username != null) {
      this.bookshelfService
        .getAllBookshelves(authUser.username)
        .subscribe(data => {
          console.log('BookshelfComponent: ngOnInit: setting ', JSON.stringify(data));
          this.bookshelves = data.bookshelves;
        });
    } else {
      console.log('BookshelfComponent: ngOnInit: found no authUser ');
    }
  }

  onSubmit(): void {
    const authUser: AuthenticatedUser = this.authService.getAuthenticatedUser();
    if (authUser == null) {
      console.log('BookshelfComponent: onSubmit: could not find authUser');
      return;
    }

    this.bookshelfService
      .addBookshelf(this.bookshelfForm.controls['title'].value, authUser.username)
      .subscribe(savedBookshelf => {
        if (savedBookshelf != null) {
          console.log('BookshelfComponent: onSubmit: pushing ', JSON.stringify(savedBookshelf));
          this.bookshelves.push(savedBookshelf);
        }
      });
  }
}
