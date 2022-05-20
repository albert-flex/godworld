<template>
  <main>
    <a-form
      :label-col="labelCol"
      :label-wrap="wrapperCol"
      :model="formData"
      class="newAuthor"
      @finish="Register"
    >
      <h2 style="text-align: center">注册作者信息</h2>
      <a-form-item label="笔名">
        <a-input
          v-model:value="formData.userName"
          placeholder="笔名"
          allow-clear
        >
          <template #prefix>
            <user-outlined type="user" />
          </template>
        </a-input>
      </a-form-item>
      <a-form-item label="邮箱">
        <a-input v-model:value="formData.email" allow-clear> </a-input>
        <a-button type="primary" @click="sendCaptcha">发送验证码</a-button>
      </a-form-item>
      <a-form-item label="验证码">
        <a-input v-model:value="formData.captcha" allow-clear></a-input>
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 10 }">
        <a-button htmlType="submit" type="primary">注册</a-button>
      </a-form-item>
    </a-form>
  </main>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { UserOutlined, InfoCircleOutlined } from "@ant-design/icons-vue";
import { RegisterAuthor } from "../../ports/author.js";
import { sendCaptchaPort } from "../../ports/user.js";
import { loadAccess } from "../../config/stores.js";
import { useRouter } from "vue-router";
const labelCol = { style: { width: "150px" } };
const wrapperCol = { span: 10 };
const formData = ref({
  name: "",
  email: "",
  captcha: "",
});
const router = useRouter();

function Register() {
  const access = loadAccess();
  if (!access.logined()) {
    alert("未登录，将前往登录页");
    router.push({ name: "login" });
  }
  RegisterAuthor(access.access_token,formData.value,(data)=>{
    if(!data.success){
      alert(data.error);
    }else{
      alert("注册作者成功!");
    }
  });
}

function sendCaptcha(){
  sendCaptchaPort(formData.value.email,(d)=>{
    alert("验证码发送成功!");
  })
}
</script>

<style scoped>
main {
  width: 30%;
  height: 600px;
  margin: auto;
  margin-right: 50px;
}

.newAuthor {
  padding: 10px;
  padding-right: 20px;
  border-radius: 10px;
  border-style: groove;
}
</style>