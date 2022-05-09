import { defineStore } from "pinia";

const loginData = defineStore('loginData', {
    state: () => {
        return {
            logined: false,
            access_token: '0',
            refresh_token: '0',
        }
    },

    actions: {
        isLogined() {
            return this.logined;
        },
        access() {
            return this.access_token;
        },
        reset() {
            this.logined = false;
            this.access_token = '';
            this.refresh_token = '';
        },
        refresh() {
            return this.refresh_token;
        },
        set(token) {
            this.access_token = token;
            this.logined = true;
        },
        setRefresh(token) {
            this.refresh_token = token;
            this.logined = true;
        }
    }
});

const userData = defineStore('userData', {
    state: () => {
        return {
            userId: '0',
            userName: '',
            email: '',
            authorId: '',
            socialId: '',
        }
    },
    actions: {
        refresh(data) {
            this.userId = data.userId;
            this.email = data.email;
            this.userName = data.userName;
            this.authorId = data.authorId;
            this.socialId = data.socialId;
        },
    }
});

export {
    loginData,
    userData,
}