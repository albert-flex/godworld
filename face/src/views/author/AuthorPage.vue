<template>
  <div class="authorpage">
    <main>
      <h2>{{ author.name }}</h2>
      <div style="display: flex">
        <a-image
          :width="200"
          :height="200"
          :src="'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png?${random}'"
          fallback="data:image/png;base64,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"
        />
        <div class="info">
          <h2>代表作: 《{{ author.present }}》</h2>
          <h2>加入日期: {{ author.createTime }}</h2>
          <h2>更新小说: 《{{ author.updateBook }}》</h2>
          <h2>更新小说: {{ author.updateTime }}</h2>
          <h2>
            更新章节: {{ author.updateVolume }} {{ author.updateChapter }}
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
                  <a :href="item.href">{{ item.title }}</a>
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
      <h2>所属社团: 玄沧阁</h2>
      <h2>参加的活动:</h2>
      <a-list
        item-layout="vertical"
        size="normal"
        :pagination="pagination2"
        :data-source="activityBooks"
      >
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
                <a-list-item-meta
                  :description="item.updateTime + ' ' + item.updateChapter"
                >
                  <template #title>
                    <a :href="item.href">{{ item.title }}</a>
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

const listData = [];

for (let i = 0; i < 10; i++) {
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
  pageSize: 20,
};

const author = ref({
  name: "夏文纯一",
  present: "谋杀者Z",
  createTime: "2022/05/13",
  updateBook: "火之异能者",
  updateTime: "2022/05/13 21:21",
  updateChapter: "第一卷 第七章",
  ownSocial: "玄沧阁",
  moto: `
  在与魔王决战（卡尔特思魔灵国灭龙湖附近）的薪火佣兵团最终还是战败了，
  作为唯一的幸存者迪克， 被作为罪人押向王宫，失去了金钱、装备、名号乃至志气的他，
  被流放到了边缘的城镇德拉普。 原本以为对生活绝望的他，
  在这里看到了拼命活着的亚人和兽人之后，终于也有了一点对生的希望。
  `,
});

const hasBooks = [];
for (let i = 0; i != 10; ++i) {
  hasBooks.push({
    title: "火之异能者",
    updateTime: "2022/05/13 12:30",
    updateChapter: "第三章",
    updateVolume: "第一卷",
    tags: ["动作", "异能", "冒险", "末日", "组队"],
  });
}

const activityBooks = [];
for (let i = 0; i != 5; ++i) {
  let books = [];
  for (let j = 0; j != 5; ++j) {
    books.push({
      id: "101",
      title: "火之异能者",
      updateTime: "2022/05/13 12:30",
      updateChapter: "第三章",
      updateVolume: "第一卷",
      tags: ["动作", "异能", "冒险", "末日", "组队"],
    });
  }
  activityBooks.push({
    name: "活动" + i,
    books: books,
  });
}

const pagination2 = {
  onChange: (page) => {
    console.log(page);
  },
  pageSize: 10,
};
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
