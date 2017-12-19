import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { RegisteredUser } from '../models/registereduser.model';
import { UserPassword } from '../models/userpwd.model';
import { User } from '../models/user.model';
import { AuthenticatedUser } from '../models/authenticateduser.model';
import { AuthenticationService } from 'app/auth/auth.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;

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

    this.authenticationService.registerUser(user);
  }
}
