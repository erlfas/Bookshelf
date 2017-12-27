export class Author {

    id: string;
    firstName: string;
    lastName: string;

    constructor(firstName: string, lastName: string, id?: string) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    fullName(): string {
        return this.firstName + " " + this.lastName;
    }

}
