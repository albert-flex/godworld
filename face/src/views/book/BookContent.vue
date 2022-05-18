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
            :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
            fallback="data:image/png;base64,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"
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
            :header="item.name"
            v-for="item in catalog"
            :key="item.id"
          >
            <a-button type="ghost" v-for="it in item.chapters" :key="it.id">
              {{ it.name }}
            </a-button>
          </a-collapse-panel>
        </a-collapse>
        <a-divider>评论</a-divider>
        <div>
          <a-list
            class="comment-list"
            :header="`${data.length} 条`"
            item-layout="horizontal"
            :data-source="data"
          >
            <template #renderItem="{ item }">
              <a-list-item>
                <a-comment :author="item.author" :avatar="item.avatar">
                  <template #content>
                    <p>
                      {{ item.content }}
                    </p>
                  </template>
                  <template #datetime>
                    <a-tooltip
                      :title="item.datetime.format('YYYY-MM-DD HH:mm:ss')"
                    >
                      <span>{{ item.datetime.fromNow() }}</span>
                    </a-tooltip>
                  </template>
                </a-comment>
              </a-list-item>
            </template>
          </a-list>
          <a-pagination v-model:current="current" :total="50" show-less-items />
          <a-comment>
            <template #avatar>
              <a-avatar
                src="https://joeschmoe.io/api/v1/random"
                alt="Han Solo"
              />
            </template>
            <template #content>
              <a-form-item>
                <a-textarea v-model:value="commentV" :rows="4" />
              </a-form-item>
              <a-form-item>
                <a-button
                  html-type="submit"
                  type="primary"
                  @click="handleSubmit"
                >
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
import { useRoute } from "vue-router";
import {
  FetchBookVo,
  FetchBooksByBoard,
  FetchBooksBytags,
} from "../../ports/book.js";
dayjs.extend(relativeTime);

const random = ref();
const route = useRoute();
const bookId = route.params.id;
const bookInfo = ref({});
FetchBookVo(bookId,(data)=>{
  bookInfo.value=data;
  FetchBooksByBoard(data.boardName,(da)=>{
    sameBoards.value=da.records;
  });
  FetchBooksBytags(data.tags,(dat)=>{
    sameTags.value=dat.records;
  })
});
const sameBoards = ref([]);
const sameTags=ref([]);

const data = [
  {
    author: "Han Solo",
    avatar: "https://joeschmoe.io/api/v1/random",
    content:
      "We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.",
    datetime: dayjs().subtract(1, "days"),
  },
  {
    author: "Han Solo",
    avatar: "https://joeschmoe.io/api/v1/random",
    content:
      "We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.",
    datetime: dayjs().subtract(2, "days"),
  },
];


const catalog = [
  {
    id: "101",
    name: "第一卷 狂僵异变",
    chapters: [
      {
        id: "101",
        name: "第一章 开幕",
      },
      {
        id: "102",
        name: "第二章 异变",
      },
      {
        id: "103",
        name: "第三章 拯救",
      },
      {
        id: "104",
        name: "第四章 打扫",
      },
      {
        id: "105",
        name: "第五章 据点",
      },
      {
        id: "106",
        name: "第六章 入侵",
      },
      {
        id: "107",
        name: "第七章 抛弃",
      },
    ],
  },
  {
    id: "102",
    name: "第二卷 生存方舟",
    chapters: [],
  },
  {
    id: "103",
    name: "第三卷 死亡世界",
    chapters: [],
  },
];

const commentV = ref("");

const current = ref("2");
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