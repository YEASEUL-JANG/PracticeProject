<template>
 <h1>To-do Page</h1>
 <div v-if="loading">loading...</div>
 <form v-else @submit.prevent="onSave">
  <div class="row">
   <div class="col-6">
    <div class="form-group">
     <label>Subject</label>
     <input type="text"
            v-model="todo.subject"
            class="form-control">
    </div>
   </div>
   <div class="col-6">
    <div class="form-group">
     <label>Status</label>
     <div>
      <button type="button" class="btn"
      :class="todo.completed?'btn-success':'btn-danger'"
      @click="toggleTodoStatus">
       {{ todo.completed ? 'Completed':'Incomplete' }}</button>
     </div>
    </div>
   </div>
  </div>

  <button type="submit"
          class="btn btn-primary"
  :disabled="!todoUpdated">
   Save
  </button>
  <button class="btn btn-outline-dark ml-2"
  @click="moveToTodoListPage">
   Cancel
  </button>
 </form>
 <Toast v-if="showToast"
 :message="toastMessage"
 :type="toastAlertType"/>
</template>
<script>
import { useRoute, useRouter } from 'vue-router';
import axios from "axios";
import {ref,computed} from "vue";
import _ from "lodash";
import Toast from '@/components/Toast.vue';
export default {
 components:{ Toast},
  setup () {
      const route = useRoute();
      const router = useRouter();
      const todo = ref(null);
      const originalTodo = ref(null);
      const loading = ref(true);
      const showToast = ref(false);
      const toastMessage = ref('');
      const todoId = route.params.id;
      const toastAlertType = ref('');
      const getTodo = async () => {
       try {
        const res = await axios.get(`http://localhost:3000/todos/${todoId}`);
       todo.value = res.data;
       originalTodo.value = {...res.data};
       //같은 객체를 다른곳에 담게되면 같은 주소를 바라보고 있기 때문에
       //res값이 변경되면 나머지도 같이 변경 될 수 있다. 따라서 서로 다른 객체에 담는걸로 하자.
       loading.value = false;
      }catch(err){
        console.log(err);
        triggerToast('Something went wrong','danger');
       }
      };
      const todoUpdated = computed(() => {
       return !_.isEqual(todo.value,originalTodo.value);
      })//두 객체가 같지 않다면 업데이트가 된것


      const toggleTodoStatus = () =>{
       todo.value.completed=!todo.value.completed;
      }
      const moveToTodoListPage = () => {
       router.push({
        name: 'Todos'
       })
      };
      const triggerToast = (message,type) => {
       toastMessage.value = message;
       toastAlertType.value = type;
       showToast.value = true;
       setTimeout(() => {
        toastMessage.value = '';
        toastAlertType.value = '';
        showToast.value = false;
       },3000)
      }
      const onSave = async () => {
       try {
        const res = await axios.put(`http://localhost:3000/todos/${todoId}`, {
         subject: todo.value.subject,
         completed: todo.value.completed
        });
        originalTodo.value = {...res.data};
        triggerToast('Successfully saved!','success');
       } catch(err) {
        console.log(err);
        triggerToast('Something went wrong','danger');
       }
      }
      getTodo();
      return {
      todo,loading,toggleTodoStatus,moveToTodoListPage,
       onSave,todoUpdated,showToast,toastMessage,toastAlertType,
      }
  }
}
</script>
<style>

</style>