<template>
  <a-layout style="padding: auto;" class="background">
    <a-layout-content style="align-self: flex-end;">
      <a-form
        :model="formData"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="login"
        class="form-body"
      >
        <h1>登录页</h1>
        <a-form-item
          label="用户名"
          name="userName"
          :rules="[{ required: true, message: '请输入用户名!' }]"
        >
          <a-input v-model:value="formData.userName" />
        </a-form-item>
        <a-form-item
          label="密 码"
          name="password"
          :rules="[{ required: true, message: '请输入密码' }]"
        >
          <a-input-password v-model:value="formData.password" />
        </a-form-item>
        <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
          <a-button
            type="primary"
            html-type="submit"
            style="margin-right: 10px"
          >
            登录
          </a-button>
          <a-button type="danger" @click="toRegister"> 注册 </a-button>
        </a-form-item>
      </a-form>
    </a-layout-content>
  </a-layout>
</template>

<script setup>
import { reactive } from "@vue/runtime-core";
import { loginAPI, userInfoAPI } from "../../api/user.js";
import { loginData, userData } from "../../state/user";
import { useRouter } from "vue-router";

const loginInfo = loginData();

const formData = reactive({
  userName: "",
  password: "",
});

const router = useRouter();

function toRegister(){
  router.push({name:"register"});
}

function login() {
  if (formData.userName != "" && formData.password != "") {
    loginAPI(formData.userName, formData.password, (data) => {
      const userD = userData();
      loginInfo.set(data.access_token);
      userInfoAPI((data2) => {
        userD.refresh(data2);
        console.log(userD.userName);
        router.push({name: "site"});
      });
    });
  }
}
</script>

<style scoped>
.form-body{
  width: 400px;
  height: 400px;
  padding: 20px;
  padding-left: auto;
  padding-right: auto;
  margin: 50px;
  border:2px solid black;
  border-radius: 10px 10px;
  background-color: #dddd;
}

.background{
  margin-top: 40px;
  background-image: url(./back2.png);
  background-size: cover;
}
</style>