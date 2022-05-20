<template>
  <div class="register-form">
    <h2>用户注册</h2>
    <a-form
      :model="registerData"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
      @finish="register"
    >
      <a-form-item
        label="用户名"
        name="userName"
        :rules="[{ required: true, message: '请输入用户名!' }]"
      >
        <a-input
          v-model:value="registerData.userName"
          placeholder="输入用户名"
        />
      </a-form-item>
      <a-form-item
        label="密码"
        name="password"
        :rules="[{ required: true, message: '请输入密码!' }]"
      >
        <a-input-password v-model:value="registerData.password" />
      </a-form-item>
      <a-form-item
        label="邮箱"
        name="email"
        :rules="[{ required: true, message: '请输入邮箱!' }]"
      >
        <a-input
          v-model:value="registerData.email"
          placeholder="albert@example.com"
        />
        <a-button type="primary" @click="sendCaptcha">发送验证码</a-button>
      </a-form-item>
      <a-form-item
        label="验证码"
        name="captcha"
        :rules="[{ required: true, message: '请输入发送到邮箱里的验证码!' }]"
      >
        <a-input
          v-model:value="registerData.captcha"
          placeholder="输入验证码"
        />
      </a-form-item>
      <a-form-item :wrapper-col="{ offset: 10, span: 20 }">
        <a-space>
          <a-button type="primary" html-type="submit">注册</a-button>
          <a-button type="danger" @click="gotoLogin">去登录</a-button>
        </a-space>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup>
import { reactive } from "@vue/reactivity";
import { useRouter } from "vue-router";
import { sendCaptchaPort, registerPort } from "../../ports/user.js";

const labelCol = { span: 7 };
const wrapperCol = { span: 14 };
const registerData = reactive({
  userName: "",
  email: "",
  captcha: "",
  password: "",
});

const router = useRouter();

function register(data) {
  registerPort(registerData, (data) => {
    alert("注册成功，用户ID:" + data.userId);
      gotoLogin();
  });
}

function gotoLogin() {
  router.push({ name: "login" });
}

function sendCaptcha() {
  sendCaptchaPort(registerData.email, (d) => {
    alert("验证码发送成功!");
  });
}
</script>

<style scoped>
.register-form {
  width: 30%;
  float: right;
  margin-right: 5%;
  border: grey;
  border-style: groove;
  border-radius: 10px 10px;
}
</style>