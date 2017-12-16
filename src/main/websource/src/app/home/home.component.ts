import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  status: string;

  constructor() { }

  ngOnInit() {
    this.status = 'You are currently not logged in.';
  }

}
