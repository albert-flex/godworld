<template>
  <div class="box">
    <p>{{ bookInfo.title }} - {{ bookInfo.update }}</p>
  </div>
</template>

<script setup>
defineProps({
  bookInfo: {
    title: String,
    update: String,
    board: String,
  },
});
</script>

<style scoped>
.box {
  background-image: url(./book-default.jpg);
  background-size: cover;
  width: 200px;
  height: 250px;
  margin-bottom: 10px;
  margin-right: 10px;
  margin-left: 0px;
  margin-top: 0px;
  display: inline-block;
  text-align: center;
}

.box:hover {
  box-shadow: 5px 5px 5px 0px;
}
</style>