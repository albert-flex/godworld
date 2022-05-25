
import { UrlPatch, RequestGet, URLConcat } from "./port";

const LIB_BOOK_IMAGE="book-picture";
const LIB_USER_AVATOR="user-avator";
const LIB_AUTHOR_AVATOR="author-avator";
const LIB_SOCIAL_AVATOR="social-picture";
const LIB_ACTIVITY_AVATOR="activity-pictu";

function FetchImage(lib, ownId) {
    return URLConcat(UrlPatch("file/download2"), [], { lib: lib, ownId: ownId });
}

function setFile(lib,ownId,file,successCall){
    //UPLOAD FILE...
}

export {
    FetchImage,
    setFile,
    LIB_ACTIVITY_AVATOR,
    LIB_AUTHOR_AVATOR,
    LIB_BOOK_IMAGE,
    LIB_SOCIAL_AVATOR,
    LIB_USER_AVATOR,
}