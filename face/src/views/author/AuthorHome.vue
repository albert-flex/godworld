<template>
  <div class="authorhome">
    <main>
      <h2>查找作者</h2>
      <a-form :model="formData" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item label="名称">
          <a-input-search
            v-model:value="formData.name"
            placeholder="input search text"
            style="width: 200px"
            @search="searchByName"
          />
        </a-form-item>
        <a-form-item label="所属社团">
          <a-input-search
            v-model:value="formData.social"
            placeholder="input search text"
            style="width: 200px"
            @search="searchBySocial"
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
                item.bookName +
                ' ' +
                item.updateTime +
                ' ' +
                item.volumeName +
                ' ' +
                item.chapter
              "
            >
              <template #title>
                <a @click="toAuthor(item.id)">{{ item.authorName }}</a>
              </template>
              <template #avatar
                ><a-avatar src="https://joeschmoe.io/api/v1/random"
              /></template>
            </a-list-item-meta>
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
              :description="item.createTime + ' ' + item.bookName"
            >
              <template #title>
                <a @click="toAuthor(item.id)">{{ item.name }}</a>
              </template>
              <template #avatar
                ><a-avatar src="https://joeschmoe.io/api/v1/random"
              /></template>
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
                item.bookName +
                ' ' +
                item.updateTime +
                ' ' +
                item.volumeName +
                ' ' +
                item.chapter
              "
            >
              <template #title>
                <a @click="toAuthor(item.id)">{{ item.authorName }}</a>
              </template>
              <template #avatar
                ><a-avatar src="https://joeschmoe.io/api/v1/random"
              /></template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </aside>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import {
  FetchNewestPage,
  FetchUpdatePage,
  QueryNamePage,
  QuerySocialPage,
} from "../../ports/author.js";
import { useRouter } from "vue-router";

const labelCol = { style: { width: "150px" } };
const wrapperCol = { span: 14 };
const router = useRouter();

const formData = ref({
  name: "",
  social: "",
});

function toAuthor(id) {
  router.push({ name: "authorPage", params: { id: id } });
}

function searchByName() {
  if (formData.value.name !== "") {
    QueryNamePage(
      formData.value.name,
      {
        size: pagination.value.pageSize,
        current: pagination.value.current,
      },
      (data) => {
        listData.value = data.records;
        filterList(listData.value);
        pagination.value.current = Number.parseInt(data.current);
        pagination.value.total = Number.parseInt(data.total);
      }
    );
  }
}

function filterList(list) {
  for (let i = 0; i != list.length; ++i) {
    let ele = list[i];
    if (ele.chapter == null) {
      ele.chapter = "";
    }
    if (ele.volumeName == null) {
      ele.volumeName = "";
    }
    if (ele.bookName == null) {
      ele.bookName = "";
    }
    if (ele.updateTime == null) {
      ele.updateTime = "";
    }
  }
}

function searchBySocial() {
  if (formData.value.social !== "") {
    QuerySocialPage(
      formData.value.social,
      {
        size: pagination.value.pageSize,
        current: pagination.value.current,
      },
      (data) => {
        listData.value = data.records;
        filterList(listData.value);
        pagination.value.current = Number.parseInt(data.current);
        pagination.value.total = Number.parseInt(data.total);
      }
    );
  }
}

const listData = ref([]);
const pagination = ref({
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 10,
  current: 1,
  total: 1,
});

const newBookAuthors = ref([]);
FetchNewestPage({ size: 5, current: 1 }, (data) => {
  newBookAuthors.value = data.records;
});

const updateBookAuthors = ref([]);
FetchUpdatePage({ size: 5, current: 1 }, (data) => {
  updateBookAuthors.value = data.records;
});
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