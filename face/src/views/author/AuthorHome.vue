<template>
  <div class="authorhome">
    <main>
      <h2>查找作者</h2>
      <a-form :model="formData" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item label="名称">
          <a-input-search
            v-model:value="value"
            placeholder="input search text"
            style="width: 200px"
            @search="onSearch"
          />
        </a-form-item>
        <a-form-item label="所属社团">
          <a-input-search
            v-model:value="value2"
            placeholder="input search text"
            style="width: 200px"
            @search="onSearch"
          />
        </a-form-item>
        <a-divider></a-divider>
      </a-form>
      <a-list
        item-layout="vertical"
        size="normal"
        :pagination="pagination"
        :data-source="listData"
      >
        <template #renderItem="{ item }">
          <a-list-item key="item.id">
            <a-list-item-meta
              :description="
                item.updateTime + ' ' + item.title + ' ' + item.updateChapter
              "
            >
              <template #title>
                <a :href="item.href">{{ item.author }}</a>
              </template>
              <template #avatar><a-avatar :src="item.avatar" /></template>
            </a-list-item-meta>
            {{ item.content }}
          </a-list-item>
        </template>
      </a-list>
    </main>
    <aside>
      <a-divider>新发布小说的作者</a-divider>
      <a-list item-layout="vertical" :data-source="newBookAuthors">
        <template #renderItem="{ item }">
          <a-list-item key="item.id">
            <a-list-item-meta
              :description="
                item.updateTime + ' ' + item.title + ' ' + item.updateChapter
              "
            >
              <template #title>
                <a :href="item.href">{{ item.author }}</a>
              </template>
              <template #avatar><a-avatar :src="item.avatar" /></template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
      <a-divider>新更新小说的作者</a-divider>
      <a-list item-layout="vertical" :data-source="updateBookAuthors">
        <template #renderItem="{ item }">
          <a-list-item key="item.id">
            <a-list-item-meta
              :description="
                item.updateTime + ' ' + item.title + ' ' + item.updateChapter
              "
            >
              <template #title>
                <a :href="item.href">{{ item.author }}</a>
              </template>
              <template #avatar><a-avatar :src="item.avatar" /></template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </aside>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";

const labelCol = { style: { width: "150px" } };
const wrapperCol = { span: 14 };
const value = ref("");
const value2=ref("");
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

const newBookAuthors = [];
for (let i = 0; i < 5; ++i) {
  newBookAuthors.push({
    title: "火之异能者",
    avatar: "https://joeschmoe.io/api/v1/random",
    updateTime: "2022/05/13 12:30",
    updateChapter: "第一卷/第三章",
    author: "夏文纯一",
    content:
      "We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure)...",
  });
}

const updateBookAuthors = newBookAuthors;

const pagination = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 20,
};
</script>

<style scoped>
.authorhome {
  display: flex;
  width: 80%;
  margin: auto;
}

.authorhome aside {
  width: 30%;
}

.authorhome main {
  width: 60%;
}
</style>