import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from 'app/services/book.service';
import { AuthenticationService } from 'app/auth/auth.service';
import { AlertService } from 'app/services/alert.service';
import { ViewBook } from 'app/models/viewbook.model';

@Component({
  selector: 'app-write-review',
  templateUrl: './write-review.component.html',
  styleUrls: ['./write-review.component.css']
})
export class WriteReviewComponent implements OnInit {

  isbn13: string;

  constructor(
    private route: ActivatedRoute,
    private bookService: BookService,
    private authService: AuthenticationService,
    private alertService: AlertService) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.isbn13 = params['id'];
    });
  }

}

