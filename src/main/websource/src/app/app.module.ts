import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { HttpClient, HttpHeaders, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from 'app/app.component';
import { LoginComponent } from 'app/login/login.component';
import { HomeComponent } from 'app/home/home.component';
import { RegisterComponent } from 'app/register/register.component';
import { AuthGuard } from 'app/auth/authguard';
import { ApiKeyInterceptor } from 'app/auth/apikeyinterceptor';
import { AuthenticationService } from 'app/auth/auth.service';
import { BookshelfComponent } from 'app/bookshelf/bookshelf.component';
import { BookshelfService } from 'app/services/bookshelf.service';
import { BookshelfcontentComponent } from 'app/bookshelfcontent/bookshelfcontent.component';
import { DynamicFormModule } from 'app/dynamic-form/dynamic-form.module';
import { FormInputComponent } from 'app/dynamic-form/components/form-input/form-input.component';
import { FormSelectComponent } from 'app/dynamic-form/components/form-select/form-select.component';
import { FormButtonComponent } from 'app/dynamic-form/components/form-button/form-button.component';
import { DynamicFieldDirective } from 'app/dynamic-form/components/dynamic-field/dynamic-field.directive';
import { RegisterBookComponent } from 'app/register-book/register-book.component';
import { BookService } from 'app/services/book.service';
import { AlertService } from 'app/services/alert.service';
import { AlertComponent } from 'app/alert/alert.component';
import { SearchBookComponent } from 'app/search-book/search-book.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LoginComponent },
  { path: 'bookshelf', component: BookshelfComponent, canActivate: [AuthGuard]},
  { path: 'bookshelfcontent/:id', component: BookshelfcontentComponent, canActivate: [AuthGuard]},
  { path: 'registerbook', component: RegisterBookComponent, canActivate: [AuthGuard]},
  { path: 'searchbook', component: SearchBookComponent},
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    BookshelfComponent,
    BookshelfcontentComponent,
    RegisterBookComponent,
    AlertComponent,
    SearchBookComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    DynamicFormModule
  ],
  providers: [
    AuthGuard,
    AuthenticationService,
    BookshelfService,
    BookService,
    AlertService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ApiKeyInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
