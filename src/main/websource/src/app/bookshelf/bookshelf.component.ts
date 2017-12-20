import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-bookshelf',
  templateUrl: './bookshelf.component.html',
  styleUrls: ['./bookshelf.component.css']
})
export class BookshelfComponent implements OnInit {

  bookshelfForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {}

    ngOnInit() {
      this.bookshelfForm = this.formBuilder.group({
        title: ['', Validators.required]
      });
    }

    onSubmit() {

    }

}
