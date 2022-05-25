<template>
  <div class="bookcontent">
    <div class="body">
      <aside>
        <div>
          <h2>同板块</h2>
          <a-list item-layout="vertical" :data-source="sameBoards">
            <template #renderItem="{ item }">
              <a-list-item-meta>
                <template #title>
                  <a-button
                    type="link"
                    @click="toBook(item.id)"
                    style="font-size: 1.2em; color: green"
                    >{{ item.name }}</a-button
                  >
                </template>
                <template #description>
                  <a-button type="link" style="font-size: 0.8em; color: grey"
                    >作者: {{ item.author }}</a-button
                  >
                </template>
              </a-list-item-meta>
            </template>
            <a-list-item></a-list-item>
          </a-list>
        </div>
        <a-divider></a-divider>
        <div>
          <h2>同标签</h2>
          <a-list item-layout="vertical" :data-source="sameTags">
            <template #renderItem="{ item }">
              <a-list-item-meta>
                <template #title>
                  <a-button
                    type="link"
                    @click="toBook(item.id)"
                    style="font-size: 1.2em; color: green"
                    >{{ item.name }}</a-button
                  >
                </template>
                <template #description>
                  <a-button type="link" style="font-size: 0.8em; color: grey"
                    >作者: {{ item.author }}</a-button
                  >
                </template>
              </a-list-item-meta>
            </template>
          </a-list>
        </div>
      </aside>
      <main>
        <header style="text-align: center">
          <h1 style="font-size: 2.3em">《{{ bookInfo.name }}》</h1>
        </header>
        <div style="display: flex">
          <a-image
            :width="200"
            :height="200"
            src="https://joeschmoe.io/api/v1/random"
          />
          <div>
            <h3 style="margin: 10px">作者: {{ bookInfo.author }}</h3>
            <h3 style="margin: 10px">板块: {{ bookInfo.boardName }}</h3>
            <h3 style="margin: 10px">更新: {{ bookInfo.updateTime }}</h3>
            <h3 style="margin: 10px">更新: {{ bookInfo.updateChapter }}</h3>
            <h3 style="margin: 10px">总字数: {{ bookInfo.wordCount }}</h3>
          </div>
        </div>
        <div>
          <h2>简介:</h2>
          <p>
            {{ bookInfo.description }}
          </p>
        </div>
        <div>
          <h2>标签:</h2>
          <div>
            <a-tag color="pink" v-for="item in bookInfo.tags" :key="item.id">{{
              item
            }}</a-tag>
          </div>
        </div>
        <a-divider><h1>目录</h1></a-divider>
        <a-collapse v-model:activeKey="activeKey" :bordered="false">
          <a-collapse-panel
            :header="item.title"
            v-for="item in catalog"
            :key="item.id"
          >
            <a-button type="ghost" @click="toChapter(it.id)" v-for="it in item.chapters" :key="it.id">
              {{ it.title }}
            </a-button>
          </a-collapse-panel>
        </a-collapse>
        <a-divider>评论</a-divider>
        <div>
          <a-list
            class="comment-list"
            :header="`共 ${commentPagi.total} 条`"
            item-layout="horizontal"
            :pagination="commentPagi"
            :data-source="comments"
          >
            <template #renderItem="{ item }">
              <a-list-item>
                <a-comment
                  :author="item.userName"
                  avatar="https://joeschmoe.io/api/v1/random"
                >
                  <template #content>
                    <p>
                      {{ item.content }}
                    </p>
                  </template>
                  <template #datetime>
                    <a-tooltip :title="item.createTime">
                      <span>{{ item.createTime }}</span>
                    </a-tooltip>
                  </template>
                </a-comment>
              </a-list-item>
            </template>
          </a-list>
          <a-comment>
            <template #avatar>
              <a-avatar
                :src="FetchImage()"
                alt="Han Solo"
              />
            </template>
            <template #content>
              <a-form-item>
                <a-textarea v-model:value="commentV.content" :rows="4" />
              </a-form-item>
              <a-form-item>
                <a-button html-type="submit" type="primary" @click="PostComm">
                  留下评论
                </a-button>
              </a-form-item>
            </template>
          </a-comment>
        </div>
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
import { loadAccess, loadUser } from "../../config/stores.js";
import { useRoute, useRouter } from "vue-router";
import {
  FetchBookVo,
  FetchBooksByBoard,
  FetchBooksBytags,
  FetchBookComments,
  FetchVolumesOnBook,
  PostComment,
} from "../../ports/book.js";
import {FetchImage,LIB_BOOK_IMAGE} from "../../ports/file.js";

dayjs.extend(relativeTime);

const route = useRoute();
const router = useRouter();

const bookId = route.params.id;
const bookInfo = ref({});

const sameBoards = ref([]);
const sameTags = ref([]);

const catalog = ref([]);

const comments = ref([]);
const commentPagi = ref({
  onChange: (page) => {
    commentPagi.value.current = page;
    QueryComments();
  },
  pageSize: 10,
  current: 1,
  total: 1,
});

function QueryComments() {
  FetchBookComments(
    { size: commentPagi.value.pageSize, current: commentPagi.value.current },
    bookId,
    (d) => {
      commentPagi.value.current = Number.parseInt(d.current);
      commentPagi.value.total = Number.parseInt(d.total);
      comments.value = [];
      comments.value = d.records;
    }
  );
}

function toBook(book) {
  router.push({ name: "book", params: { id: book } });
}

function toChapter(chapter){
  router.push({ name: "chapter", params: { id: chapter } });
}

function goToBook(book) {
  FetchBookVo(book, (data) => {
    bookInfo.value = data;
    FetchBooksByBoard(data.boardName, (da) => {
      sameBoards.value = da.records;
    });
    FetchBooksBytags(data.tags, (dat) => {
      sameTags.value = dat.records;
    });
    FetchVolumesOnBook(bookId, (data) => {
      catalog.value = data;
    });
    QueryComments();
  });
}

goToBook(bookId);

const commentV = ref({
  userId: "",
  content: "",
  bookId: "",
});

const access = loadAccess();
function PostComm() {
  if (access.access_token == "") {
    alert("请先登录，再评论");
    return;
  }

  const user = loadUser();
  commentV.value.userId = user.userId;
  commentV.value.bookId = bookId;
  PostComment(access.access_token, commentV.value, (da) => {
    if (!da.success) {
      alert("错误:" + da.error);
      return;
    }

    QueryComments();
  });
}
</script>

<style scoped>
.body {
  display: flex;
}

.body aside {
  width: 35%;
}

.body main {
  width: 50%;
}

.bookcontent {
  width: 80%;
  margin: auto;
}
</style>