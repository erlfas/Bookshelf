import { Author } from 'app/models/author.model';
import { Tag } from 'app/models/tag.model';
import { Review } from 'app/models/review.model';

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
    reviews: Array<Review>;
    pictureUrl; string;

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
        reviewsp: Array<Review>,
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
        this.reviews = reviewsp;
        this.pictureUrl = pictureurlp;
    }

}
