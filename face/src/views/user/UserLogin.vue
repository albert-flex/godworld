<template>
  <div class="login-form">
    <h2>用户登录</h2>
    <a-form :model="loginData" :label-col="labelCol" :wrapper-col="wrapperCol" @finish="login">
      <a-form-item label="用户名" required>
        <a-input v-model:value="loginData.userName" placeholder="输入用户名" />
      </a-form-item>
      <a-form-item label="密码" required>
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
import { reactive } from "@vue/reactivity";
import { useRouter } from "vue-router";
import { loadAccess, loadUser } from "../../config/stores.js";
import { loginPort, userinfoPort } from "../../ports/user.js";

const labelCol = { span: 7 };
const wrapperCol = { span: 14 };
const loginData = reactive({
  userName: "",
  password: "",
});

const router = useRouter();

function gotoRegister() {
  router.push({ name: "register" });
}

function login(data) {
  const access = loadAccess();
  const info = loadUser();
  loginPort(loginData.userName, loginData.password, (da) => {
    access.set(da.access_token);
    userinfoPort(access.access_token, (d) => {
      info.set(d);
      alert("登录成功！用户Id:" + info.userId);
    });
  });
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