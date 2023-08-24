<template>
 <div>
  2. <Test5_comp/>
 </div>
 <div>
  3. <div class="form-group">
  <input class="form-control"
         v-model="checkText"
         placeholder="아무거나 작성해주세요">
  <button type="button"
  class="btn"
  :class="checkType === true ?'btn-success':'btn-danger'">
   {{checkType === true? '작성':'미작성'}}
  </button>
 </div>
 </div>
</template>
<script>
import {ref, watch} from "vue";
import Test5_comp from "@/pages/practice/test5_comp.vue";

export default {
    components: {Test5_comp},
    setup(){
        const checkType=ref(false);
        const checkText = ref('');

     watch(checkText,()=>{
      if(checkText.value !== '') checkType.value = true;
      else checkType.value = false;
     });
    return{
        onsubmit,checkText,checkType
    }
    }
}
</script>