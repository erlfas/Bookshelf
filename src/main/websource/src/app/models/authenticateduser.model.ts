export interface AuthenticatedUser {
    username: string,
    hashedApiKey: string,
    expires: string
}
