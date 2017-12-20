export class Tag {

    _name: string;

    constructor(name: string) {
        this._name = name;
    }

    get name(): string {
        return this._name;
    }

    set name(v: string) {
        this._name = v;
    }

}
