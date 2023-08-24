<template>
<div class="container">
     <div v-for="(fruit,index) in fruits" :key="index">
           * {{fruit}}<br>
     </div>
</div>
</template>
<script>
import {ref} from "vue";

export default {
    setup(){
     const fruits = ref(['사과','수박','파인애플']);
        return{
          fruits,
        }
    }
}
</script>