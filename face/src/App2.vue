<template>
  <a-layout>
    <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
      <div class="logo" />
      <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px', width: '100%' }"
        @click="selectMenu"
      >
        <a-menu-item key="1">书库</a-menu-item>
        <a-menu-item key="2">作者</a-menu-item>
        <a-menu-item key="3">社团</a-menu-item>
        <a-menu-item key="4">工作台</a-menu-item>
        <a-menu-item key="5">个人主页</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout-content :style="{ padding: '0px 0px', marginTop: '64px' }">
      <div :style="{ background: '#fff', padding: '10px', minHeight: '380px' }">
        <router-view v-slot="{ Component }" :key="route.fullPath">
          <transition>
            <component :is="Component" />
          </transition>
        </router-view>
      </div>
    </a-layout-content>
    <a-layout-footer :style="{ textAlign: 'center' }">
      ©2022 Albert Flex
    </a-layout-footer>
  </a-layout>
</template>
<script setup>
import { ref } from "vue";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const route = useRoute();
const selectedKeys = ref(["2"]);
function selectMenu(e) {
  if (e.key == "1") {
    router.push({ name: "bookHome" });
  } else if (e.key == "2") {
    router.push({ name: "authorHome" });
  } else if (e.key == "3") {
    router.push({ name: "socialHome" });
  } else if (e.key == "4") {
    router.push({ name: "authorStation" });
  } else {
    router.push({ name: "login" });
  }
}
</script>
<style>
#components-layout-demo-fixed .logo {
  width: 120px;
  height: 31px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px 24px 16px 0;
  float: left;
}
</style>
