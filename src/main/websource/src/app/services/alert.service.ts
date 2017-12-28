import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { Router, NavigationStart } from '@angular/router';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AlertService {

    private subject = new Subject<any>();
    private keepAfterNavigationChange = false;

    constructor(private router: Router) {
        router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                if (this.keepAfterNavigationChange) {
                    this.keepAfterNavigationChange = false;
                } else {
                    this.subject.next();
                }
            }
        });
    }

    success(message: string, keepAfterNavigationChange = false) {
        this.sendMessage(message, 'success', keepAfterNavigationChange);
    }

    error(message: string, keepAfterNavigationChange = false) {
        this.sendMessage(message, 'error', keepAfterNavigationChange);
    }

    sendMessage(message: string, typep: string, keepAfterNavigationChange = false) {
        this.keepAfterNavigationChange = keepAfterNavigationChange;
        this.subject.next({
            type: typep,
            text: message
        });
    }

    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }

}
