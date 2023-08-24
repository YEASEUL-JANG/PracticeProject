<template>
    <form @submit.prevent="onSubmit">
      <div class="d-flex">
        <div class="flex-grow-1 mr-2">
          <input class="form-control" v-model = "thing">
        </div>
        <div>
        <button class="btn btn-primary" type="submit" >Add</button>
        </div>
      </div>
      <div v-show="haserror" style="color:red">This field cannot be empty</div>
    </form>
</template>

<script>
import {ref} from 'vue';
export default{
    emits:['add-todo'],
    setup(props, context){
        const thing = ref('');
        const haserror = ref(false);

        //메소드
        const onSubmit = () =>{
      if(thing.value==''){
        haserror.value=true;
      }else{
        context.emit('add-todo',{
            id: Date.now(),
            subject: thing.value,
            completed: false
        });//자식컴포넌트에서 부모컴포넌트로 필요한 값을 보낼때 context 안에 emit을 이용해서 보냄.
      haserror.value=false;
      thing.value='';
      }
    };
        return {
            thing,haserror,
            onSubmit,
        };
    }
}
</script>

<style>
</style>