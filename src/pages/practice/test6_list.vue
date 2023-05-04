<template>
 <Test6_slot :items="todolist">
 <template #default="{item}">
   <div>{{ item.subject }}</div>
 </template>
 </Test6_slot>
</template>
<script>
import axios from "@/axios";
import {ref} from "vue";
import Test6_slot from "@/pages/practice/test6_slot.vue";

export default {
 components: {Test6_slot},
    setup(){
     const todolist = ref([]);
     const getList = async ()=> {
      const res = await axios.get('todolist');
      todolist.value= res.data;
     }
     getList();


     return {
      todolist,
     }
    }
}

</script>
<style>

</style>