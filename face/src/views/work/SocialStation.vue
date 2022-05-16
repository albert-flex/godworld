<template>
  <div class="socialstation">
    <main>
      <div style="display: flex">
        <a-image
          :width="200"
          :height="200"
          :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
          fallback="data:image/png;base64,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"
        />
        <div>
          <h2>社团: 玄沧阁</h2>
          <h2>
            负责人:
            <a-avatar src="https://joeschmoe.io/api/v1/random" />夏文纯一
          </h2>
          <h2>代表活动: 《2020文集——逆转季节》</h2>
        </div>
      </div>
      <div>
        <h2>社团简介:</h2>
        <p>这是一个轻松的文学社团，在这里我们大家都可以获得感悟。</p>
      </div>
      <div>
        <h2 style="">社团组成:</h2>
        <h3>
          管理:
          <a-avatar-group
            :max-count="10"
            size="large"
            :max-style="{
              color: '#f56a00',
            }"
          >
            <a-space>
              <a-tooltip
                v-for="item in admins"
                :key="item.id"
                :title="item.name"
                placement="top"
              >
                <a-avatar
                  src="https://joeschmoe.io/api/v1/random"
                  style="background-color: #87d068"
                >
                </a-avatar>
              </a-tooltip>
              <a-tooltip title="添加管理员" placement="top">
                <a-avatar>+</a-avatar>
              </a-tooltip>
            </a-space>
          </a-avatar-group>
        </h3>
        <h3>
          成员:
          <a-avatar-group
            :max-count="10"
            size="large"
            :max-style="{
              color: '#f56a00',
              backgroundColor: '#fde3cf',
            }"
          >
            <a-space>
              <a-tooltip
                v-for="item in members"
                :key="item.id"
                :title="item.name"
                placement="top"
              >
                <a-avatar
                  src="https://joeschmoe.io/api/v1/random"
                  style="background-color: #87d068"
                >
                </a-avatar> </a-tooltip
            ></a-space>
          </a-avatar-group>
        </h3>
      </div>
      <div>
        <a-tabs v-model:activeKey="activeKey">
          <a-tab-pane key="1" tab="公告">
            <a-list
              class="demo-loadmore-list"
              :loading="initLoading"
              item-layout="horizontal"
              :data-source="anns"
              :pagination="annPagi"
            >
              <template #renderItem="{ item }">
                <a-list-item>
                  <template #actions>
                    <a-button type="primary" @click="openEditAnn(item)">编辑</a-button>
                    <a-button type="danger">删除</a-button>
                  </template>
                  <a-skeleton :title="false" :loading="!!item.loading" active>
                    <a-list-item-meta :description="item.description">
                      <template #title>
                        <a href="https://www.antdv.com/">{{ item.admin }}</a>
                      </template>
                      <template #avatar>
                        <a-avatar>A</a-avatar>
                      </template>
                    </a-list-item-meta>
                  </a-skeleton>
                </a-list-item>
              </template>
            </a-list>
          </a-tab-pane>
          <a-tab-pane key="2" tab="活动" force-render>
            <a-list
              class="demo-loadmore-list"
              :loading="initLoading"
              item-layout="horizontal"
              :pagination="actPagi"
              :data-source="acts"
            >
              <template #renderItem="{ item }">
                <a-list-item>
                  <div>
                    <div>
                      <h2>
                        发布人
                        <a-avatar src="https://joeschmoe.io/api/v1/random" />{{
                          item.admin
                        }}, 标题 《{{ item.name }}》
                      </h2>
                      <div style="display: flex">
                        <a-image
                          style="width: 150px"
                          :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
                        />
                        <p style="margin-left: 1em; text-indent: 2em">
                          与蓝色短发的莉莉丝不同，她留着到胸口长度的金色头发，平时都是散落下来，不过现在是两边扎起来的，应该是为了不沾到多余的灰尘吗？
                          不过那副模样，很像一个贤淑的妻子呢——除了她看起来是个十二三岁的模样以外。
                        </p>
                      </div>
                      <div style="margin: 10px">
                        <a-space>
                          <a-button type="primary">开启</a-button>
                          <a-button type="danger">结束</a-button>
                        </a-space>
                      </div>
                      <h2 style="margin: 10px">参与作品</h2>
                      <div>
                        <a-list
                          :grid="{
                            gutter: 16,
                            xs: 1,
                            sm: 2,
                            md: 4,
                            lg: 4,
                            xl: 6,
                            xxl: 3,
                            xxxl: 2,
                          }"
                          :data-source="item.books"
                        >
                          <template #renderItem="{ item }">
                            <a-list-item>
                              <a-card>
                                <a-image
                                  src="https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
                                />
                              </a-card>
                              {{item.name}}
                            </a-list-item>
                          </template>
                        </a-list>
                      </div>
                    </div>
                  </div>
                </a-list-item>
              </template>
            </a-list>
          </a-tab-pane>
        </a-tabs>
      </div>
      <a-button type="primary" @click="openEditSocialInfo"
        >Open Edit Act Modal</a-button
      >
      <a-modal
        v-model:visible="editAnnVisible"
        title="修改公告"
        ok-text="确认"
        cancel-text="取消"
        @ok="hideModal"
      >
        <a-form :label-col="labelCol" :label-wrap="wrapperCol" :model="editAnn">
          <a-form-item label="标题">
            <a-input v-model:value="editAnn.title" placeholder="输入标题" />
          </a-form-item>
          <a-form-item label="简介">
            <a-textarea
              v-model:value="editAnn.description"
              :rows="8"
            ></a-textarea>
          </a-form-item>
        </a-form>
      </a-modal>
      <a-modal
        v-model:visible="editSocialInfoVisi"
        title="修改社团资料"
        ok-text="确认"
        cancel-text="取消"
        @ok="hideModal"
      >
        <a-form :label-col="labelCol" :label-wrap="wrapperCol" :model="editSocialInfo">
          <a-form-item label="名称">
            <a-input v-model:value="editSocialInfo.name" placeholder="输入名称" />
          </a-form-item>
          <a-form-item label="简介">
            <a-textarea
              v-model:value="editSocialInfo.description"
              :rows="8"
            ></a-textarea>
          </a-form-item>
          <a-form-item label="社团图片">
            <a-upload
              v-model:value="newAct.image"
              action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
              list-type="picture-card"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                upload
              </a-button>
            </a-upload>
          </a-form-item>
          <a-form-item label="代表活动">
            <a-select v-model:value="editSocialInfo.present" :options="presentAct">
            </a-select>
          </a-form-item>
        </a-form>
      </a-modal>
      
    </main>
    <aside>
      <div>
        <h2>请求</h2>
        <a-list
          class="demo-loadmore-list"
          :loading="initLoading"
          item-layout="horizontal"
          :pagination="requestPagi"
          :data-source="requests"
        >
          <template #renderItem="{ item }">
            <a-list-item>
              <template #actions>
                <a-button type="primary">接受</a-button>
                <a-button type="primary">拒绝</a-button>
              </template>
              <a-skeleton
                avatar
                :title="false"
                :loading="!!item.loading"
                active
              >
                <a-list-item-meta :description="item.message">
                  <template #title>
                    <a href="https://www.antdv.com/">{{ item.author }}</a>
                  </template>
                  <template #avatar>
                    <a-avatar>A</a-avatar>
                  </template>
                </a-list-item-meta>
              </a-skeleton>
            </a-list-item>
          </template>
        </a-list>
      </div>
      <div class="newAct">
        <h2>新活动</h2>
        <a-form :label-col="labelCol" :label-wrap="wrapperCol" :model="newAct">
          <a-form-item label="标题">
            <a-input v-model:value="newAct.title" placeholder="输入标题" />
          </a-form-item>
          <a-form-item label="开始-结束日期">
            <a-range-picker v-model:value="newAct.range" />
          </a-form-item>
          <a-form-item label="自动开始">
            <a-switch v-model:checked="newAct.autoStart" />
          </a-form-item>
          <a-form-item label="简介图片">
            <a-upload
              v-model:value="newAct.image"
              action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
              list-type="picture-card"
            >
              <a-button>
                <upload-outlined></upload-outlined>
                upload
              </a-button>
            </a-upload>
          </a-form-item>
          <a-form-item label="简介">
            <a-textarea :rows="10"></a-textarea>
          </a-form-item>
          <a-form-item :wrapper-col="{ span: 14, offset: 10 }">
            <a-button type="primary">发布</a-button>
          </a-form-item>
        </a-form>
      </div>
      <div class="newAnn">
        <h2>新公告</h2>
        <a-form :label-col="labelCol" :label-wrap="wrapperCol">
          <a-form-item label="标题">
            <a-input v-model:value="newAnn.title" placeholder="输入标题" />
          </a-form-item>
          <a-form-item label="内容">
            <a-textarea :rows="10"></a-textarea>
          </a-form-item>
          <a-form-item :wrapper-col="{ span: 14, offset: 10 }">
            <a-button type="primary">发布</a-button>
          </a-form-item>
        </a-form>
      </div>
    </aside>
  </div>
