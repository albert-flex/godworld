<template>
  <div class="socialhome">
    <main>
      <a-button type="primary" @click="toSocialNew">创建社团!</a-button>
      <h2 style="text-indent: 2em">查询社团</h2>
      <a-form :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item label="名称">
          <a-input-search
            v-model:value="searchName"
            placeholder="input search text"
            style="width: 200px"
            @search="Query(searchName)"
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
              <a-list-item-meta :description="'负责人 ' + item.masterName">
                <template #title>
                  <a @click="toSocial(item.id)">{{ item.name }}</a>
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
            <a-list-item-meta :description="item.actName">
              <template #title>
                <a @click="toSocial(item.id)">{{ item.name }}</a>
              </template>
            </a-list-item-meta>
            {{ item.actDesc }}
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
            <a-list-item-meta :description="item.announceName">
              <template #title>
                <a @click="toSocial(item.id)">{{ item.name }}</a>
              </template>
            </a-list-item-meta>
            {{ item.announceContent }}
          </a-list-item>
        </template>
      </a-list>
    </aside>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { FetchNewAct, FetchNewAnn, QueryByName } from "../../ports/social.js";
import { useRouter } from "vue-router";
const labelCol = { style: { width: "150px" } };
const wrapperCol = { span: 14 };

const router = useRouter();
const searchName = ref("");
const result = ref([]);
const pagination = ref({
  onChange: (page) => {
    pagination.value.current = page;
    Query(searchName.value);
  },
  pageSize: 20,
  current: 1,
  total: 1,
});
const newActSocials = ref([]);
const newAnnounceSocials = ref([]);

function Query(name) {
  QueryByName(
    name,
    { size: pagination.value.pageSize, current: pagination.value.current },
    (data) => {
      result.value = data.records;
      pagination.value.current = Number.parseInt(data.current);
      pagination.value.total = Number.parseInt(data.total);
    }
  );
}

function toSocialNew() {
  router.push({ name: "socialNew" });
}

function toSocial(id) {
  router.push({ name: "socialPage", params: { id: id } });
}

function Init() {
  FetchNewAct({ size: 5, current: 1 }, (data) => {
    newActSocials.value = data.records;
  });
  FetchNewAnn({ size: 5, current: 1 }, (data) => {
    newAnnounceSocials.value = data.records;
  });
}

Init();
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