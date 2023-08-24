<template>
<div class="container">
  <test-2_component @add-hobby="addHobby"/>
  제 취미는 
  <div v-for="(hobby,index) in hobbys" :key="index">
    {{hobby}},
  </div> 입니다!
  </div>
</template>

<script>
import {ref} from 'vue';
import test2_component from './test2_component.vue'
export default {
  components: { test2_component },
  setup(props,{emit}){
    const hobbys = ref([]);
    const addHobby = (hobby) => {
        hobbys.value.push(hobby);
    }
    return{
        addHobby,hobbys

    }
  }
}
</script>

<style>

</style>