<template>
<div class="container">
  <test2_comp_mine @add-hobby="addHobby" />
  1. 제 취미는 
  <test2_comp2_mine :hobbys=hobbys />
   입니다!
    
    <hr>

   2.(세정님_computed) <br>
    사과의 가격은 <input v-model="price">원 이다.<br>
    세정이는 사과 {{count}}개가 먹고싶다. <button @click="countUp"> click</button> <br>
    세정이 지불해야 할 총 금액은 {{computeCount}}원이다.
    
  </div>
</template>

<script>
import {computed, ref} from 'vue';
import test2_comp_mine from './test2_comp_mine.vue'
import test2_comp2_mine from './test2_comp2_mine.vue'
export default {
  components: { test2_comp_mine, test2_comp2_mine },
  emit:['addHobby'],
  setup(props,{emit}){
    const hobbys = ref([]);
    const count = ref(0);
    const price = ref(0);
    const addHobby = (hobby) => {
        hobbys.value.push(hobby);
    }
    const countUp = () => {
      count.value++;
      console.log(count.value);
    }
    const computeCount = computed(()=> {
      return 1500*count.value;
    });


    return{
        addHobby,hobbys,countUp,computeCount,count,

    }
  }
}
</script>

<style>

</style>