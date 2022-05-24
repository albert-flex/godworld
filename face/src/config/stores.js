
import { defineStore } from "pinia";

const NO_LOGIN_ACCESS = '';

const loadAccess = defineStore('userAccess', {
    state: () => {
        return {
            access_token: NO_LOGIN_ACCESS,
            refresh_token: NO_LOGIN_ACCESS,
        }
    },
    actions: {
        logined() {
            return this.access_token != NO_LOGIN_ACCESS;
        },
        unset() {
            this.access_token = NO_LOGIN_ACCESS;
            this.refresh_token = NO_LOGIN_ACCESS;
        },
        set(token) {
            this.access_token = token;
        },
        setRefresh(token) {
            this.refresh_token = token;
        }
    }
});

const loadUser = defineStore('userData', {
    state: () => {
        return {
            userId: NO_LOGIN_ACCESS,
            userName: NO_LOGIN_ACCESS,
            email: NO_LOGIN_ACCESS,
            authorId: NO_LOGIN_ACCESS,
            socialId: NO_LOGIN_ACCESS,
            memberId: NO_LOGIN_ACCESS,
        }
    },
    actions: {
        logined() {
            return this.userId === NO_LOGIN_ACCESS;
        },
        set(data) {
            this.userId = data.userId;
            this.email = data.email;
            this.userName = data.userName;
            this.authorId = data.authorId;
            this.socialId = data.socialId;
            this.memberId = data.memberId;
        },
        unset() {
            this.userId = NO_LOGIN_ACCESS;
            this.email = NO_LOGIN_ACCESS;
            this.userName = NO_LOGIN_ACCESS;
            this.authorId = NO_LOGIN_ACCESS;
            this.socialId = NO_LOGIN_ACCESS;
            this.memberId = NO_LOGIN_ACCESS;
        },
    }
});

export {
    loadAccess,
    loadUser
}