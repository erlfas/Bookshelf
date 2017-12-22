import { Book } from './book.model';

export class Bookshelf {
    _title: string;
    _username: string;
    _books: Array<Book>;
    _url: string;

    constructor(title: string, username: string, books: Array<Book>, url: string) {
        this._title = title;
        this._username = username;
        this._books = books;
        this._url = url;
    }

    get title(): string {
        return this._title;
    }

    set title(v: string) {
        this._title = v;
    }

    get username(): string {
        return this._username;
    }

    set username(v: string) {
        this._username = v;
    }

    get books(): Array<Book> {
        return this._books;
    }

    set books(v: Array<Book>) {
        this._books = v;
    }

    get url(): string {
        return this._url;
    }

    set url(v: string) {
        this._url = v;
    }

}
