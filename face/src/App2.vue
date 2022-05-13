<template>
  <a-layout>
    <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
      <div class="logo" />
      <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px'}"
      >
        <a-menu-item key="1">书库</a-menu-item>
        <a-menu-item key="2">作者</a-menu-item>
        <a-menu-item key="3">社团</a-menu-item>
        <a-menu-item key="4">工作台</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout-content :style="{ padding: '0px 0px', marginTop: '64px' }">
      <div :style="{ background: '#fff', padding: '10px', minHeight: '380px' }">
        <router-view />
      </div>
    </a-layout-content>
    <a-layout-footer :style="{ textAlign: 'center' }">
      ©2022 Albert Flex
    </a-layout-footer>
  </a-layout>
</template>
<script setup>
import { ref } from 'vue';
const selectedKeys= ref(['2']);
</script>
<style>
#components-layout-demo-fixed .logo {
  width: 120px;
  height: 31px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px 24px 16px 0;
  float: left;
}
.site-layout .site-layout-background {
  background: #fff;
}

[data-theme="dark"] .site-layout .site-layout-background {
  background: #141414;
}
</style>
