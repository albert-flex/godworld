<template>
  <main>
    <a-form :label-col="labelCol" :label-wrap="wrapperCol" :model="formData" class="newAuthor">
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
        <a-button type="primary">发送验证码</a-button>
      </a-form-item>
      <a-form-item label="验证码">
        <a-input v-model:value="formData.captcha" allow-clear></a-input>
      </a-form-item>
      <a-form-item label="密码">
        <a-input-password
          v-model:value="formData.password"
          placeholder="input password"
        />
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 10 }">
        <a-button type="primary">注册</a-button>
      </a-form-item>
    </a-form>
  </main>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import { UserOutlined, InfoCircleOutlined } from "@ant-design/icons-vue";

const labelCol = { style: { width: "150px" } };
const wrapperCol = { span: 10 };
const formData = ref({
  userName: "",
});
</script>

<style scoped>
main {
  width: 30%;
  height: 600px;
  margin: auto;
  margin-right: 50px;
}

.newAuthor{
  padding: 10px;
  padding-right: 20px;
  border-radius: 10px;
  border-style: groove;
}
</style>