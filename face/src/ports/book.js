
import { UrlPatch, RequestGet } from "./port";

//获取更新页
const books_fetch_newest = UrlPatch("/book/page/newest");
//获取根据板块排列的页
const books_fetch_by_all_board = UrlPatch("/book/rank/all/");
//根据作品名称哈寻分页
const books_fetch_by_name = UrlPatch("/book/page/name/");
//作品查询分页
const books_query = UrlPatch("/book/query");

function NewestBooksPort(successCall) {
    fetch(books_fetch_newest, {
        method: "GET",
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

function AllBoardBooks(successCall) {
    RequestGet(books_fetch_by_all_board, successCall);
}

function FetchBooksByName(name, successCall) {
    RequestGet(books_fetch_by_name + "name", successCall);
}

function QueryBooks(require, successCall) {
    books_query.search = new URLSearchParams(params).toString();
    RequestGet(books_query,successCall); 
}

export {
    NewestBooksPort,
    AllBoardBooks,
    FetchBooksByName,
    QueryBooks
}