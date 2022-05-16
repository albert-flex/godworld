<template>
  <div class="login-form">
    <h2>用户登录</h2>
    <a-form :model="loginData" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-item label="用户名">
        <a-input v-model:value="loginData.userName" placeholder="输入用户名" />
      </a-form-item>
      <a-form-item label="密码">
        <a-input-password v-model:value="loginData.password" />
      </a-form-item>
      <a-form-item :wrapper-col="{ offset: 10, span: 20 }">
        <a-space>
          <a-button type="primary" html-type="submit">登录</a-button>
          <a-button type="danger" @click="gotoRegister">去注册</a-button>
        </a-space>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import {useRouter} from "vue-router";

const labelCol = { span: 7 };
const wrapperCol = { span: 14 };
const loginData = ref({
  userName: "",
  password: "",
});

const router=useRouter();

function gotoRegister() {
  router.push({ name: "register" });
}

function login() {
  alert("登录");
}
</script>

<style scoped>
.login-form {
  width: 30%;
  float: right;
  margin-right: 5%;
  border: grey;
  border-style: groove;
  border-radius: 10px 10px;
}
</style>