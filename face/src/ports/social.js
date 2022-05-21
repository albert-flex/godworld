
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

export {
    QueryByName,
    FetchMembers,
    FetchActList,
    FetchAnnList,
    FetchNewAct,
    FetchNewAnn,
    FetchSocialInfo,
    RegitserSocial,
}