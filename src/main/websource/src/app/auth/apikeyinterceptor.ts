import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthenticatedUser } from 'app/models/authenticateduser.model';

@Injectable()
export class ApiKeyInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('interceptor');
        const item = localStorage.getItem('currentUser');
        if (item != null) {
            const currentUser: AuthenticatedUser = JSON.parse(item);
            console.log('Found currentUser: ', currentUser);
            if (currentUser != null) {
                request = request.clone({
                    setHeaders: {
                        'API-KEY': `${currentUser.apiKey}`
                    }
                });
            }
        }
        return next.handle(request);
    }
}
