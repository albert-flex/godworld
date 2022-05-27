<template>
  <div class="authorStation">
    <main>
      <div style="display: flex">
        <a-image
          :width="150"
          :src="FetchImage(LIB_AUTHOR_AVATOR,authorInfo.id)"
        />
        <div class="info">
          <h2>作者: {{ authorInfo.authorName }}</h2>
          <h2>创建时间: {{ authorInfo.inTime }}</h2>
          <h2>代表作: 《{{ authorInfo.presentBookName }}》</h2>
          <h2>官方邮箱: {{ authorInfo.email }}</h2>
        </div>
      </div>
      <div>
        <a-button
          style="margin: 5px; padding: 5px"
          type="primary"
          @click="openAuthorEdit"
          >修改信息</a-button
        >
        <a-button
          style="margin: 5px; padding: 5px"
          type="primary"
          @click="toSocial"
          >社团工作台</a-button
        >
        <a-button @click="showInSocialView" :disabled="hasSocial" style="margin: 5px; padding: 5px" type="primary"
          >申请加入社团</a-button
        >
        <a-button @click="postOutSocial" :disabled="!hasSocial" style="margin: 5px; padding: 5px" type="primary"
          >申请退出社团</a-button
        >
        <a-button @click="requestSetAdmin" :disabled="!hasSocial" style="margin: 5px; padding: 5px" type="primary"
          >申请社团管理员</a-button
        >
        <a-button @click="requestOutAdmin" :disabled="!hasSocial" style="margin: 5px; padding: 5px" type="primary"
          >申请退出社团管理员</a-button
        >
        <a-button @click="showActInSocialView" :disabled="!hasSocial" style="margin: 5px; padding: 5px" type="primary"
          >申请加入活动</a-button
        >
        <a-button @click="showActOutSocialView" :disabled="!hasSocial" style="margin: 5px; padding: 5px" type="primary"
          >申请退出活动</a-button
        >
      </div>
      <div>
        <h2>签名:</h2>
        <p>{{ authorInfo.moto }}</p>
      </div>
      <div>
        <h2>作品</h2>
        <a-button type="primary" @click="showNewBook">+</a-button>
        <a-list
          item-layout="vertical"
          :data-source="books"
          :pagination="booksPagi"
        >
          <template #renderItem="{ item }">
            <a-list-item key="item.id">
              <template #extra>
                <img
                  width="150"
                  alt="logo"
                  :src="FetchImage(LIB_BOOK_IMAGE,item.id)"
                />
              </template>
              <a-list-item-meta
                :description="
                  item.updateTime +
                  ' ' +
                  item.updateVolume +
                  '-' +
                  item.updateChapter
                "
              >
                <template #title>
                  <a-button @click="bookSelect(item.id)">{{
                    item.name
                  }}</a-button>
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </div>
    </main>
    <aside>
      <a-divider
        ><h2>{{ editBookInfo.name }}</h2></a-divider
      >
      <a-form
        :model="editBookInfo"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="modifyBookInfo"
      >
        <a-form-item label="板块">
          <a-input
            v-model:value="editBookInfo.boardName"
            :disabled="true"
          ></a-input>
        </a-form-item>
        <a-form-item label="标签">
          <a-select
            v-model:value="editBookInfo.tags"
            mode="multiple"
            style="width: 100%"
            placeholder="Please select"
          >
            <a-select-option v-for="item in options" :key="item.id" :value="item.id">{{item.value}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="简介">
          <a-textarea
            v-model:value="editBookInfo.description"
            placeholder="Basic usage"
            :rows="4"
          />
        </a-form-item>
        <a-form-item label="设为代表作">
          <a-switch
            v-model:checked="editBookInfo.isPresent"
            checked-children="开"
            un-checked-children="关"
            @change="presentToggle"
          />
        </a-form-item>
        <a-form-item :wrapper-col="{ span: 14, offset: 10 }">
          <a-button type="primary" htmlType="submit">修改</a-button>
        </a-form-item>
      </a-form>
      <a-divider>卷目</a-divider>
      <a-space>
        <a-button type="primary" @click="showNewVolume">新增卷目</a-button>
        <a-button type="primary" @click="showEditVolume">修改卷目</a-button>
        <a-button type="danger" @click="deleteVol">删除卷目</a-button>
      </a-space>
      <div style="padding: 10px">
        <a-radio-group v-model:value="volumeSelect">
          <a-radio-button
            v-for="item in volumes"
            :value="item.id"
            @click="selectVolume(item)"
            :key="item.id"
            >{{ item.name }}</a-radio-button
          >
        </a-radio-group>
      </div>
      <a-divider>所选卷目的章节</a-divider>
      <a-space>
        <a-button type="primary" @click="showNewChapter">新增章节</a-button>
        <a-button type="primary" @click="showEditChapter">修改章节</a-button>
        <a-button type="danger" @click="deleteChapter">删除章节</a-button>
      </a-space>
      <div style="padding: 10px">
        <a-radio-group v-model:value="chapterSelect">
          <a-radio-button
            v-for="item in chapters"
            :value="item.id"
            :key="item.id"
            @click="selectChapter(item)"
            >{{ item.title }}</a-radio-button
          >
        </a-radio-group>
      </div>
    </aside>

    <a-drawer
      v-model:visible="newVolumeVisi"
      style="color: red"
      title="新建卷目"
      placement="right"
    >
      <a-form
        :model="newVolume"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="postVolume"
      >
        <a-form-item label="名称">
          <a-input v-model:value="newVolume.name" placeholder="名字" />
        </a-form-item>
        <a-form-item label="上一卷目">
          <a-select v-model:value="newVolume.prevVolumeId">
            <a-select-option
              v-for="item in volumes"
              :key="item.id"
              :value="item.id"
              >{{ item.name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit">发布</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-drawer
      v-model:visible="editVolumeVisi"
      style="color: red"
      title="修改卷目"
      placement="right"
    >
      <a-form
        :model="editVolume"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="editVol"
      >
        <a-form-item label="名称">
          <a-input v-model:value="editVolume.name" placeholder="名字" />
        </a-form-item>
        <a-form-item label="上一卷目">
          <a-select v-model:value="editVolume.prevVolumeId">
            <a-select-option
              v-for="item in volumes"
              :key="item.id"
              :value="item.id"
              >{{ item.name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit">修改</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-drawer
      v-model:visible="newChapterVisi"
      class="custom-class"
      style="color: red"
      title="新建章节"
      placement="right"
    >
      <a-form
        :model="newChapter"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="postChapter"
      >
        <a-form-item label="标题">
          <a-input v-model:value="newChapter.title" placeholder="名字" />
        </a-form-item>
        <a-form-item label="所属卷目">
          <a-select v-model:value="newChapter.volumeId" style="width: 120px">
            <a-select-option
              v-for="item in volumes"
              :key="item.id"
              :value="item.id"
              >{{ item.name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="上一章节">
          <a-select
            v-model:value="newChapter.prevChapterId"
          >
            <a-select-option
              v-for="item in chapters"
              :key="item.id"
              :value="item.id"
              >{{ item.title }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-divider><h2>文章正文</h2></a-divider>
        <a-textarea
          :rows="20"
          style="overflow: scroll"
          v-model:value="newChapter.content"
        ></a-textarea>
        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit">发布</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-drawer
      v-model:visible="editChapterVisi"
      class="custom-class"
      style="color: red"
      title="修改章节"
      placement="right"
    >
      <a-form
        :model="editChapter"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="editCha"
      >
        <a-form-item label="标题">
          <a-input v-model:value="editChapter.title" placeholder="名字" />
        </a-form-item>
        <a-form-item label="所属卷目">
          <a-select v-model:value="editChapter.volumeId" style="width: 120px">
            <a-select-option
              v-for="item in volumes"
              :key="item.id"
              :value="item.id"
              >{{ item.name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="上一章节">
          <a-select
            v-model:value="editChapter.prevChapterId"
            style="width: 120px"
          >
            <a-select-option
              v-for="item in chapters"
              :key="item.id"
              :value="item.id"
              >{{ item.title }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-divider><h2>文章正文</h2></a-divider>
        <a-textarea
          :rows="20"
          style="overflow: scroll"
          v-model:value="editChapter.content"
        ></a-textarea>
        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit">发布</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-drawer
      v-model:visible="newBookVisi"
      class="custom-class"
      style="color: red"
      title="New Book"
      placement="right"
    >
      <a-form
        :model="newBook"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="postNewBook"
      >
        <a-form-item label="title">
          <a-input v-model:value="newBook.name" />
        </a-form-item>
        <a-form-item label="board">
          <a-select v-model:value="newBook.boardId" style="width: 120px">
            <a-select-option
              v-for="item in boards"
              :key="item.id"
              :value="item.id"
              >{{ item.value }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="tags">
          <a-select
            v-model:value="newBook.tags"
            :size="size"
            mode="tags"
            placeholder="Please select"
            style="width: 200px"
          >
            <a-select-option
              v-for="item in options"
              :key="item.id"
              :value="item.id"
              >{{ item.value }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="Description">
          <a-textarea v-model:value="newBook.description"></a-textarea>
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 10, span: 20 }">
          <a-button type="primary" html-type="submit">发布</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>
    <a-drawer
      v-model:visible="editAuthorVisi"
      class="custom-class"
      style="color: red"
      title="Edit Author"
      placement="right"
    >
      <a-form
        :model="authorInfo"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="changeProfile"
      >
        <a-form-item label="头像">
          <a-avatar shape="square" :avator="FetchImage(LIB_AUTHOR_AVATOR,authorInfo.id)" :size="64">
            <template #icon><UserOutlined /></template>
          </a-avatar>
        </a-form-item>
        <a-form-item label="名字">
          <a-input v-model:value="authorInfo.authorName" />
        </a-form-item>
        <a-form-item label="邮箱">
          <a-input v-model:value="authorInfo.email" />
        </a-form-item>
        <a-form-item label="签名">
          <a-textarea :row="4" v-model:value="authorInfo.moto"></a-textarea>
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 10, span: 20 }">
          <a-button type="primary" html-type="submit">修改</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-drawer
      v-model:visible="requestInSocialVisi"
      class="custom-class"
      style="color: red"
      title="申请加入社团"
      placement="right"
    >
      <a-form
        :model="requestInSocial"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="postInSocial"
      >
        <a-form-item label="社团名称">
          <a-input v-model:value="requestInSocial"></a-input>
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 10, span: 20 }">
          <a-button type="primary" html-type="submit">加入</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-drawer
      v-model:visible="requestInSocialActVisi"
      class="custom-class"
      style="color: red"
      title="申请加入活动"
      placement="right"
    >
      <a-form
        :model="requestInSocialAct"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="requestInSocialActDo"
      >
        <a-form-item label="活动">
          <a-select
            v-model:value="requestInSocialAct.activityId"
            style="width: 120px"
            @change="ActInChange"
          >
            <a-select-option
              v-for="item in socialActivity"
              :key="item.id"
              :value="item.id"
              >{{ item.value }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="书籍">
          <a-select
            v-model:value="requestInSocialAct.bookId"
            style="width: 120px"
          >
            <a-select-option
              v-for="item in socialActivity"
              :key="item.id"
              :value="item.id"
              >{{ item.value }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 10, span: 20 }">
          <a-button type="primary" html-type="submit">加入</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>
    <a-drawer
      v-model:visible="requestOutSocialActVisi"
      class="custom-class"
      style="color: red"
      title="申请移除活动"
      placement="right"
    >
      <a-form
        :model="requestOutSocialAct"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="requestOutSocialActDo"
      >
        <a-form-item label="活动">
          <a-select
            v-model:value="requestOutSocialAct.activityId"
            style="width: 120px"
            @change="ActOutChange"
          >
            <a-select-option
              v-for="item in socialActivity"
              :key="item.id"
              :value="item.id"
              >{{ item.value }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="书籍">
          <a-select
            v-model:value="requestOutSocialAct.bookId"
            style="width: 120px"
          >
            <a-select-option
              v-for="item in socialActivity"
              :key="item.id"
              :value="item.id"
              >{{ item.value }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 10, span: 20 }">
          <a-button type="primary" html-type="submit">加入</a-button>
        </a-form-item>
      </a-form>
    </a-drawer>
  </div>
</template>
<script setup>
import { UserOutlined } from "@ant-design/icons-vue";
import { loadAccess, loadUser } from "../../config/stores.js";
import { FetchAuthor, ModifyAuthor } from "../../ports/author.js";
import {
  FetchBooksOnAuthor,
  FetchBookVo,
  FetchVolumesOnBook,
  FetchTagList,
  FetchBoardList,
  postBooK,
  modifyBook,
  setPresent,
  erasePresent,
  addChapter,
  addVolume,
  modifyChapter,
  modifyVolume,
  listChapter,
  listVolume,
  removeChapter,
  removeVolume,
  FetchBookInActivity,
  FetchBookNotInActivity,
} from "../../ports/book.js";
import { FetchActivityPage } from "../../ports/social.js";
import { sendRequest, QueryByName } from "../../ports/social.js";
import { ref } from "@vue/reactivity";
import { useRoute, useRouter } from "vue-router";
import {FetchImage,LIB_BOOK_IMAGE,LIB_AUTHOR_AVATOR} from "../../ports/file.js";

const route = useRoute();
const router = useRouter();
const editVolumeVisi = ref(false);
const newChapterVisi = ref(false);
const newVolumeVisi = ref(false);
const editChapterVisi = ref(false);
const editBookInfo = ref({});
const editAuthorVisi = ref(false);
const authorInfo = ref({});
const selectBook = ref({});
const hasBooks = ref([]);

const volumeSelect = ref("");
const chapterSelect = ref("");
const volumes = ref([
  { id: 1, name: "第一卷" },
  { id: 2, name: "第二卷" },
  { id: 3, name: "第三卷" },
]);
const chapters = ref([
  { id: 1, name: "第一章" },
  { id: 2, name: "第二章" },
  { id: 3, name: "第三章" },
]);

const boards = ref([]);
const options = ref([]);

const newBookVisi = ref(false);
const newBook = ref({
  authorId: "",
  name: "",
  boardId: "",
  tags: [],
  description: ``,
});

const newVolume = ref({
  bookId: "",
  name: "",
  prevVolumeId: "0",
});

const editVolume = ref({
  id: "",
  bookId: "",
  name: "",
  prevVolumeId: "0",
});

const newContent = ref("");

const newChapter = ref({
  volumeId: "101",
  title: "",
  contentWord: "",
  content: [],
});

const editChapter = ref({
  id: "101",
  title: "",
  contentWord: "",
  content: [],
});

const books = ref([]);
const booksPagi = ref({
  onChange: (page) => {
    booksPagi.value.current = page;
    QueryBooks();
  },
  pageSize: 5,
  current: 1,
  total: 1,
});

const hasSocial=ref(false);
const requestInSocial = ref("");
const requestInSocialVisi = ref(false);
const requestInSocialAct = ref({
  activityId: "",
  activityName: "",
  bookId: "",
  bookName: "",
});
const requestInSocialActVisi = ref(false);
const requestOutSocialAct = ref({
  activityId: "",
  activityName: "",
  bookId: "",
  bookName: "",
});
const requestOutSocialActVisi = ref(false);
const activityInSocial = ref([]);
const activityBook = ref([]);
const notActivityBook = ref([]);

function fetchA() {
  const access = loadAccess();
  if (!access.logined()) {
    alert("未登录，请先登录");
    router.push({ name: "login" });
    return;
  }

  const user = loadUser();
  if (user.authorId == null) {
    alert("非作者，请先成为作者");
    router.push({ name: "authorNew" });
    return;
  }

  if(user.socialId!=null){
    hasSocial.value=true;
  }

  FetchAuthor(user.authorId, (data) => {
    authorInfo.value = data;
  });
}

function _re(page, data) {
  page.value.current = Number.parseInt(data.current);
  page.value.total = Number.parseInt(data.total);
}

function QueryBooks() {
  const user = loadUser();
  FetchBooksOnAuthor(
    user.authorId,
    {
      size: booksPagi.value.pageSize,
      current: booksPagi.value.current,
    },
    (data) => {
      books.value = data.records;
      _re(booksPagi, data);
    }
  );
}

function bookSelect(id) {
  FetchBookVo(id, (data) => {
    selectBook.value = data;
    editBookInfo.value.id = data.id;
    editBookInfo.value.authorId = data.authorId;
    editBookInfo.value.name = data.name;
    editBookInfo.value.tags=getTags(data.tags);
    editBookInfo.value.boardName = data.boardName;
    editBookInfo.value.description = data.description;
    editBookInfo.value.isPresent = data.isPresent;
    listVolumes();
  });
  FetchVolumesOnBook(id, (data) => {
    catalog.value = data;
  });
}

function getT(name){
  for(let i=0;i<options.value.length;++i){
    if(options.value[i].value==name){
      return options.value[i].id;
    }
  }
}

function getTags(namedTags){
  let arr=[];
  for(let i=0;i<namedTags.length;++i){
    arr.push(getT(namedTags[i]));
  }
  return arr;
}

function fetchTags() {
  FetchTagList((data) => {
    options.value = [];
    for (let i = 0; i < data.length; ++i) {
      options.value.push({
        id: data[i].id,
        value: data[i].name,
      });
    }
  });
}

function fetchBoards() {
  FetchBoardList((data) => {
    boards.value = [];
    for (let i = 0; i < data.length; ++i) {
      boards.value.push({
        id: data[i].id,
        value: data[i].name,
      });
    }
  });
}

function postNewBook() {
  const access = loadAccess();
  const user = loadUser();
  newBook.value.authorId = user.authorId;
  postBooK(access.access_token, newBook.value, (data) => {
    if (!data.success) {
      alert(data.error);
    } else {
      alert("创建成功，id:" + data.obj.id);
    }
  });
}

function presentToggle(present) {
  if (editBookInfo.value.id == null) return;
  const user = loadUser();
  const access = loadAccess();

  if (present) {
    setPresent(
      access.access_token,
      user.authorId,
      editBookInfo.value.id,
      (data) => {
        alert("设置成功");
      }
    );
  } else {
    erasePresent(access.access_token, user.authorId, (data) => {
      alert("取消成功");
    });
  }
}

function modifyBookInfo() {
  if (editBookInfo.value.id == null) return;

  const access = loadAccess();
  const user = loadUser();
  try {
    editBookInfo.value.authorId = user.authorId;
    modifyBook(access.access_token, editBookInfo.value, (data) => {
      if (!data.success) {
        alert(data.error);
      } else {
        alert("修改成功");
      }
    });
  } catch (error) {
    console.log(error);
  }
}

function changeProfile() {
  const access = loadAccess();
  ModifyAuthor(
    access.access_token,
    {
      id: authorInfo.value.id,
      name: authorInfo.value.authorName,
      email: authorInfo.value.email,
      moto: authorInfo.value.moto,
    },
    (data) => {
      if (!data.success) {
        alert(data.error);
      } else {
        alert("修改成功!");
      }
    }
  );
}

function showNewChapter() {
  newChapterVisi.value = true;
}

function showEditChapter() {
  editChapterVisi.value = true;
}
function openAuthorEdit() {
  editAuthorVisi.value = true;
}

function showNewBook() {
  newBookVisi.value = true;
}

function showNewVolume() {
  newVolumeVisi.value = true;
}

function showEditVolume() {
  editVolumeVisi.value = true;
}

function showInSocialView(){
  requestInSocialVisi.value=true;
}

function showActInSocialView(){
  QueryActivityInSocial();
  requestInSocialActVisi.value=true;
}

function ActInChange(id){
  QueryBookInActivity(id);
}

function ActOutChange(id){
  QueryBookNotInActivity(id);
}

function showActOutSocialView(){
  QueryActivityInSocial();
  requestOutSocialActVisi.value=true;
}

function postVolume() {
  const access = loadAccess();
  newVolume.value.bookId = editBookInfo.value.id;
  addVolume(access.access_token, newVolume.value, (data) => {
    alert(data.success);
  });
}

function postChapter() {
  const access = loadAccess();
  newChapter.value.bookId = editBookInfo.value.id;
  addChapter(access.access_token, newChapter.value, (data) => {
    if (data.success) {
      alert("成功");
    } else {
      alert(data.error);
    }
  });
}

function toSocial() {
  router.push({ name: "socialStation" });
}

function editVol() {
  const access = loadAccess();
  modifyVolume(access.access_token, editVolume.value, (data) => {
    if (data.success) {
      alert("成功");
    } else {
      alert(data.error);
    }
  });
}

function editCha() {
  const access = loadAccess();
  modifyChapter(access.access_token, editChapter.value, (data) => {
    if (data.success) {
      alert("成功");
    } else {
      alert(data.error);
    }
  });
}

function listVolumes() {
  listVolume(editBookInfo.value.id, (data) => {
    volumes.value = [];
    for (let i = 0; i != data.length; ++i) {
      volumes.value.push({
        id: data[i].id,
        name: data[i].name,
      });
    }
  });
}

function pageChapter(id) {
  listChapter(id, { size: 10000, current: 1 }, (data) => {
    chapters.value = data.records;
  });
}

function selectVolume(item) {
  console.log("volumeSelect:" + volumeSelect.value);
  editVolume.value = item;
  pageChapter(item.id);
}

function selectChapter(item) {
  chapterSelect.value = item.id;
  editChapter.value = item;
}

function deleteVol() {
  const access = loadAccess();
  removeVolume(access.access_token, volumeSelect.value, (data) => {
    if (data.success) {
      alert("成功");
    } else {
      alert(data.error);
    }
  });
}

function deleteChapter() {
  const access = loadAccess();
  removeChapter(access.access_token, chapterSelect.value, (data) => {
    if (data.success) {
      alert("成功");
    } else {
      alert(data.error);
    }
  });
}

function changeV(newV) {
  console.log("new Volume:" + newV);
  volumeSelect.value = newV;
  pageChapter();
}

function QueryActivityInSocial() {
  const user = loadUser();
  FetchActivityPage(user.socialId, { size: 100, current: 1 }, (data) => {
    activityInSocial.value = data.records;
  });
}

function QueryBookInActivity(id) {
  const user = loadUser();
  FetchBookInActivity(user.authorId, id, (data) => {
    activityBook.value = data.records;
  });
}

function QueryBookNotInActivity(id) {
  const user = loadUser();
  FetchBookNotInActivity(user.authorId, id, (data) => {
    notActivityBook.value = data.records;
  });
}

function postInSocial() {
  const user = loadUser();
  let request = {
    socialId: "",
    authorId: "",
    type: "",
    message: "",
    content: "",
  };

  //先查找社团
  QueryByName(requestInSocial.value, { size: 1, current: 1 }, (data) => {
    if (data.total < 1) {
      alert("查不到此社团，请重新输入社团名称");
    } else {
      _sendRe(socialId, 1, "加入社团[" + data.records[0].name + "]", "");
    }
  });
}

function postOutSocial() {
  _sendRe2(2, "想要退出社团", "");
}

function _sendRe2(type, message, content) {
  const user = loadUser();
  _sendRe(user.socialId, type, message, content);
}

function _sendRe(socialId, type, message, content) {
  const user = loadUser();
  let request = {
    socialId: socialId,
    authorId: user.authorId,
    type: type,
    message: "[" + user.userName + "]" + message,
    content: content,
  };

  const access = loadAccess();
  sendRequest(access.access_token, request, (da) => {
    if (da.id != null) {
      alert("申请成功");
    } else {
      alert("申请失败");
    }
  });
}

function requestInSocialActDo() {
  const content =
    requestInSocialAct.value.activityId + ";" + requestInSocialAct.value.bookId;
  _sendRe2(
    3,
    "加入社团活动[" +
      requestInSocialAct.value.activityName +
      "],以此书[" +
      requestInSocialAct.value.bookName +
      "]",
    content
  );
}

function requestOutSocialActDo() {
  const content =
    requestOutSocialAct.value.activityId +
    ";" +
    requestOutSocialAct.value.bookId;
  _sendRe2(
    4,
    "退出社团活动[" +
      requestOutSocialAct.value.activityName +
      "],以此书[" +
      requestOutSocialAct.value.bookName +
      "]",
    content
  );
}

function requestSetAdmin() {
  _sendRe2(5, "成为管理员", "");
}

function requestOutAdmin() {
  _sendRe2(6, "退出管理员");
}

function Init() {


  fetchA();
  QueryBooks();
  fetchTags();
  fetchBoards();
}

const labelCol = { span: 7 };
const wrapperCol = { span: 14 };

const labelCol2 = { span: 7 };
const wrapperCol2 = { span: 14 };

const catalog = ref([]);

Init();
</script>

<style scoped>
.authorStation {
  display: flex;
  width: 80%;
  margin: auto;
}

.authorStation main {
  width: 50%;
  margin-right: 10%;
}

.authorStation aside {
  width: 40%;
}

.info h2 {
  font-size: 1.3em;
  margin-left: 1em;
}

</style>