
import { UrlPatch, AuthPatch , URLConcat } from "./port";

const register_api = UrlPatch("user/register");
const login_api = AuthPatch("oauth/token");
const userinfo_api = UrlPatch("user/info");
const sendcaptcha_api = UrlPatch("captcha");

/**
 * 发送验证码接口
 * @param {String} email
 * @param {(data)=>{}} successCall
 */
const sendCaptchaPort = function (email, successCall) {
    let params = { email: email };
    let api = URLConcat(sendcaptcha_api,[],params);
    fetch(api, {
        method: 'POST',
        headers: {
            "Content-Type": "application/x-www-form-urlencode",
        },
        mode: "cors"
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

/**
 * 注册接口
 * @param {{userName: String, password: String, email: String, captcha: String}} require
 * @param {(data)=>{}} successCall
 */
const registerPort = function (require, successCall) {
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    let api=URLConcat(register_api,[],{});
    fetch(api, {
        method: 'POST',
        body: JSON.stringify(require),
        headers: myHeader,
        mode: 'cors'
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

/**
 * 登录接口
 * @param {String} userName
 * @param {String} password
 * @param {(data)=>{}} successCall
 */
const loginPort = function (userName, password, successCall) {
    let params = {
        client_id: 'c1', client_secret: 'secret',
        grant_type: 'password', username: userName,
        password: password
    };
    let api=URLConcat(login_api,[],params);
    fetch(api, {
        method: 'POST',
        headers: {
            "Content-Type": "application/x-www-form-urlencode",
        },
        mode: 'cors'
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

/**
 * 根据用户凭证获取用户信息
 * @param {String} token
 * @param {(data)=>{}} successCall
 * @returns 
 */
const userinfoPort = function (token, successCall) {
    if (token == null || token == '') {
        alert("未登录,请先登录");
        return;
    }

    let headers = new Headers();
    headers.append("Authorization", "bearer " + token);
    let api=URLConcat(userinfo_api,[],{});
    fetch(api, {
        method: 'GET',
        headers: headers,
        mode: 'cors'
    }).then((res) => res.json())
        .then((data) => successCall(data))
        .catch((error) => alert(error));
}

export {
    sendCaptchaPort,
    registerPort,
    loginPort,
    userinfoPort,
}