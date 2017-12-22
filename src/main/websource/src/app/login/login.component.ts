import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { AuthenticationService } from 'app/auth/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticatedUser } from '../models/authenticateduser.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  returnUrl: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService) {}

  ngOnInit() {
    const logoutUrl = this.route.snapshot.url.find(x => x.path.search('logout') > -1);
    if (logoutUrl != null) {
      console.log('LoginComponent: ngOnInit: used logout path: ', logoutUrl.path);
      this.logout();
    }
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      pwd: ['', Validators.required]
    });
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    this.authenticationService.loginUser(this.loginForm.controls['email'].value, this.loginForm.controls['pwd'].value);
  }

  logout() {
    this.authenticationService.logout();
  }

  notLoggedIn(): boolean {
    return !this.loggedIn();
  }

  loggedIn(): boolean {
    return this.authenticationService.isLoggedIn();
  }

  getLogInStatus(): string {
    return this.authenticationService.getLogInStatus();
  }

}
