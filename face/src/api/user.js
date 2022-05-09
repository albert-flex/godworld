import { UrlPatch, AuthPatch } from "./base";

const loginPort = AuthPatch("oauth/token");
const registerPort = UrlPatch("user/register");
const sendCaptchaPort = UrlPatch("captcha");

const sendCaptchaAPI = function (email) {
    let params = { email: email };
    sendCaptchaPort.search = new URLSearchParams(params).toString();
    fetch(sendCaptchaPort, {
        method: 'POST',
        headers: {
            "Content-Type": "application/x-www-form-urlencode",
        },
        mode: ["no-cors"],
    }).then((res) => res.text())
        .then(() => { })
        .catch((error) => alert(error));
}

const registerAPI = function (require, successCall) {
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    fetch(registerPort, {
        method: 'POST',
        body: JSON.stringify(require),
        headers: myHeader
    }).then((res) => res.text())
        .then((data) => successCall(JSON.parse(data)))
        .catch((error) => console.log(error));
}

const loginAPI = function (userName, password, successCall) {
    let params = {
        client_id: 'c1', client_secret: 'secret',
        grant_type: 'password', username: userName, password: password
    };
    loginPort.search = new URLSearchParams(params).toString();
    fetch(loginPort, {
        method: 'POST',
        headers: {
            "Content-Type": "application/x-www-form-urlencode",
        },
        mode: 'cors'
    }).then(
        (res) => res.text())
        .then(
            (data) => successCall
            (JSON.parse(data))
            )
        .catch((error) => console.log(error));
}

export {
    sendCaptchaAPI,
    registerAPI,
    loginAPI,
}
