export class Author {
    _firstName: string;
    _lastName: string;

    constructor(firstName: string, lastName: string) {
        this._firstName = firstName;
        this._lastName = lastName;
    }

    get firstName(): string {
        return this._firstName;
    }

    set firstName(v: string) {
        this._firstName = v;
    }

    get lastName(): string {
        return this._lastName;
    }

    set lastName(v: string) {
        this._lastName = v;
    }

}
