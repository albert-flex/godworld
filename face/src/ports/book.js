
import { UrlPatch, RequestGet, URLConcat } from "./port";

//获取更新页
const books_fetch_newest = UrlPatch("book/page/update");
//获取根据板块排列的页
const books_fetch_by_all_board = UrlPatch("book/rank/all");
//根据作品名称哈寻分页
const books_fetch_by_name = UrlPatch("book/page/name/:1");
//作品查询分页
const books_query = UrlPatch("book/page/query");
//板块列表
const board_list = UrlPatch("book_board/list");
//标签列表
const tag_list = UrlPatch("book_tag/list");
//找到书本
const book_info = UrlPatch("book/get/id/:1");
//查找板块的分页
const books_board_page = UrlPatch("book/page/board/:1");
//查找同标签的分页
const books_tags_page = UrlPatch("book/page/tags");
//查找书本的评论
const comments_on_book = UrlPatch("book_comment/page/book/:1");
//获取书本的卷目信息
const volumes_on_book = UrlPatch("book_chapter/volumes/:1");
//发送评论
const send_comments = UrlPatch("book_comment");
//获取章节信息
const chapter_info = UrlPatch("book_chapter/id/:1")


/**
 * 
 * @param {(data)=>{}} successCall
 */
function NewestBooksPort(successCall) {
    let params = { size: 10, current: 1 };
    let api = URLConcat(books_fetch_newest, [], params);
    fetch(api, {
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
    RequestGet(URLConcat(books_fetch_by_all_board), 'GET', successCall);
}

/**
 * 
 * @param {String} name
 * @param {(data)=>{}} successCall
 */
function FetchBooksByName(name, successCall) {
    let params = { size: 10, current: 1 };
    let api = URLConcat(books_fetch_by_name, [name], params);
    RequestGet(api, 'GET', successCall);
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
    let api = URLConcat(books_query, [], require);
    fetch(api, {
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
    RequestGet(URLConcat(board_list), 'GET', successCall);
}

/**
 * 
 * @param {(data)=>{}} successCall
 */
function FetchTagList(successCall) {
    RequestGet(URLConcat(tag_list), 'GET', successCall);
}

/**
 * 
 * @param {String} name
 * @param {(data)=>{}} successCall
 */
function FetchBooksByBoard(name, successCall) {
    let params = { size: 10, current: 1 };
    let api = URLConcat(books_board_page, [name], params);
    RequestGet(api, 'GET', successCall);
}

/**
 * 
 * @param {[String]} tags
 * @param {(data)=>{}} successCall
 */
function FetchBooksBytags(tags, successCall) {
    let params = { size: 10, current: 1, tags: tags };
    let api = URLConcat(books_tags_page, [], params);
    RequestGet(api, 'GET', successCall);
}

/**
 * 
 * @param {String} id 
 * @param {(data)=>{}} successCall 
 */
function FetchBookVo(id, successCall) {
    RequestGet(URLConcat(book_info, [id], {}), 'GET', successCall);
}

/**
 * 
 * @param {{size: Number, current: Number}} page 
 * @param {Number} bookId 
 * @param {(data)=>{}} successCall 
 */
function FetchBookComments(page, bookId, successCall) {
    RequestGet(URLConcat(comments_on_book, [bookId], page), 'GET', successCall);
}

/**
 * 
 * @param {String} bookId 
 * @param {(data)=>{}} successCall 
 */
function FetchVolumesOnBook(bookId, successCall) {
    RequestGet(URLConcat(volumes_on_book, [bookId]), 'GET', successCall);
}

/**
 * 
 * @param {String} token 
 * @param {{userId: String, content: String, bookId:String}} comment 
 * @param {(data)=>{}} successCall 
 */
function PostComment(token, comment, successCall) {
    let api = send_comments;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(api, {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(comment),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

/**
 * 
 * @param {Number} chapterId 
 * @param {(data)=>{}} successCall 
 */
function FetchChapter(chapterId, successCall) {
    RequestGet(URLConcat(chapter_info, [chapterId], {}), 'GET', successCall);
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
    FetchBookComments,
    FetchVolumesOnBook,
    PostComment,
    FetchChapter,
}