import { Book } from 'app/models/book.model';

export class Books {
    books: Array<Book>;

    constructor(books: Array<Book>) {
        this.books = books;
    }

}
