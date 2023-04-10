<template>
<div class="container">
   <div v-for="(hobby,index) in hobbys" :key="index">
    {{hobby}},
  </div>
  </div>
</template>

<script>
import {ref} from 'vue';
export default {
    name: "test2_component",
    props:['hobbys'],
    emits:['add-hobby'],
    setup(props,{emit}) {
        const hobby = ref('');
        const onsubmit = () => {
            emit('add-hobby',hobby.value);
            hobby.value='';
        }
        return {
            hobby,onsubmit
        }
    }
}
</script>

<style>

</style>