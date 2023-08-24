<template>
 <div>
 1.<Test5_input
   label="email"
   v-model:email="email"
 ></Test5_input>
 <button @click="onsubmit">제출</button> <br>
 이메일은 <input :value="submitEmail"> 입니다.
 </div>
</template>
<script>
import Test5_input from "@/pages/practice/test5_input.vue";
import {ref} from "vue";
export default {
    components: {Test5_input},
    setup(){
        const email = ref('');
        const submitEmail = ref('');

        const onsubmit = () => {
            submitEmail.value = email.value;
            email.value = "";
        }
        return{
            email, onsubmit, submitEmail
        }
    }
}
</script>
