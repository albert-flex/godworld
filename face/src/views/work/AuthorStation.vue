<template>
  <div class="authorStation">
    <main>
      <div style="display: flex">
        <a-image
          :width="150"
          :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
        />
        <div class="info">
          <h2>作者: {{ authorInfo.authorName }}</h2>
          <h2>创建时间: {{ authorInfo.inTime }}</h2>
          <h2>代表作: 《{{ authorInfo.presentBookName }}》</h2>
          <h2>官方邮箱: {{ authorInfo.email }}</h2>
        </div>
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
                  src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
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
            :options="options"
          ></a-select>
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
    <a-button type="primary" @click="showAuthor">Open</a-button>

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
          <a-select
            v-model:value="newVolume.preVolume"
          >
            <a-select-option
              v-for="item in volumes"
              :key="item.id"
              :value="item.id"
            >{{item.name}}</a-select-option>
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
          <a-select v-model:value="editVolume.preVolume">
            <a-select-option
              v-for="item in volumes"
              :key="item.id"
              :value="item.id"
            >{{item.name}}</a-select-option>
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
            >{{item.name}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="上一章节">
          <a-select
            v-model:value="newChapter.prevChapterId"
            :options="prevChapterLists"
          ></a-select>
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
            >{{item.name}}</a-select-option>
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
              :value="item.title"
            ></a-select-option>
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
          <a-select
            v-model:value="newBook.boardName"
            :options="boards"
            style="width: 120px"
          >
          </a-select>
        </a-form-item>
        <a-form-item label="tags">
          <a-select
            v-model:value="newBook.tags"
            :options="options"
            :size="size"
            mode="tags"
            placeholder="Please select"
            style="width: 200px"
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
          <a-avatar shape="square" :size="64">
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
} from "../../ports/book.js";
import { ref } from "@vue/reactivity";
import { useRoute, useRouter } from "vue-router";

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
  boardName: "",
  tags: [],
  description: ``,
});

const newVolume = ref({
  name: "",
  preVolume: "",
});

const editVolume = ref({
  id: "",
  name: "",
  preVolume: "",
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
    editBookInfo.value.boardName = data.boardName;
    editBookInfo.value.tags = data.tags;
    editBookInfo.value.description = data.description;
    editBookInfo.value.isPresent = data.isPresent;
    listVolumes();
  });
  FetchVolumesOnBook(id, (data) => {
    catalog.value = data;
  });
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
function showAuthor() {
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

function postVolume() {
  const access = loadAccess();
  addVolume(access.access_token, newVolume.value, (data) => {
    alert(data.success);
  });
}

function postChapter() {
  const access = loadAccess();
  addChapter(access.access_token, newChapter.value, (data) => {
    if (data.success) {
      alert("成功");
    } else {
      alert(data.error);
    }
  });
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

function selectVolume(item){
  console.log("volumeSelect:"+volumeSelect.value);
  editVolume.value=item;
  pageChapter(item.id);  
}

function selectChapter(item){
  chapterSelect.value=item.id;
  editChapter.value=item;
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
  volumeSelect = newV;
  pageChapter();
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