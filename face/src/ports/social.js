
import { UrlPatch, RequestGet, URLConcat } from "./port";


//社团信息查询
const query_by_name = UrlPatch("social/query/:1");
//最新公告社团
const new_ann = UrlPatch("social/new_ann");
//最新活动社团
const new_act = UrlPatch("social/new_act");
//获取社团信息
const social_info =UrlPatch("social/info/:1");
//获取社团公告列表
const announce_list=UrlPatch("social_announce/page/social/:1");
//获取社团活动列表
const activity_list=UrlPatch("social_activity_book/activities_info_with_book/:1");
//获取社团成员信息
const members=UrlPatch("social/member/page/member/:1/:2");
//注册社团
const register=UrlPatch("social");
//修改社团信息
const modify_social=UrlPatch("social");
//发布社团活动
const add_act=UrlPatch("social_activity");
//发布社团公告
const add_ann=UrlPatch("social_announce");
//删除活动
const remove_act=UrlPatch("social_activity/:1");
//删除公告
const remove_ann=UrlPatch("social_announce/:1");
//编辑公告
const edit_ann=UrlPatch("social_announce");
//获取请求列表
const request_list=UrlPatch("social_request/page/:1");
//确认请求
const request_ok=UrlPatch("social_request/confirm/:1/:2");
//拒绝请求
const request_refuse=UrlPatch("social_request/refuse/:1/:2");

function QueryByName(name,page,successCall){
    RequestGet(URLConcat(query_by_name,[name],page),'GET',successCall);
}

function FetchNewAnn(page,successCall){
    RequestGet(URLConcat(new_ann,[],page),'GET',successCall);
}

function FetchNewAct(page,successCall){
    RequestGet(URLConcat(new_act,[],page),'GET',successCall);
}

function FetchSocialInfo(socialId,successCall){
    RequestGet(URLConcat(social_info,[socialId]),'GET',successCall);
}

function FetchAnnList(socialId,page,successCall){
    RequestGet(URLConcat(announce_list,[socialId],page),'GET',successCall);
}

function FetchActList(socialId,page,successCall){
    RequestGet(URLConcat(activity_list,[socialId],page),'GET',successCall);
}

function FetchMembers(sociaId,type,page,successCall){
    RequestGet(URLConcat(members,[sociaId,type],page),'GET',successCall);
}

function addAnn(token,ann,successCall){
    let api=add_ann;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api), {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(ann),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

function addAct(token,act,successCall){
    let api=add_act;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api), {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(act),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

function removeAnn(token,id,successCall){
    let api=remove_ann;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api,[id]), {
        method: "DELETE",
        headers: myHeader,
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

function removeAct(token,id,successCall){
    let api=remove_act;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api,[id]), {
        method: "DELETE",
        headers: myHeader,
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

function editAnnounce(token,act,successCall){
    let api=edit_ann;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api), {
        method: "PUT",
        headers: myHeader,
        body: JSON.stringify(act),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}


function RegitserSocial(token,socialIfo,successCall){
    let api = register;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(api, {
        method: "POST",
        headers: myHeader,
        body: JSON.stringify(socialIfo),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

function PageRequest(socialId,page,successCall){
    RequestGet(URLConcat(request_list,[socialId],page),'GET',successCall);
}

function confirmRequest(token,requestId,adminMemId,successCall){
    let api = request_ok;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api,[adminMemId,requestId]), {
        method: "PUT",
        headers: myHeader,
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}

function refuseRequest(token,requestId,adminMemId,successCall){
    let api = request_refuse;
    let myHeader = new Headers();
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(URLConcat(api,[adminMemId,requestId]), {
        method: "PUT",
        headers: myHeader,
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}


function EditSocial(token,social,successCall){
    let api = modify_social;
    let myHeader = new Headers();
    myHeader.append('Content-Type', 'application/json');
    myHeader.append('Authorization', 'bearer ' + token);
    fetch(api, {
        method: "PUT",
        headers: myHeader,
        body: JSON.stringify(social),
        mode: "cors"
    }).then(res => res.json())
        .then(data => successCall(data))
        .catch(error => alert(error));
}
export {
    QueryByName,
    FetchMembers,
    FetchActList,
    FetchAnnList,
    FetchNewAct,
    FetchNewAnn,
    FetchSocialInfo,
    RegitserSocial,
    addAct,
    addAnn,
    editAnnounce,
    removeAct,
    removeAnn,
    PageRequest,
    confirmRequest,
    refuseRequest,
    EditSocial
}