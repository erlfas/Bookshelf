export class AuthenticatedUser {
    constructor(
        public username: string,
        public hashedApiKey: string,
        public expires: Date) {}
}
