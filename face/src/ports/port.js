
const BASEAPI = "http://localhost:9002/";
const AUTH_BASEAPI = "http://localhost:9001/";

const UrlPatch = function (base) {
    return BASEAPI + base;
}

/**
 * URLConcat('localhost:9002/api/demo/:1/:2',[1,2])
 * 
 * @param {String} base 
 * @param {[String]} paths 
 * @param {{}} param 
 * @returns 
 */
const URLConcat= function(base,paths=[],param={}){
    for(let i=0;i<paths.length;++i){
        base=base.replace(":"+(i+1),paths[i]);
    }
    let url=new URL(base);
    url.search=new URLSearchParams(param).toString();
    return url;
}

const AuthPatch = function (base) {
    return AUTH_BASEAPI + base;
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
    RequestGet,
    URLConcat
}
