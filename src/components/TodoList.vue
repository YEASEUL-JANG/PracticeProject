<template>
    <div>
        <div class="card mt-2" v-for="(thing,index) in todo" :key="thing.id">
          <div class="card-body p-2 d-flex align-itmes-center" @click="moveToPage(thing.id)" >
            <div class="form-check flex-grow-1" >
              <input class="form-check-input" type="checkbox" :checked="thing.completed" @input="toggleTodo(index)">
              <label class="form-check-label" :class="{thing: thing.completed} ">{{thing.subject}}</label>
              <!-- :style="thing.completed?todoStyle:{}" 으로 스타일을 바인딩해서 사용할 수도 있다.  -->
              <!-- :class 속성을 바인딩할때 {클래스이름 : 속성} 에서 속성이 true일때만 클래스가 동작하게 된다.  -->
            </div>
            <div>
              <button class="btn btn-danger btn-sm" @click="deletething(index)">Delete</button>
            </div>
          </div>
      </div>
    </div>
</template>

<script>
import {useRouter} from 'vue-router';
//import { watchEffect } from 'vue';
export default {
    props:['todo'],
    emits:['toggle-todo','delete-thing'],//emit으로 보낸값도 따로 적어주는것이 좋다. 
// props로 todo를 부모컴포넌트로 부터 받아온다.
    setup(props, {emit}){//context.emit을 {emit}으로 바로 사용가능
      // watchEffect(() => {
      //   console.log(props.todo.length);
      // });
        const router=useRouter();
        const toggleTodo=(index)=>{
            emit('toggle-todo',index);
            //부모컴포넌트로 자식컴포넌트의 값이 변경되었음을 알린다. 
        };
        const deletething=(index)=>{
            emit('delete-thing',index);
        }
        const moveToPage =  (id) => {
          router.push('/todos/'+id);
        }
        return{
            toggleTodo,deletething,moveToPage
        };
    }
}
</script>

<style>

</style>