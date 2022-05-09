<template>
  <a-layout style="padding: 24px 0; background: #fff">
    <a-layout-sider width="300px" style="background: #fff">
      <h2 style="margin-left: 50px; padding-right: 40px">
        排行榜
        <a-button type="primary" style="background-color: grey; float: right"
          >更多</a-button
        >
      </h2>
      <ul class="bound">
        <li class="bound-list">
          <book-preview
            v-for="item in hotList"
            :key="item.title"
            :bookInfo="item"
          >
          </book-preview>
        </li>
      </ul>
    </a-layout-sider>
    <a-layout-content width="80%">
      <h2 class="bound" style="padding-right: 40px">
        最近更新
        <a-button type="primary" style="background-color: grey; float: right"
          >更多</a-button
        >
      </h2>
      <book-preview
        v-for="item in newest_num"
        :key="item.id"
        :bookInfo="newest"
      />
    </a-layout-content>
  </a-layout>
</template>

<script setup>
import BookPreview from "../book/BookPreview.vue";
const hotList = [
  { id: "1", title: "世界在我之上", board: "恋爱", update: "第三章" },
  { id: "2", title: "主人是鬼", board: "恋爱", update: "第三卷" },
  { id: "3", title: "梦想的世界", board: "校园", update: "第三章" },
  { id: "4", title: "我没有性癖", board: "搞笑", update: "第六卷第三章" },
  { id: "5", title: "吃饱了撑的", board: "古典", update: "第二章" },
];
const newest = {
  title: "吃饱了撑的",
  board: "古典",
  update: "第二章",
};
const newest_num = [
  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
];
</script>