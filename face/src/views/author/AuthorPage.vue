<template>
  <div class="authorpage">
    <main>
      <h2>{{ author.authorName }}</h2>
      <div style="display: flex">
        <a-image
          :width="200"
          :height="200"
          :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
          fallback="data:image/png;base64,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"
        />
        <div class="info">
          <h2>
            代表作:
            <a @click="toBook(author.presentBookId)"
              >《{{ author.presentBookName }}》</a
            >
          </h2>
          <h2>加入时间: {{ author.inTime }}</h2>
          <h2>
            更新小说:
            <a @click="toBook(author.updateBookId)"
              >《{{ author.updateBookName }}》</a
            >
          </h2>
          <h2>更新时间: {{ author.updateTime }}</h2>
          <h2>
            更新章节: 
            <a @click="toChapter(author.updateChapterId)">{{ author.updateVolume + " " + author.updateChapter }}</a>
          </h2>
        </div>
      </div>
      <div>
        <h2>签名:</h2>
        <p>
          {{ author.moto }}
        </p>
      </div>
      <div>
        <h2>拥有作品:</h2>
        <a-list
          item-layout="vertical"
          size="normal"
          :pagination="pagination"
          :data-source="hasBooks"
        >
          <template #renderItem="{ item }">
            <a-list-item key="item.id">
              <template #extra>
                <img
                  width="272"
                  alt="logo"
                  src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
                />
              </template>
              <a-list-item-meta
                :description="item.updateTime + ' ' + item.updateChapter"
              >
                <template #title>
                  <a-button type="link" @click="toBook(item.id)">{{
                    item.name
                  }}</a-button>
                </template>
              </a-list-item-meta>
              <div>
                <a-tag color="pink" v-for="item in item.tags" :key="item.id">{{
                  item
                }}</a-tag>
              </div>
            </a-list-item>
          </template>
        </a-list>
      </div>
    </main>
    <aside>
      <h2>所属社团: {{ author.socialName }}</h2>
      <h2>参加的活动:</h2>
      <a-list item-layout="vertical" size="normal" :data-source="activityBooks">
        <template #renderItem="{ item }">
          <a-divider>{{ item.name }}</a-divider>
          <a-list
            item-layout="vertical"
            size="normal"
            :data-source="item.books"
          >
            <template #renderItem="{ item }">
              <a-list-item key="item.id">
                <template #extra>
                  <img
                    width="272"
                    alt="logo"
                    src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
                  />
                </template>
                <a-list-item-meta>
                  <template #title>
                    <a-button type="link" @click="toBook(item.id)">{{
                      item.bookName
                    }}</a-button>
                  </template>
                </a-list-item-meta>
                <div>
                  <a-tag
                    color="pink"
                    v-for="item in item.tags"
                    :key="item.id"
                    >{{ item }}</a-tag
                  >
                </div>
              </a-list-item>
            </template>
          </a-list>
        </template>
      </a-list>
    </aside>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { useRoute, useRouter } from "vue-router";
import { FetchAuthor } from "../../ports/author.js";
import {
  FetchBooksOnActivityAuthor,
  FetchBooksOnAuthor,
} from "../../ports/book.js";

const route = useRoute();
const router = useRouter();
const author = ref({});
const authorId = ref(route.params.id);
const hasBooks = ref([]);
const activityBooks = ref([]);

const pagination = ref({
  onChange: (page) => {
    pagination.value.current = page;
    QueryAuthorBooks();
  },
  pageSize: 20,
  current: 1,
  total: 1,
});

function toBook(id) {
  router.push({ name: "book", params: { id: id } });
}

function toChapter(id) {
  router.push({ name: "chapter", params: { id: id } });
}

function filter(authorV){
  if(authorV.updateVolume==null){
    authorV.updateVolume="";
  }
  if(authorV.updateChapter==null){
    authorV.updateChapter="";
  }
}

function QueryAuthorBooks() {
  FetchBooksOnAuthor(
    authorId.value,
    { size: pagination.value.pageSize, current: pagination.value.current },
    (data) => {
      hasBooks.value = data.records;
      pagination.value.current = data.current;
      pagination.value.total = data.total;
    }
  );
}

FetchAuthor(authorId.value, (data) => {
  author.value = data;
  filter(author.value);
  QueryAuthorBooks();
});

FetchBooksOnActivityAuthor(authorId.value, (data) => {
  activityBooks.value = [];
  for (let key in data) {
    activityBooks.value.push({
      name: key,
      books: data[key],
    });
  }
});
</script>

<style scoped>
.authorpage {
  display: flex;
  width: 80%;
  margin: auto;
}

.authorpage main {
  width: 50%;
  margin-right: 10%;
}

.authorpage aside {
  width: 40%;
}

.info h2 {
  font-size: 1.2em;
  margin-left: 10px;
  margin-top: 5px;
}
</style>
