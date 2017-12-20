import { Book } from './book.model';

export class Bookshelf {
    _title: string;
    _books: Array<Book>;

    constructor(title: string, books: Array<Book>) {
        this._title = title;
        this._books = books;
    }

    get title(): string {
        return this._title;
    }

    set title(v: string) {
        this._title = v;
    }

    get books(): Array<Book> {
        return this._books;
    }

    set books(v: Array<Book>) {
        this._books = v;
    }

}
