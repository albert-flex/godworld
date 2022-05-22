
import { UrlPatch, RequestGet, URLConcat } from "./port";


//更新分页
const update_page = UrlPatch("author/page/update");
//最新分页
const newest_page = UrlPatch("author/page/newest");
//根据名称查询
const query_by_name = UrlPatch("author/page/name/:1");
//根据社团名查询
const query_by_social = UrlPatch("author/page/social/:1");
//获取作者视图信息
const author_vo = UrlPatch("author/id/:1");
//注册作者
const reister_author = UrlPatch("author");
//修改作者信息
const modify_author = UrlPatch("author/update/vo");

/**
 * 
 * @param {{size: Number, current: Number}} page 
 * @param {(data)=>{}} successCall 
 */
function FetchUpdatePage(page, successCall) {
    RequestGet(URLConcat(update_page, [], page), 'GET', successCall);
}

/**
 * 
 * @param {{size: Number, current: Number}} page 
 * @param {(data)=>{}} successCall 
 */
function FetchNewestPage(page, successCall) {
    RequestGet(URLConcat(newest_page, [], page), 'GET', successCall);
}

/**
 * @param {String} name
 * @param {{size: Number, current: Number}} page 
 * @param {(data)=>{}} successCall 
 */
function QueryNamePage(name, page, successCall) {
    RequestGet(URLConcat(query_by_name, [name], page), 'GET', successCall);
}

/**
 * @param {String} name
 * @param {{size: Number, current: Number}} page 
 * @param {(data)=>{}} successCall 
 */
function QuerySocialPage(name, page, successCall) {
    RequestGet(URLConcat(query_by_social, [name], page), 'GET', successCall);
}

/**
 * 
 * @param {String} id 
 * @param {(data)=>{}} successCall 
 */
function FetchAuthor(id, successCall) {
    RequestGet(URLConcat(author_vo, [id]), 'GET', successCall);
}

/**
 * 
 * @param {String} token 
 * @param {{name: String,email:String,captca:Number}} author 
 * @param {(data:{
 *  success: Boolean,
 *  code: Number,
 *  obj: {},
 *  error: String,
 * })=>{}} successCall 
 */
function RegisterAuthor(token, author, successCall) {
    let api = reister_author;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(api, {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(author),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

/**
 * 
 * @param {String} token 
 * @param {{id:String,name:String,email:String,moto:String}} author 
 * @param {(data)=>{}} successCall 
 */
function ModifyAuthor(token, author, successCall) {
    let api = modify_author;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(api, {
        method: "PUT",
        headers: myHeader,
        body: JSON.stringify(author),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

export {
    FetchUpdatePage,
    FetchNewestPage,
    QueryNamePage,
    QuerySocialPage,
    FetchAuthor,
    RegisterAuthor,
    ModifyAuthor
}