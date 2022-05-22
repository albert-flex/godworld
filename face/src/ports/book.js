
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
const chapter_info = UrlPatch("book_chapter/id/:1");
//获取作者的作品分页
const book_on_author = UrlPatch("book/page/author/id/:1");
//获取活动分页
const book_on_activity_author = UrlPatch("social_activity_book/author/:1");
//新增一个book
const book_create = UrlPatch("book");
//设book为代表作
const book_set_present = UrlPatch("book/set_present/:1/:2");
//取消book为代表作
const book_erase_present = UrlPatch("book/erase_present/:1");
//修改book信息
const book_modify = UrlPatch("book/modify");
//新增卷目
const add_volume = UrlPatch("book_volume/create");
//修改卷目
const modify_volume = UrlPatch("book_volume/modify");
//获取卷目列表
const list_volumes_in = UrlPatch("book_volume/of/:1");
//删除卷目
const remove_volume = UrlPatch("book_volume/:1");
//新增章节
const add_chapter = UrlPatch("book_chapter/create");
//修改章节
const modify_chapter = UrlPatch("book_chapter/modify");
//获取章节列表
const list_chapter_in_volume = UrlPatch("book_chapter/page/volume/:1");
//删除章节
const remove_chapter = UrlPatch("book_chapter/:1");

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

/**
 * 
 * @param {String} authorId 
 * @param {{size: Number, current: Number}} page 
 * @param {(data)=>{}} successCall 
 */
function FetchBooksOnAuthor(authorId, page, successCall) {
    RequestGet(URLConcat(book_on_author, [authorId], page), 'GET', successCall);
}

function FetchBooksOnActivityAuthor(authorId, successCall) {
    RequestGet(URLConcat(book_on_activity_author, [authorId]), 'GET', successCall);
}


/**
 * 
 * @param {String} token 
 * @param {{name:String,boardName:String,tags:[String],description:String}} bookDTO 
 * @param {(data)=>{}} successCall 
 */
function postBooK(token, bookDTO, successCall) {
    let api = book_create;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(api, {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(bookDTO),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

/**
 * 
 * @param {String} token
 * @param {String} authorId 
 * @param {String} bookId 
 * @param {(data)=>{}} successCall 
 */
function setPresent(token, authorId, bookId, successCall) {
    let api = book_set_present;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api, [authorId, bookId]), {
        method: "PUT",
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(e => alert(e));
}

/**
 * 
 * @param {String} token 
 * @param {String} authorId 
 * @param {(data)=>{}} successCall 
 */
function erasePresent(token, authorId, successCall) {
    let api = book_erase_present;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api, [authorId]), {
        method: "PUT",
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(e => alert(e));
}

/**
 * 
 * @param {String} token 
 * @param {{authorId:String,name:String,boardName:String,description:String,tags:[String],id:String}} bookInfo 
 * @param {(data)=>{}} successCall 
 */
function modifyBook(token, bookInfo, successCall) {
    let api = book_modify;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    myHeader.append('Content-Type', 'application/json');
    fetch(URLConcat(api), {
        method: "PUT",
        headers: myHeader,
        body: JSON.stringify(bookInfo),
        mode: "cors",
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(e => alert(e));
}

/**
 * 
 * @param {String} token 
 * @param {{name: String, prevVolumeId: String}} volume 
 * @param {(data)=>{}} successCall 
 */
function addVolume(token, volume, successCall) {
    let api = add_volume;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api, [], volume), {
        method: "POST",
        headers: myHeader,
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(e => alert(e));
}

/**
 * 
 * @param {String} token 
 * @param {(id:String, name: String,prevVolumeId: String)} volume 
 * @param {(data)=>{}} successCall 
 */
function modifyVolume(token, volume, successCall) {
    let api = modify_volume;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api, [], volume), {
        method: "POST",
        headers: myHeader,
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(e => alert(e));
}

/**
 * 
 * @param {String} bookId 
 * @param {(data)=>{}} successCall 
 */
function listVolume(bookId, successCall) {
    RequestGet(URLConcat(list_volumes_in, [bookId]), "GET", successCall);
}

/**
 * 
 * @param {String} volumeId 
 * @param {(data)=>{}} successCall 
 */
function removeVolume(volumeId, successCall) {
    RequestGet(URLConcat(remove_volume, [volumeId]), "DELETE", successCall);
}


/**
 * 
 * @param {String} token 
 * @param {{bookId:String,volumeId:String,title:String,content:String,prevChapterId: String}} chapter 
 * @param {(data)=>{}} successCall 
 */
function addChapter(token, chapter, successCall) {
    let api = add_chapter;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    myHeader.append('Content-Type', 'application/json');
    fetch(URLConcat(api), {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(chapter),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(e => alert(e));
}

/**
 * 
 * @param {String} token 
 * @param {(id:String, title: String,content:String,prevChapterId: String)} volume 
 * @param {(data)=>{}} successCall 
 */
function modifyChapter(token, volume, successCall) {
    let api = modify_volume;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    myHeader.append('Content-Type', 'application/json');
    fetch(URLConcat(api, [], volume), {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(volume),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(e => alert(e));
}

/**
 * @param {{size:Number,current: Number}} page
 * @param {String} volumeId 
 * @param {(data)=>{}} successCall 
 */
function listChapter(volumeId, page, successCall) {
    RequestGet(URLConcat(list_chapter_in_volume, [volumeId], page), "GET", successCall);
}

/**
 * 
 * @param {String} volumeId 
 * @param {(data)=>{}} successCall 
 */
function removeChapter(chapterId, successCall) {
    RequestGet(URLConcat(remove_chapter, [chapterId]), "DELETE", successCall);
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
    FetchBooksOnAuthor,
    FetchBooksOnActivityAuthor,
    postBooK,
    setPresent,
    erasePresent,
    modifyBook,
    addChapter,
    addVolume,
    modifyChapter,
    modifyVolume,
    listChapter,
    listVolume,
    removeChapter,
    removeVolume
}