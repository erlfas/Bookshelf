import { Input, Component, OnInit } from '@angular/core';
import { ViewBook } from 'app/models/viewbook.model';

@Component({
  selector: 'app-book-item',
  templateUrl: './book-item.component.html',
  styleUrls: ['./book-item.component.css']
})
export class BookItemComponent implements OnInit {

  @Input() book: ViewBook;

  constructor() { }

  ngOnInit() {
  }

}
