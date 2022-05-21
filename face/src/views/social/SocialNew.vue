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
      <a-form-item label="社团名字">
        <a-input
          v-model:value="formData.userName"
          placeholder="名字"
          allow-clear
        >
          <template #prefix>
            <user-outlined type="user" />
          </template>
        </a-input>
      </a-form-item>
      <a-form-item label="验证">
        <a-button type="primary" @click="sendCaptcha">发送验证码</a-button>
      </a-form-item>
      <a-form-item label="验证码">
        <a-input v-model:value="formData.captcha" allow-clear></a-input>
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 10 }">
        <a-button type="primary" htmlType="submit">注册</a-button>
      </a-form-item>
    </a-form>
  </main>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { UserOutlined, InfoCircleOutlined } from "@ant-design/icons-vue";
import { RegitserSocial } from "../../ports/social.js";
import { sendCaptchaPort } from "../../ports/user.js";
import { loadAccess, loadUser } from "../../config/stores.js";

const labelCol = { style: { width: "150px" } };
const wrapperCol = { span: 10 };
const formData = ref({
  masterId: "",
  name: "",
  captcha: "",
});

function sendCaptcha() {
  const access = loadAccess();
  if (!access.logined()) {
    alert("未登录,请登录!");
    return;
  }

  const user = loadUser();
  sendCaptchaPort(user.email, (data) => {
    alert("验证码已发送!");
  });
}

function Register() {
  const access = loadAccess();
  if (!access.logined()) {
    alert("未登录,请登录!");
    return;
  }

  const user = loadUser();
  if (user.authorId == null) {
    alert("不是作者，不能常见社团");
    return;
  }

  formData.value.masterId = user.authorId;
  RegitserSocial(access.access_token,formData.value,(data)=>{
    if(!data.success){
        alert(data.error);
    }else{
      alert("创建成功!");
    }
  });
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