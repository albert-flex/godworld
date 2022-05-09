<template>
  <a-layout>
    <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
      <div style="float: left; color: white; cursor:pointer;" @click="toSiteHome">GOD WORLD</div>
      <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
        class="menu-bar"
      >
        <a-menu-item
          key="1"
          @click="loginInfo.isLogined() ? toHome() : toLogin()"
          >{{ loginInfo.isLogined() ? "个人主页" : "登录" }}</a-menu-item
        >
        <a-menu-item key="2">书库</a-menu-item>
        <a-menu-item key="3">社团库</a-menu-item>
        <a-menu-item key="4">作者库</a-menu-item>
        <a-menu-item key="5">作者工作台</a-menu-item>
        <a-menu-item key="6">社团工作台</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px' }">
      <router-view></router-view>
    </a-layout-content>
    <a-layout-footer :style="{ textAlign: 'center' }">
      Albert Flex ©2022 Copyright
    </a-layout-footer>
  </a-layout>
</template>
<script setup>
import { ref } from "vue";
import { loginData } from "./state/user";
import { useRouter } from "vue-router";

const selectedKeys = ref(["2"]);
const loginInfo = loginData();
const router = useRouter();

function toLogin() {
  router.push({ name: "login" });
}

function toHome() {
  alert("to Home");
}

function toSiteHome() {
  router.push({ name: "site" });
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