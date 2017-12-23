import { Book } from './book.model';

export class Bookshelf {
    _id: string;
    _title: string;
    _username: string;
    _books: Array<Book>;

    constructor(id: string, title: string, username: string, books: Array<Book>) {
        this._id = id;
        this._title = title;
        this._username = username;
        this._books = books;
    }

    get id(): string {
        return this._id;
    }

    set id(v: string) {
        this._id = v;
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

}
