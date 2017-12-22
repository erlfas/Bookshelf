import { Bookshelf } from 'app/models/bookshelf.model';

export class Bookshelves {
    _bookshelves: Array<Bookshelf>;

    constructor(bookshelves: Array<Bookshelf>) {
        this._bookshelves = bookshelves;
    }

    get bookshelves(): Array<Bookshelf> {
        return this._bookshelves;
    }

    set bookshelves(v: Array<Bookshelf>) {
        this._bookshelves = v;
    }
}
