<template>
  <div class="container">
  {{ count }}
  <button @click="count++"> Home Page</button>
  </div>
</template>


<script>
import { useCount } from '@/composables/count.js';
export default {
  setup(){
   const { count } = useCount(); //state를 리턴하고 그 안에 count를 바로 꺼내옴
      console.log(count);
   return{
       count,
   }
  }
}
</script>

<style>

</style>