<template>
  <div class="socialhome">
    <main>
      <h2 style="text-indent: 2em">查询社团</h2>
      <a-form :model="formData" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item label="名称">
          <a-input-search
            v-model:value="value"
            placeholder="input search text"
            style="width: 200px"
            @search="onSearch"
          />
        </a-form-item>
      </a-form>
      <a-divider>搜索结果</a-divider>
      <div style="margin: 10%">
        <a-list
          item-layout="vertical"
          :data-source="result"
          :pagination="pagination"
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
              <a-list-item-meta :description="'负责人 ' + item.master">
                <template #title>
                  <a :href="item.href">{{ item.name }}</a>
                </template>
              </a-list-item-meta>
              {{ item.moto }}
            </a-list-item>
          </template>
        </a-list>
      </div>
    </main>
    <aside>
      <a-divider><h2>新发活动社团</h2></a-divider>
      <a-list item-layout="vertical" :data-source="newActSocials">
        <template #renderItem="{ item }">
          <a-list-item key="item.id">
            <template #extra>
              <img
                width="272"
                alt="logo"
                src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
              />
            </template>
            <a-list-item-meta :description="item.newAct">
              <template #title>
                <a :href="item.href">{{ item.name }}</a>
              </template>
            </a-list-item-meta>
            {{ item.newActContent }}
          </a-list-item>
        </template>
      </a-list>
      <a-divider><h2>新公告社团</h2></a-divider>
      <a-list item-layout="vertical" :data-source="newAnnounceSocials">
        <template #renderItem="{ item }">
          <a-list-item key="item.id">
            <template #extra>
              <img
                width="272"
                alt="logo"
                src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
              />
            </template>
            <a-list-item-meta :description="item.newAnnounce">
              <template #title>
                <a :href="item.href">{{ item.name }}</a>
              </template>
            </a-list-item-meta>
            {{ item.newAnnounceContent }}
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

const result = ref([
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
  { id: "101", name: "玄沧阁", master: "夏文纯一", moto: "快乐地写作" },
]);

const newActSocials = [];
for (let i = 0; i != 5; ++i) {
  newActSocials.push({
    id: "101",
    name: "玄沧阁",
    master: "夏文纯一",
    newAct: "活动" + i,
    newActContent: "这是一个互动内容，我们有更多内容，请点击...",
  });
}

const newAnnounceSocials = [];
for (let i = 0; i != 5; ++i) {
  newAnnounceSocials.push({
    id: "101",
    name: "玄沧阁",
    master: "夏文纯一",
    newAnnounce: "公告" + i,
    newAnnounceContent: "这是一个公告，我们没有很多内容...",
  });
}

const pagination = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 20,
};
</script>

<style scoped>
.socialhome {
  display: flex;
}

.socialhome main {
  width: 60%;
  margin-right: 5%;
}

.socialhome aside {
  width: 30%;
}
</style>