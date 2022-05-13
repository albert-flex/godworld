
import { UrlPatch, AuthPatch } from "./port";

const register_api = UrlPatch("user/register");
const login_api = AuthPatch("oauth/token");
const userinfo_api = UrlPatch("user/info");
const sendcaptcha_api = UrlPatch("captcha");

/**
 * 发送验证码接口
 * @param {*} email : String
 * @param {*} successCall : function(data)
 */
const sendCaptchaPort = function (email, successCall) {
    let params = { email: email };
    sendcaptcha_api.search = new URLSearchParams(params).toString();
    fetch(sendcaptcha_api, {
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
 * @param {*} require : {userName,password,email,captcha}
 * @param {*} successCall : function(data)
 */
const registerPort = function (require, successCall) {
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    fetch(register_api, {
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
 * @param {String} userName : String
 * @param {*} password : String
 * @param {*} successCall : function(data)
 */
const loginPort = function (userName, password, successCall) {
    let params = {
        client_id: 'c1', client_secret: 'secret',
        grant_type: 'password', username: userName,
        password: password
    };
    login_api.search = new URLSearchParams(params).toString();
    fetch(login_api, {
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
 * @param {*} token : String
 * @param {*} successCall : function(data)
 * @returns 
 */
const userinfoPort = function (token, successCall) {
    if (token == null || token == '') {
        alert("未登录,请先登录");
        return;
    }

    let headers = new Headers();
    headers.append("Authorization", "bearer " + token);
    fetch(userinfo_api, {
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