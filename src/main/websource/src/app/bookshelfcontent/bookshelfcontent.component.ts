import { Component, OnInit } from '@angular/core';
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

  constructor(
    private route: ActivatedRoute,
    private bookshelfService: BookshelfService,
    private authService: AuthenticationService) {

      route.params.subscribe(params => { this.id = params['id']; });

    }

  ngOnInit() {
  }

}
