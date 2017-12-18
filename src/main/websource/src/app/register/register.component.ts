import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { RegisteredUser } from '../models/registereduser.model';
import { UserPassword } from '../models/userpwd.model';
import { User } from '../models/user.model';
import { AuthenticatedUser } from '../models/authenticateduser.model';
import { AuthenticationService } from 'app/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  authenticatedUser: AuthenticatedUser;

  constructor(
    private formBuilder: FormBuilder,
    private http: Http,
    private authenticationService: AuthenticationService) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
      password2: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      phone: ['', Validators.required]
    });
  }

  onSubmit() {
    const user: User = new User(
      this.registerForm.controls['email'].value,
      this.registerForm.controls['password'].value,
      this.registerForm.controls['firstName'].value,
      this.registerForm.controls['lastName'].value,
      this.registerForm.controls['phone'].value
    );

    console.log('Authenticating user');

    const authUser: AuthenticatedUser = this.authenticationService.login(user.email, user.password);

    console.log(authUser);

    const headers: Headers = new Headers();
    headers.append('API-KEY', this.authenticatedUser.hashedApiKey);

    const opts: RequestOptions = new RequestOptions();
    opts.headers = headers;

    const registerUserUrl = `localhost:8080/Bookshelf/webresources/user`;

    this.http
      .post(registerUserUrl, JSON.stringify(user), opts)
      .subscribe((res: Response) => {
        if (res.ok) {
          console.log('ok');
        } else {
          console.log('failed');
        }
      });
  }
}
