
import { UrlPatch, RequestGet } from "./port";

//获取更新页
const books_fetch_newest = UrlPatch("book/page/update");
//获取根据板块排列的页
const books_fetch_by_all_board = UrlPatch("book/rank/all");
//根据作品名称哈寻分页
const books_fetch_by_name = UrlPatch("book/page/name/");
//作品查询分页
const books_query = UrlPatch("book/page/query");
//板块列表
const board_list = UrlPatch("book_board/list");
//标签列表
const tag_list = UrlPatch("book_tag/list");
//找到书本
const book_info = UrlPatch("book/get/id/");
//查找板块的分页
const books_board_page = UrlPatch("book/page/board/");
//查找同标签的分页
const books_tags_page = UrlPatch("book/page/tags");

/**
 * 
 * @param {(data)=>{}} successCall
 */
function NewestBooksPort(successCall) {
    let params = { size: 10, current: 1 };
    books_fetch_newest.search = new URLSearchParams(params).toString();
    fetch(books_fetch_newest, {
        method: "GET",
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

/**
 * 
 * @param {(data)=>{}} successCall
 */
function AllBoardBooks(successCall) {
    RequestGet(books_fetch_by_all_board, 'GET', successCall);
}

/**
 * 
 * @param {String} name
 * @param {(data)=>{}} successCall
 */
function FetchBooksByName(name, successCall) {
    let params = { size: 10, current: 1 ,name: name };
    books_fetch_by_name.search = new URLSearchParams(params).toString();
    RequestGet(books_fetch_by_name, 'GET', successCall);
}

/**
 * 
 * @param {{current: Number, size: Number}} require
 * @param {{board: String, year: Number, month: Number, tags:[String]}} body
 * @param {(data)=>{}} successCall
 */
function QueryBooks(require, body, successCall) {
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    books_query.search = new URLSearchParams(require).toString();
    fetch(books_query, {
        method: "PUT",
        body: JSON.stringify(body),
        headers: myHeader,
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

/**
 * 
 * @param {(data)=>{}} successCall
 */
function FetchBoardList(successCall) {
    RequestGet(board_list, 'GET', successCall);
}

/**
 * 
 * @param {(data)=>{}} successCall
 */
function FetchTagList(successCall) {
    RequestGet(tag_list, 'GET', successCall);
}

/**
 * 
 * @param {String} name
 * @param {(data)=>{}} successCall
 */
function FetchBooksByBoard(name, successCall) {
    let params = { size: 10, current: 1 ,board: name };
    books_board_page.search = new URLSearchParams(params).toString();
    RequestGet(books_board_page, 'GET', successCall);
}

/**
 * 
 * @param {[String]} tags
 * @param {(data)=>{}} successCall
 */
function FetchBooksBytags(tags, successCall) {
    let params = { size: 10, current: 1, tags: tags };
    books_tags_page.search = new URLSearchParams(params).toString();
    RequestGet(books_tags_page, 'GET', successCall);
}

/**
 * 
 * @param {String} id 
 * @param {(data)=>{}} successCall 
 */
function FetchBookVo(id, successCall) {
    RequestGet(book_info + id, 'GET', successCall);
}

export {
    NewestBooksPort,
    AllBoardBooks,
    FetchBooksByName,
    QueryBooks,
    FetchBoardList,
    FetchTagList,
    FetchBooksByBoard,
    FetchBooksBytags,
    FetchBookVo,
}