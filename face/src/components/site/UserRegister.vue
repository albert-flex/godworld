<template>
  <a-layout style="padding: auto; width: 30%">
    <a-layout-content>
      <a-form
        v-bind="layout"
        :model="formData"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
      >
        <a-form-item
          label="用户名"
          name="userName"
          :rules="[{ required: true, message: '请输入用户名!' }]"
        >
          <a-input v-model:value="formData.userName" />
        </a-form-item>
        <a-form-item
          name="userEmail"
          label="邮  箱"
          :rules="[
            { type: 'email', required: 'true', message: '请输入邮箱地址' },
          ]"
        >
          <a-input v-model:value="formData.email" />
        </a-form-item>
        <a-button
          type="primary"
          style="margin: 20px; margin-top: 0px"
          @click="sendCaptcha"
          >获取验证码</a-button
        >
        <a-form-item
          name="Email Captcha"
          label="验证码"
          :rules="[{ required: 'true', message: '请输入邮箱验证码' }]"
        >
          <a-input v-model:value="formData.captcha" />
        </a-form-item>
        <a-form-item
          label="密码"
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
            @click="register"
          >
            注册
          </a-button>
          <a-button type="danger" @click="toLogin"> 登录 </a-button>
        </a-form-item>
      </a-form>
    </a-layout-content>
  </a-layout>
</template>

<script setup>
import { reactive } from "@vue/reactivity";
import { useRouter } from "vue-router";
import { sendCaptchaAPI, registerAPI } from "../../api/user.js";

const layout = {
  labelCol: {
    span: 8,
  },
  wrapperCol: {
    span: 16,
  },
};

const router = useRouter();

const formData = reactive({
  userName: "",
  password: "",
  email: "",
  captcha: "",
});

function toLogin() {
  router.push({ name: "login" });
}

function sendCaptcha() {
  if (formData.email == null || formData.email == "") {
    return;
  }
  sendCaptchaAPI(formData.email, (data) => {
    alert("已发送验证码到邮箱：" + formData.email);
  });
}

function register() {
  registerAPI(formData, (data) => {
    if (data.success) {
      console.log(data.obj.userId);
    } else {
      alert(data.error);
    }
  });
}
</script>