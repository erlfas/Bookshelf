webpackJsonp([1,4],{

/***/ 350:
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 350;


/***/ }),

/***/ 351:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__ = __webpack_require__(437);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__environments_environment__ = __webpack_require__(462);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_app_module__ = __webpack_require__(458);




if (__WEBPACK_IMPORTED_MODULE_2__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["a" /* enableProdMode */])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_3__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=/Users/erlend321/NetBeansProjects/Bookshelf/src/main/websource/src/main.js.map

/***/ }),

/***/ 457:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var AppComponent = (function () {
    function AppComponent() {
        this.title = 'Bookshelf';
    }
    AppComponent = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["U" /* Component */])({
            selector: 'app-root',
            template: __webpack_require__(618),
            styles: [__webpack_require__(615)]
        }), 
        __metadata('design:paramtypes', [])
    ], AppComponent);
    return AppComponent;
}());
//# sourceMappingURL=/Users/erlend321/NetBeansProjects/Bookshelf/src/main/websource/src/app.component.js.map

/***/ }),

/***/ 458:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(193);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(283);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__(290);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_component__ = __webpack_require__(457);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__login_login_component__ = __webpack_require__(459);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["b" /* NgModule */])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */],
                __WEBPACK_IMPORTED_MODULE_5__login_login_component__["a" /* LoginComponent */]
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
                __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */],
                __WEBPACK_IMPORTED_MODULE_2__angular_forms__["b" /* ReactiveFormsModule */]
            ],
            providers: [],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */]]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
//# sourceMappingURL=/Users/erlend321/NetBeansProjects/Bookshelf/src/main/websource/src/app.module.js.map

/***/ }),

/***/ 459:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(0);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_forms__ = __webpack_require__(283);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_http__ = __webpack_require__(290);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__models_userpwd_model__ = __webpack_require__(461);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__models_user_model__ = __webpack_require__(460);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LoginComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var LoginComponent = (function () {
    function LoginComponent(formBuilder, http) {
        this.formBuilder = formBuilder;
        this.http = http;
    }
    LoginComponent.prototype.ngOnInit = function () {
        this.registerForm = this.formBuilder.group({
            email: ['', __WEBPACK_IMPORTED_MODULE_1__angular_forms__["c" /* Validators */].required],
            pwd: ['', __WEBPACK_IMPORTED_MODULE_1__angular_forms__["c" /* Validators */].required],
            pwd2: ['', __WEBPACK_IMPORTED_MODULE_1__angular_forms__["c" /* Validators */].required],
            firstName: ['', __WEBPACK_IMPORTED_MODULE_1__angular_forms__["c" /* Validators */].required],
            lastName: ['', __WEBPACK_IMPORTED_MODULE_1__angular_forms__["c" /* Validators */].required],
            phone: ['', __WEBPACK_IMPORTED_MODULE_1__angular_forms__["c" /* Validators */].required]
        });
    };
    LoginComponent.prototype.onSubmit = function (_a) {
        var _this = this;
        var registeredUser = _a.registeredUser, valid = _a.valid;
        console.log(registeredUser, valid);
        var authUrl = "localhost:8080/Bookshelf/webresources/authentication/json";
        this.http
            .post(authUrl, JSON.stringify(new __WEBPACK_IMPORTED_MODULE_3__models_userpwd_model__["a" /* UserPassword */](registeredUser.email, registeredUser.password)))
            .subscribe(function (res) {
            _this.authenticatedUser = res.json();
        });
        var headers = new __WEBPACK_IMPORTED_MODULE_2__angular_http__["b" /* Headers */]();
        headers.append('API-KEY', this.authenticatedUser.hashedApiKey);
        var opts = new __WEBPACK_IMPORTED_MODULE_2__angular_http__["c" /* RequestOptions */]();
        opts.headers = headers;
        var registerUserUrl = "localhost:8080/Bookshelf/webresources/user";
        var jsonBody = JSON.stringify(new __WEBPACK_IMPORTED_MODULE_4__models_user_model__["a" /* User */](registeredUser.email, registeredUser.password, registeredUser.firstName, registeredUser.lastName, registeredUser.phone));
        this.http
            .post(registerUserUrl, jsonBody, opts)
            .subscribe(function (res) {
            if (res.ok) {
            }
            else {
            }
        });
    };
    LoginComponent = __decorate([
        __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["U" /* Component */])({
            selector: 'app-login',
            template: __webpack_require__(619),
            styles: [__webpack_require__(616)]
        }), 
        __metadata('design:paramtypes', [(typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_forms__["d" /* FormBuilder */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_1__angular_forms__["d" /* FormBuilder */]) === 'function' && _a) || Object, (typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_http__["d" /* Http */] !== 'undefined' && __WEBPACK_IMPORTED_MODULE_2__angular_http__["d" /* Http */]) === 'function' && _b) || Object])
    ], LoginComponent);
    return LoginComponent;
    var _a, _b;
}());
//# sourceMappingURL=/Users/erlend321/NetBeansProjects/Bookshelf/src/main/websource/src/login.component.js.map

