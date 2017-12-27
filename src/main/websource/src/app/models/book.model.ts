import { Author } from './author.model';
import { Tag } from './tag.model';

export class Book {
    isbn13: string;
    isbn10: string;
    title: string;
    published: Date;
    publisher: string;
    edition: number;
    numPages: number;
    authors: Array<Author>;
    tags: Array<Tag>;

    constructor(
        isbn13p: string,
        isbn10p: string,
        titlep: string,
        publishedp: Date,
        publisherp: string,
        editionp: number,
        numPagesp: number,
        authorsp: Array<Author>,
        tagsp: Array<Tag>) {

        this.isbn13 = isbn13p;
        this.isbn10 = isbn10p;
        this.title = titlep;
        this.published = publishedp;
        this.publisher = publisherp;
        this.edition = editionp;
        this.numPages = numPagesp;
        this.authors = authorsp;
        this.tags = tagsp;
    }

    authorSummary(): string {
        let s = '';
        if (this.authors !== null && this.authors !== undefined) {
            for (const a of this.authors) {
                if (s.length <= 0) {
                    s = a.fullName();
                } else {
                    s = s + ', ' + a.fullName();
                }
            }
        }
        return '';
    }

}
