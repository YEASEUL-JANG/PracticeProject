<template>
 <div>
 1.<Test5_input
   label="email"
   v-model:email="email"
 ></Test5_input>
 <button @click="onsubmit">제출</button> <br>
 이메일은 <input :value="submitEmail"> 입니다.
 </div>
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
import Test5_input from "@/pages/practice/test5_input.vue";
import {ref, watch} from "vue";
import Test5_comp from "@/pages/practice/test5_comp.vue";

export default {
    components: {Test5_comp, Test5_input},
    setup(){
        const email = ref('');
        const submitEmail = ref('');
        const checkType=ref(false);
        const checkText = ref('');

     watch(checkText,()=>{
      if(checkText.value !== '') checkType.value = true;
      else checkType.value = false;
     });
     const onsubmit = () => {
         submitEmail.value = email.value;
        }
    return{
        email, onsubmit, submitEmail,checkText,checkType
    }
    }
}
</script>