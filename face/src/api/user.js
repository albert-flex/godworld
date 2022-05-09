import { UrlPatch, AuthPatch } from "./base";
import { loginData } from "../state/user";

const loginPort = AuthPatch("oauth/token");
const registerPort = UrlPatch("user/register");
const userInfoPort = UrlPatch("user/info");
const sendCaptchaPort = UrlPatch("captcha");

const sendCaptchaAPI = function (email) {
    let params = { email: email };
    sendCaptchaPort.search = new URLSearchParams(params).toString();
    fetch(sendCaptchaPort, {
        method: 'POST',
        headers: {
            "Content-Type": "application/x-www-form-urlencode",
        },
        mode: "cors"
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

const registerAPI = function (require, successCall) {
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    fetch(registerPort, {
        method: 'POST',
        body: JSON.stringify(require),
        headers: myHeader,
        mode: 'cors'
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

const loginAPI = function (userName, password, successCall) {
    let params = {
        client_id: 'c1', client_secret: 'secret',
        grant_type: 'password', username: userName,
        password: password
    };
    loginPort.search = new URLSearchParams(params).toString();
    fetch(loginPort, {
        method: 'POST',
        headers: {
            "Content-Type": "application/x-www-form-urlencode",
        },
        mode: 'cors'
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

const userInfoAPI = function (successCall) {
    const loginInfo = loginData();
    if (!loginInfo.logined) {
        return;
    }

    let headers = new Headers();
    headers.append("Authorization", "bearer " + loginInfo.access_token);
    fetch(userInfoPort, {
        method: 'GET',
        headers: headers,
        mode: 'cors'
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

export {
    sendCaptchaAPI,
    registerAPI,
    loginAPI,
    userInfoAPI,
}