</template>

<script setup>
import { UserOutlined } from "@ant-design/icons-vue";
import { ref } from "@vue/reactivity";

const labelCol = { span: 8 };
const wrapperCol = { span: 10 };

const admins = [
  { id: "101", name: "汄汄" },
  { id: "101", name: "仄仄" },
  { id: "101", name: "瑾安" },
];

const members = [
  { id: "101", name: "秋月" },
  { id: "101", name: "流浪" },
  { id: "101", name: "枫华夜" },
  { id: "101", name: "包子蜜茶" },
];

const socialInfo=ref({
  id:"101",
  name:"玄沧阁",
  master:"夏文纯一",
  present:"2020文集-逆转季节",
});

const presentAct=[
  {key:'1',value:"2018文集"},
  {key:'2',value:"2020文集"},
  {key:'3',value:"2021文集"},
  {key:'4',value:"2022文集"},
];

const anns = [
  { admin: "夏文纯一", title: "关于2022文集", description: "关于2022文集..." },
  { admin: "夏文纯一", title: "关于2022文集", description: "关于2022文集..." },
  { admin: "夏文纯一", title: "关于2022文集", description: "关于2022文集..." },
  { admin: "夏文纯一", title: "关于2022文集", description: "关于2022文集..." },
  { admin: "夏文纯一", title: "关于2022文集", description: "关于2022文集..." },
];

