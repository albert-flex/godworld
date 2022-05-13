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
          <a-list item-layout="vertical" :data-source="updates">
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
                    >作者: {{ item.author }}
                  </a-button>
                </template>
              </a-list-item-meta>
            </template>
          </a-list>
        </div>
        <div v-for="item in d_boards" :key="item.id">
          <h2>{{ item.name }}</h2>
          <a-list item-layout="vertical" :data-source="item.list">
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
        >
          <a-form-item label="标题">
            <a-input-search
              v-model:value="value"
              placeholder="input search text"
              style="width: 200px"
              @search="onSearch"
            />
          </a-form-item>
          <a-divider></a-divider>
          <a-form-item label="板块">
            <a-radio-group v-model:value="value1">
              <a-radio-button
                :value="item.id"
                v-for="item in d_boards_name"
                :key="item.id"
                >{{ item.name }}</a-radio-button
              >
            </a-radio-group>
          </a-form-item>
          <a-form-item label="标签">
            <a-checkbox-group v-model:value="checkedList" :options="tags" />
          </a-form-item>
          <a-form-item label="年份">
            <a-radio-group v-model:value="value2">
              <a-radio-button
                :value="item"
                v-for="item in years"
                :key="item.id"
                >{{ item }}</a-radio-button
              >
            </a-radio-group>
          </a-form-item>
          <a-form-item label="月份">
            <a-radio-group v-model:value="value2">
              <a-radio-button
                :value="item"
                v-for="item in months"
                :key="item.id"
                >{{ item }}</a-radio-button
              >
            </a-radio-group>
          </a-form-item>
          <a-form-item label="*">
            <a-list
              item-layout="vertical"
              size="normal"
              :pagination="pagination"
              :data-source="listData"
            >
              <template #renderItem="{ item }">
                <a-list-item key="item.title">
                  <template #extra>
                    <img
                      width="272"
                      alt="logo"
                      src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
                    />
                  </template>
                  <a-list-item-meta
                    :description="
                      item.updateTime +
                      ' ' +
                      item.author +
                      ' ' +
                      item.updateChapter
                    "
                  >
                    <template #title>
                      <a :href="item.href">{{ item.title }}</a>
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
  StarOutlined,
  LikeOutlined,
  MessageOutlined,
} from "@ant-design/icons-vue";

const d_board_a = [
  { name: "作品A", id: "101", author: "夏文纯一" },
  { name: "作品B", id: "102", author: "夏文纯一" },
  { name: "作品C", id: "103", author: "夏文纯一" },
];

const d_board_b = [
  { name: "作品A", id: "101", author: "夏文纯一" },
  { name: "作品B", id: "102", author: "夏文纯一" },
  { name: "作品C", id: "103", author: "夏文纯一" },
];

const d_boards = [
  { name: "板块A", list: d_board_a },
  { name: "板块B", list: d_board_b },
];

const d_boards_name = [
  { name: "板块A", id: "101" },
  { name: "板块B", id: "102" },
  { name: "板块C", id: "103" },
  { name: "板块D", id: "104" },
];

const tags = ["恋爱", "推理", "历史", "校园"];

const checkedList = ref(["恋爱", "历史"]);
const updates = d_board_a;

const value = ref("");

const value1 = ref("");

const value2 = ref("");

const value3 = ref("");

const labelCol = { style: { width: "150px" } };

const wrapperCol = { span: 20 };

const years = [
  "2022",
  "2021",
  "2020",
  "2019",
  "2018",
  "2017",
  "2016",
  "2015",
  "2014",
  "2013",
  "2012",
  "2011",
  "更早",
];

const months = [
  "1月",
  "2月",
  "3月",
  "4月",
  "5月",
  "6月",
  "7月",
  "8月",
  "9月",
  "10月",
  "11月",
  "12月",
];

const listData = [];

for (let i = 0; i < 23; i++) {
  listData.push({
    href: "https://www.antdv.com/",
    title: "火之异能者",
    avatar: "https://joeschmoe.io/api/v1/random",
    updateTime: "2022/05/13 12:30",
    updateChapter: "第一卷/第三章",
    author: "夏文纯一",
    content:
      "We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure)...",
  });
}

const pagination = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 3,
};
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