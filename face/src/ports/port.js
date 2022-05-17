
const BASEAPI = "http://localhost:9002/";
const AUTH_BASEAPI = "http://localhost:9001/";

const UrlPatch = function (url) {
    let realUrl = new URL(BASEAPI + url);
    return realUrl;
}

const AuthPatch = function (url) {
    let realUrl = new URL(AUTH_BASEAPI + url);
    return realUrl;
}

const RequestGet = function (url, method = "GET", successCall) {
    fetch(url, {
        method: method,
        mode: "cors",
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

export {
    UrlPatch,
    AuthPatch,
    RequestGet
}
