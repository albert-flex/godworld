<template>
  <a-layout style="padding: auto; width: 30%">
    <a-layout-content>
      <a-form
        :model="formData"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        @finish="login"
      >
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
import { useRouter } from "vue-router";
import { loginAPI } from "../../api/user.js";

const router = useRouter();

const formData = reactive({
  userName: "",
  password: "",
});

function login() {
  if(formData.userName!=''&&formData.password!=''){
    loginAPI(formData.userName,formData.password,(data)=>{
      console.log(data.userName);
    });
  }

  alert("登录:" + formData.userName + " / " + formData.password);
}

function toRegister() {
  router.push({ name: "register" });
}
</script>

<style scoped>
</style>