<template>
    <div class="form-group">
        <label>{{ label }}</label>
<!--        v-model 대신 :value로 값을 받고 @input이벤트 함수로 값을 보낸다.-->
        <input type="text"
               :value="subject"
               @input="onInput"
               class="form-control">
        <div v-if="error" class="text-red">
            {{error}}
        </div>
    </div>
</template>
<script>
//import {ref} from "vue";
export default {
    props:{
      label: {
          type: String,
          required: true
      },
      error: {
          type: String,
          required: true
      },
      subject: {
          type: String,
          required: true
      }
    },
    setup(props,{emit}){
        const onInput = (e) => {
            emit("update:subject",e.target.value);
        }
        return{
            onInput,

        }
    }
}
</script>
<style scoped>
.text-red{
    color: red;
}
</style>



