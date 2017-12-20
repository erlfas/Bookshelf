import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './auth/auth.service';
import { AuthenticatedUser } from './models/authenticateduser.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Bookshelf';

  constructor(
    private router: Router,
    private authService: AuthenticationService) {}

  getLogInStatus() {
    return this.authService.getLogInStatus();
  }
}
