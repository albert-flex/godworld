<template>
  <div class="authorStation">
    <main>
      <div style="display: flex">
        <a-image
          :width="150"
          :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
          fallback="data:image/png;base64,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"
        />
        <div>
          <h2>作者: 夏文纯一</h2>
          <h2>创建时间: 2022年5月21日</h2>
          <h2>代表作: 《谋杀者Z》</h2>
        </div>
      </div>
      <div>
        <h2>签名:</h2>
        <p>这就是人生...</p>
      </div>
      <div>
        <h2>作品</h2>
        <a-list item-layout="vertical" :data-source="books">
          <template #renderItem="{ item }">
            <a-list-item key="item.id">
              <template #extra>
                <img
                  width="150"
                  alt="logo"
                  src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
                />
              </template>
              <a-list-item-meta
                :description="
                  item.updateTime +
                  ' ' +
                  item.updateVolume +
                  '-' +
                  item.updateChapter
                "
              >
                <template #title>
                  <a-button>{{ item.name }}</a-button>
                </template>
              </a-list-item-meta>
            </a-list-item>
          </template>
        </a-list>
      </div>
    </main>
    <aside>
      <a-divider><h2>作品详情</h2></a-divider>
      <a-empty>
        <template #description>
          <span> 未选择作品 </span>
        </template>
      </a-empty>
      <a-divider><h2>火之异能者</h2></a-divider>
      <a-form :model="formData" :label-col="labelCol" :wrapper-col="wrapperCol">
        <a-form-item label="标签">
          <a-tag color="red" closable @close.prevent>战斗</a-tag>
          <a-tag color="red" closable @close.prevent>冒险</a-tag>
          <a-tag color="red" closable @close.prevent>末世</a-tag>
          <a-tag color="red" closable @close.prevent>恋爱</a-tag>
        </a-form-item>
        <a-form-item label="简介">
          <a-textarea
            v-model:value="value"
            placeholder="Basic usage"
            :rows="4"
          />
        </a-form-item>
        <a-form-item label="设为代表作">
          <a-switch
            v-model:checked="checked1"
            checked-children="开"
            un-checked-children="关"
          />
        </a-form-item>
      </a-form>
      <a-divider>目录</a-divider>
      <div>
        <a-button type="ghost">+新增卷目</a-button>
        <a-button type="ghost">R删除卷目</a-button>
        <a-button type="ghost">E修改卷目</a-button>
      </div>
      <a-collapse v-model:activeKey="activeKey" :bordered="false">
        <a-collapse-panel
          :header="item.name"
          v-for="item in catalog"
          :key="item.id"
        >
          <a-button type="ghost" v-for="it in item.chapters" :key="it.id">
            {{ it.name }}
          </a-button>
          <a-button type="danger">+新增 章节</a-button>
        </a-collapse-panel>
      </a-collapse>
    </aside>
    <a-button type="primary" @click="showDrawer">Open</a-button>

    <a-drawer
      v-model:visible="editVolumeVisi"
      class="custom-class"
      style="color: red"
      title="修改卷目"
      placement="right"
      @after-visible-change="afterVisibleChange"
    >
      <a-form
        :model="editVolume"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-item label="卷ID">
          <a-input v-model:value="editVolume.id" placeholder="名字" />
        </a-form-item>
        <a-form-item label="卷名">
          <a-input v-model:value="editVolume.name" placeholder="名字" />
        </a-form-item>
      </a-form>
    </a-drawer>

    <a-drawer
      v-model:visible="newChapterVisi"
      class="custom-class"
      style="color: red"
      title="新建章节"
      placement="right"
      @after-visible-change="afterVisibleChange"
    >
      <a-form
        :model="editVolume"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-item label="标题">
            <a-input v-model:value="editVolume.id" placeholder="名字" />
        </a-form-item>
      </a-form>
    </a-drawer>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";

const editVolumeVisi = ref(false);
function showDrawer() {
  editVolumeVisi.value = true;
}
const newChapterVisi = ref(false);
function showDrawerChapter() {
  newChapterVisi.value = true;
}

const editVolume = ref({
  id: "101",
  name: "第一卷",
});

const newContent=ref('');

const newChapter=ref({
  volumeId:"101",
  title:"",
  content:[]
});

function splitContent(){
  let words=[];
  for(let cn/ )

}n/ 
b2
}n/ b2
const books = [];
consb2t books = [];
const pagi = {};
consb2t pagi = {};
for (let i = 0; i != 10; ++i) {
  books.push({
    id: "101",
    name: "火之异能者",
    updateTime: i + "小时前",
    updateChapter: "第七章",
    updateVolume: "第一卷",
    description: `中原区望洋县研究所出现了暴乱，主角夏宇轩和秋淼汐带上刘杰戈很快掌握了信息，
        夏宇轩和秋淼汐获得了异能的力量，并且一连三次突破重围找到了暂时可以适合居住的敌方，
        半途遇到程辰和林残月...`,
  });
}

const labelCol = { style: { width: "150px" } };
const wrapperCol = { span: 20 };

const labelCol2 = { style: { width: "100px" } };
const wrapperCol2 = { span: 14 };

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
</script>

<style scoped>
.authorStation {
  display: flex;
  width: 80%;
  margin: auto;
}

.authorStation main {
  width: 50%;
  margin-right: 10%;
}

.authorStation aside {
  width: 40%;
}
</style>