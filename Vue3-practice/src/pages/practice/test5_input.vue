<template>
  <div>
      {{label}} :
      <input
          type="text"
          :value="email"
          @input="onInput">
  </div>
</template>
<script>
export default {
    props:{
      label:{
          type: String
      } ,
      email:{
          type: String
      }
    },
    setup(props,{emit}){
        const onInput = (e) => {
            emit("update:email",e.target.value);
        }
        return{
          onInput
        }
    }
}
</script>