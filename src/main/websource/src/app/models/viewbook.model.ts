import { Author } from './author.model';
import { Tag } from './tag.model';

export class ViewBook {
    isbn13: string;
    isbn10: string;
    title: string;
    published: Date;
    publisher: string;
    edition: number;
    numPages: number;
    authors: Array<Author>;
    tags: Array<Tag>;
    pictureUrl; string;
    authorSummary: string;

    constructor(
        isbn13p: string,
        isbn10p: string,
        titlep: string,
        publishedp: Date,
        publisherp: string,
        editionp: number,
        numPagesp: number,
        authorsp: Array<Author>,
        tagsp: Array<Tag>,
        pictureurlp: string) {

        this.isbn13 = isbn13p;
        this.isbn10 = isbn10p;
        this.title = titlep;
        this.published = publishedp;
        this.publisher = publisherp;
        this.edition = editionp;
        this.numPages = numPagesp;
        this.authors = authorsp;
        this.tags = tagsp;
        this.pictureUrl = pictureurlp;
        this.authorSummary = this.buildAuthorSummary(authorsp);
    }

    private buildAuthorSummary(authorsp: Array<Author>): string {
        let s = '';
        if (authorsp !== null && authorsp !== undefined) {
            for (const a of authorsp) {
                if (s.length <= 0) {
                    s = a.firstName + ' ' + a.lastName;
                } else {
                    s = s + ', ' + a.firstName + ' ' + a.lastName;
                }
            }
        }
        return s;
    }

}
