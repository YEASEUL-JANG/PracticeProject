<template>
<div class="container">
    <div class="box">
    1. 자식컴포넌트에서 hobby를 input text박스에 v-model을 이용해서 입력하고,
    submit 버튼으로 부모컴포넌트에 form을 제출하면 부모컴포넌트의 hobbys 배열에서
    해당 hobby값을 받아 v-for로 출력하기 
  </div>
<form @submit.prevent="onsubmit">
  <input type="text" v-model="hobby">
  <button type="submit">submit</button>
  </form>
  </div>
</template>

<script>
import {ref} from 'vue';
export default {
    name: "test2_component",
    emits:['add-hobby'],
    setup(props,{emit}) {
        const hobby = ref('');
        const onsubmit = () => {
            emit('add-hobby',hobby.value);
            hobby.value='';
        }
        return {
            hobby,onsubmit,
        }
    }
}
</script>

<style>

</style>