/***/ }),

/***/ 460:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return User; });
var User = (function () {
    function User(email, password, firstName, lastName, phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    return User;
}());
//# sourceMappingURL=/Users/erlend321/NetBeansProjects/Bookshelf/src/main/websource/src/user.model.js.map

/***/ }),

/***/ 461:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserPassword; });
var UserPassword = (function () {
    function UserPassword(username, password) {
        this.username = username;
        this.password = password;
    }
    return UserPassword;
}());
//# sourceMappingURL=/Users/erlend321/NetBeansProjects/Bookshelf/src/main/websource/src/userpwd.model.js.map

/***/ }),

/***/ 462:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `angular-cli.json`.
var environment = {
    production: false,
    webservice: "http://localhost:8080"
};
//# sourceMappingURL=/Users/erlend321/NetBeansProjects/Bookshelf/src/main/websource/src/environment.js.map

/***/ }),

/***/ 615:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(146)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 616:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(146)(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 618:
/***/ (function(module, exports) {

module.exports = "<div class=\"container\">\n  <h1>\n    {{title}}\n  </h1>\n  <app-login></app-login>\n</div>"

/***/ }),

/***/ 619:
/***/ (function(module, exports) {

module.exports = "<h2>Register</h2>\n<form [formGroup]=\"registerForm\" (ngSubmit)=\"onSubmit(registerForm)\">\n  <div class=\"form-group row\">\n    <label for=\"inputEmail\" class=\"col-sm-2 col-form-label\">Email</label>\n    <div class=\"col-sm-10\">\n      <input type=\"email\" class=\"form-control\" id=\"inputEmail\" placeholder=\"Email\" [formControl]=\"registerForm.controls['email']\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"inputPassword\" class=\"col-sm-2 col-form-label\">Password</label>\n    <div class=\"col-sm-10\">\n      <input type=\"password\" class=\"form-control\" id=\"inputPassword\" placeholder=\"Password\" [formControl]=\"registerForm.controls['pwd']\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"inputPassword2\" class=\"col-sm-2 col-form-label\">Confirm password</label>\n    <div class=\"col-sm-10\">\n      <input type=\"password\" class=\"form-control\" id=\"inputPassword2\" placeholder=\"Confirm password\" [formControl]=\"registerForm.controls['pwd2']\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"firstName\" class=\"col-sm-2 col-form-label\">First name</label>\n    <div class=\"col-sm-10\">\n      <input type=\"text\" class=\"form-control\" id=\"firstName\" placeholder=\"First name\" [formControl]=\"registerForm.controls['firstName']\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"lastName\" class=\"col-sm-2 col-form-label\">Last name</label>\n    <div class=\"col-sm-10\">\n      <input type=\"text\" class=\"form-control\" id=\"lastName\" placeholder=\"Last name\" [formControl]=\"registerForm.controls['lastName']\">\n    </div>\n  </div>\n  <div class=\"form-group row\">\n    <label for=\"phone\" class=\"col-sm-2 col-form-label\">Phone</label>\n    <div class=\"col-sm-10\">\n      <input type=\"text\" class=\"form-control\" id=\"phone\" placeholder=\"Phone\" [formControl]=\"registerForm.controls['phone']\">\n    </div>\n  </div>\n  <button type=\"submit\" class=\"btn btn-primary\">Register</button>\n</form>"

/***/ }),

/***/ 634:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(351);


/***/ })

},[634]);
//# sourceMappingURL=main.bundle.js.map