import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { LoginUser } from '../models/loginuser.model';
import { AuthenticationService } from 'app/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationService) {}

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      pwd: ['', Validators.required]
    });
  }

  onSubmit({ loginUser, valid }: { loginUser: LoginUser; valid: boolean }) {
    ;
  }
}