const annPagi = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 20,
};

const actPagi = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 20,
};

const requestPagi = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 20,
};

const acts = [];
for (let i = 0; i != 10; ++i) {
  let act = {
    id: "101",
    name: "2022文集",
    admin: "夏文纯一",
    startTime: "2022年5月15日",
    endTime: "2022年9月10日",
    enabled: false,
    books: [],
  };
  for (let j = 0; j != 10; ++j) {
    act.books.push({
      id: "101",
      name: "玄沧阁日记",
      author: "玄沧阁",
    });
  }
  acts.push(act);
}

const requests = [];
for (let i = 0; i != 10; ++i) {
  requests.push({
    id: "101",
    author: "秋月",
    message: "想成为管理员",
  });
}

const newAct = ref({
  title: "",
  range: "",
  autoStart: false,
});

const editAnn = ref({
  id: "101",
  title: "",
  start: false,
  description:""
});

const editSocialInfo=ref({
  id:"101",
  name:"玄沧阁",
  master:"夏文纯一",
  present:"2020文集-逆转季节"
})

const editAnnVisible = ref(false);
const editSocialInfoVisi=ref(false);
function openEditAnn(item){
  editAnn.value=item;
  editAnnVisible.value=true;
}
function openEditSocialInfo(){
  editSocialInfo.value=socialInfo.value;
  editSocialInfoVisi.value=true;
}

const newAnn = ref({
  title: "",
  content: "",
});
</script>

<style scoped>
.socialstation {
  display: flex;
  width: 80%;
  margin: auto;
}

.socialstation main {
  width: 60%;
  margin-right: 5%;
}

.socialstation aside {
  width: 35%;
}

.newAct {
  border: 2px groove;
  border-radius: 10px 10px;
  padding: 10px;
  margin: 10px;
}

.newAnn {
  border: 2px groove;
  border-radius: 15px 15px;
  padding: 10px;
  margin: 10px;
}
</style>