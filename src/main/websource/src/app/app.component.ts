import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Validators } from '@angular/forms';
import { AuthenticationService } from './auth/auth.service';
import { AuthenticatedUser } from './models/authenticateduser.model';
import { FieldConfig } from 'app/dynamic-form/models/field-config.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Bookshelf';

  config: FieldConfig[] = [
    {
      type: 'input',
      label: 'Full name',
      name: 'name',
      placeholder: 'Enter your name',
      validation: [Validators.required, Validators.minLength(4)]
    },
    {
      type: 'select',
      label: 'Favourite Food',
      name: 'food',
      options: ['Pizza', 'Hot Dogs', 'Knakworstje', 'Coffee'],
      placeholder: 'Select an option',
      validation: [Validators.required]
    },
    {
      label: 'Submit',
      name: 'submit',
      type: 'button'
    }
  ];

  constructor(
    private router: Router,
    private authService: AuthenticationService) {}

  getLogInStatus() {
    return this.authService.getLogInStatus();
  }

  submit(value: {[name: string]: any}) {
    console.log(value);
  }
}
