import { Book } from 'app/models/book.model';

export class BookToAdd {
    bookshelfTitle: string;
    username: string;
    book: Book;

    constructor(bookshelfTitle: string, username: string, book: Book) {
        this.bookshelfTitle = bookshelfTitle;
        this.username = username;
        this.book = book;
    }
}
