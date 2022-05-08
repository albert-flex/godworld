<template>
  <a-layout>
    <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
      <div style="float: left; color: white">GOD WORLD</div>
      <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
        class="menu-bar"
      >
        <a-menu-item key="1">书库</a-menu-item>
        <a-menu-item key="2">书库</a-menu-item>
        <a-menu-item key="3">社团库</a-menu-item>
        <a-menu-item key="4">作者库</a-menu-item>
        <a-menu-item key="5">作者工作台</a-menu-item>
        <a-menu-item key="6">社团工作台</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
      <a-breadcrumb :style="{ margin: '16px 0' }">
        <a-breadcrumb-item>官网</a-breadcrumb-item>
        <a-breadcrumb-item>书库</a-breadcrumb-item>
        <a-breadcrumb-item>主页</a-breadcrumb-item>
      </a-breadcrumb>
      <router-view></router-view>
    </a-layout-content>
    <a-layout-footer :style="{ textAlign: 'center' }">
      Albert Flex ©2022 Copyright
    </a-layout-footer>
  </a-layout>
</template>
<script setup>
import { ref } from "vue";
const selectedKeys=ref(['2']);
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

.menu-bar {
  float: right;
}

[data-theme="dark"] .site-layout .site-layout-background {
  background: #141414;
}

.bound {
  font-size: 1.3em;
  list-style: none;
}

.bound li {
  align-items: center;
}
</style>