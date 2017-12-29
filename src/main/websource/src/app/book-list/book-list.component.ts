import { Input, Component, OnInit } from '@angular/core';
import { ViewBook } from 'app/models/viewbook.model';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  @Input() books: ViewBook[];

  constructor() { }

  ngOnInit() {
  }

}
