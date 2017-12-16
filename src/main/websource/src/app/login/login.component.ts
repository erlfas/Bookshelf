import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { RegisteredUser } from '../models/registereduser.model';
import { UserPassword } from '../models/userpwd.model';
import { User } from '../models/user.model';
import { AuthenticatedUser } from '../models/authenticateduser.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  registerForm: FormGroup;
  authenticatedUser: AuthenticatedUser;

  constructor(private formBuilder: FormBuilder, private http: Http) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: ['', Validators.required],
      pwd: ['', Validators.required],
      pwd2: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      phone: ['', Validators.required]
    });
  }

  onSubmit({ registeredUser, valid }: { registeredUser: RegisteredUser; valid: boolean }) {
    console.log(registeredUser, valid);

    const authUrl = `localhost:8080/Bookshelf/webresources/authentication/json`;

    this.http
      .post(authUrl, JSON.stringify(new UserPassword(registeredUser.email, registeredUser.password)))
      .subscribe((res: Response) => {
        this.authenticatedUser = res.json();
      });

    const headers: Headers = new Headers();
    headers.append('API-KEY', this.authenticatedUser.hashedApiKey);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    const registerUserUrl = `localhost:8080/Bookshelf/webresources/user`;
    const jsonBody = JSON.stringify(new User(registeredUser.email, registeredUser.password,
      registeredUser.firstName, registeredUser.lastName, registeredUser.phone));

    this.http
      .post(registerUserUrl, jsonBody, opts)
      .subscribe((res: Response) => {
        if (res.ok) {
          
        } else {

        }
      });
  }
}
