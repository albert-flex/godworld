<template>
  <div class="libhome">
    <nav>
      <a href=""
        ><h2 style="text-align: center; font-size: 1.8em">
          &gt;&gt;&gt;书库&lt;&lt;&lt;
        </h2></a
      >
    </nav>
    <div class="body">
      <aside>
        <div>
          <h2>更新列表</h2>
          <a-list item-layout="vertical" :data-source="updateList">
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
                    >作者: {{ item.author }}
                  </a-button>
                </template>
              </a-list-item-meta>
            </template>
          </a-list>
        </div>
        <div v-for="item in boardData" :key="item.id">
          <h2>{{ item.name }}</h2>
          <a-list item-layout="vertical" :data-source="item.list">
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
                    >作者: {{ item.author }}
                  </a-button>
                </template>
              </a-list-item-meta>
            </template>
          </a-list>
        </div>
      </aside>
      <main>
        <a-form
          :model="formData"
          :label-col="labelCol"
          :wrapper-col="wrapperCol"
          @finish="onQuery"
        >
          <a-form-item label="标题">
            <a-input-search
              v-model:value="SearchValue"
              placeholder="input search text"
              style="width: 200px"
              @search="onSearch"
            />
          </a-form-item>
          <a-divider></a-divider>
          <a-form-item label="板块">
            <a-radio-group v-model:value="formData.board">
              <a-radio-button
                :value="item.name"
                v-for="item in boardList"
                :key="item.id"
                >{{ item.name }}</a-radio-button
              >
            </a-radio-group>
          </a-form-item>
          <a-form-item label="标签">
            <a-checkbox-group
              v-model:value="formData.tags"
              :options="tagList"
            />
          </a-form-item>
          <a-form-item label="年份">
            <a-radio-group v-model:value="formData.year">
              <a-radio-button
                :value="item.id"
                v-for="item in years"
                :key="item.id"
                >{{ item.name }}</a-radio-button
              >
            </a-radio-group>
          </a-form-item>
          <a-form-item label="月份">
            <a-radio-group v-model:value="formData.month">
              <a-radio-button
                :value="item"
                v-for="item in months"
                :key="item.id"
                >{{ item }}月</a-radio-button
              >
            </a-radio-group>
          </a-form-item>
          <a-form-item :wrapper-col="{ offset: 5, span: 20 }">
            <a-button type="primary" html-type="submit">查询</a-button>
          </a-form-item>
          <a-form-item label="*">
            <a-list
              item-layout="vertical"
              size="normal"
              :pagination="pagination"
              :data-source="listData"
            >
              <template #renderItem="{ item }">
                <a-list-item key="item.name">
                  <template #extra>
                    <img
                      width="272"
                      alt="logo"
                      :src="FetchImage(LIB_BOOK_IMAGE, item.id)"
                    />
                  </template>
                  <a-list-item-meta
                    :description="item.updateTime + ' ' + item.author"
                  >
                    <template #title>
                      <a-button type="link" @click="toBook(item.id)">{{
                        item.name
                      }}</a-button>
                    </template>
                  </a-list-item-meta>
                  {{ item.content }}
                </a-list-item>
              </template>
            </a-list>
          </a-form-item>
        </a-form>
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import {
  NewestBooksPort,
  AllBoardBooks,
  FetchBooksByName,
  FetchBoardList,
  FetchTagList,
  QueryBooks,
} from "../../ports/book.js";
import {
  StarOutlined,
  LikeOutlined,
  MessageOutlined,
} from "@ant-design/icons-vue";
import { useRouter } from "vue-router";
import { FetchImage, LIB_BOOK_IMAGE } from "../../ports/file.js";

const router = useRouter();

const updateList = ref([]);
NewestBooksPort((data) => {
  updateList.value = data.records;
});

function toBook(book) {
  router.push({ name: "book", params: { id: book } });
}

const boardData = ref([]);
AllBoardBooks((data) => {
  boardData.value = [];
  for (let key in data) {
    if (data[key].length > 0) {
      boardData.value.push({
        name: key,
        list: data[key],
      });
    }
  }
});

const SearchValue = ref("");
const listData = ref([]);
function onSearch() {
  FetchBooksByName(SearchValue.value, (data) => {
    listData.value = data.records;
  });
}

const boardList = ref([]);
FetchBoardList((data) => {
  boardList.value = data;
  formData.value.board = data[0].name;
});

const tagList = ref([]);
FetchTagList((data) => {
  tagList.value = [];
  for (let i = 0; i < data.length; ++i) {
    tagList.value.push({
      label: data[i].name,
      value: data[i].name,
    });
  }
  formData.value.tags = [];
  formData.value.tags.push(data[0].name);
});

const formData = ref({
  board: boardList.value[0],
  tags: [],
  year: 2022,
  month: 1,
});

const formPage = ref({
  current: 1,
  size: 2,
});

const pagination = ref({
  onChange: (page) => {
    formPage.value.current = page;
    onQuery();
  },
  pageSize: 3,
  total: 10,
  current: "1",
});

function onQuery() {
  QueryBooks(formPage.value, formData.value, (data) => {
    listData.value = data.records;
    pagination.value.total = data.total;
    pagination.value.pageSize = data.size;
    pagination.value.current = Number.parseInt(data.current);
  });
}

const labelCol = { style: { width: "150px" } };

const wrapperCol = { span: 20 };

const date = new Date();
const yearNum = date.getFullYear();
const years = [];
for (let i = 0; i != 8; ++i) {
  years.push({
    id: yearNum - i,
    name: yearNum - i + "年",
  });
}
years.push({
  id: -1,
  name: "更多",
});

const months = [];
for (let i = 1; i <= 12; ++i) {
  months.push(i);
}

const actions = [
  {
    type: "StarOutlined",
    text: "156",
  },
  {
    type: "LikeOutlined",
    text: "156",
  },
  {
    type: "MessageOutlined",
    text: "2",
  },
];
</script>

<style scoped>
.body {
  display: flex;
}

.body aside {
  width: 20%;
}

.body main {
  width: 70%;
}

.libhome {
  width: 80%;
  margin: auto;
}
</style>