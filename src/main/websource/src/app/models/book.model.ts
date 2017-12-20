import { Author } from './author.model';
import { Tag } from './tag.model';

export class Book {
    _isbn13: string;
    _isbn10: string;
    _title: string;
    _published: Date;
    _publisher: string;
    _edition: number;
    _numPages: number;
    _authors: Array<Author>;
    _tags: Array<Tag>;

    constructor(
        isbn13: string,
        isbn10: string,
        title: string,
        published: Date,
        publisher: string,
        edition: number,
        numPages: number,
        authors: Array<Author>,
        tags: Array<Tag>) {

        this._isbn13 = isbn13;
        this._isbn10 = isbn10;
        this._title = title;
        this._published = published;
        this._publisher = publisher;
        this._edition = edition;
        this._numPages = numPages;
        this._authors = authors;
        this._tags = tags;
    }

    get isbn13(): string {
        return this._isbn13;
    }

    set isbn13(v: string) {
        this._isbn13 = v;
    }

    get isbn10(): string {
        return this._isbn13;
    }

    set isbn10(v: string) {
        this._isbn13 = v;
    }

    get title(): string {
        return this._title;
    }

    set title(v: string) {
        this._title = v;
    }

    get published(): Date {
        return this._published;
    }

    set published(v: Date) {
        this._published = v;
    }

    get publisher(): string {
        return this._publisher;
    }

    set publisher(v: string) {
        this._publisher = v;
    }

    get edition(): number {
        return this._edition;
    }

    set edition(v: number) {
        this._edition = v;
    }

    get numPages(): number {
        return this._numPages;
    }

    set numPages(v: number) {
        this._numPages = v;
    }

    get authors(): Array<Author> {
        return this._authors;
    }

    set authors(v: Array<Author>) {
        this._authors = v;
    }

    get tags(): Array<Tag> {
        return this._tags;
    }

    set tags(v: Array<Tag>) {
        this._tags = v;
    }

}
