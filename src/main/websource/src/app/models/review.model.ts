export class Review {

    id: string;
    isbn13: string;
    username: string;
    rating: string;

    constructor(
        id: string,
        isbn13: string,
        username: string,
        rating: string) {

        this.id = id;
        this.isbn13 = isbn13;
        this.username = username;
        this.rating = rating;
    }
}
