<template>
<div v-if="loading">loading...</div>
<form v-else @submit.prevent="onSave">
<div class="row">
    <div class="col-6">
<!--       todo.subject를 Input 컴포넌트의 :subject로 내려주고 (한개만일 경우 생략가능하다)
            Input컴포넌트에서 올리는 subject는 emit으로 : 을 사용해 올린다.-->
        <Input
            v-model:subject="todo.subject"
            :error="subjectError"
            label="Subject"
        >

        </Input>
    </div>
    <div v-if="editing" class="col-6">
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
    <div class="col-12">
        <div class="form-group">
            <label>Body</label>
            <textarea v-model="todo.body" class="form-control" />
        </div>
    </div>
</div>

<button type="submit"
        class="btn btn-primary"
        :disabled="!todoUpdated">
    {{ editing ? 'Update': 'Create' }}
</button>
<button class="btn btn-outline-dark ml-2"
        @click="moveToTodoListPage">
    Cancel
</button>
</form>
    <transition name="fade">
<Toast v-if="showToast"
       :message="toastMessage"
       :type="toastAlertType"/>
    </transition>
</template>
 <script>
 import { useRoute, useRouter } from 'vue-router';
 import axios from "axios";
 import {ref,computed } from "vue";
 import _ from "lodash";
 import Toast from '@/components/Toast.vue';
 import {useToast} from "@/composables/toast";
import Input from "@/components/input.vue";
 export default {
     components:{ Toast, Input},
     props: {
       editing: {
           type: Boolean,
           default: false
       }
     },
     setup ( props ) {
         const route = useRoute();
         const router = useRouter();
         const todo = ref({
             subject: '',
             completed: false,
             body: ''
         });
         const originalTodo = ref(null);
         const loading = ref(false);
         const todoId = route.params.id;
         const {
             toastMessage,
             toastAlertType,
             showToast,
             triggerToast
         } = useToast();
         const subjectError = ref('');
         const getTodo = async () => {
             loading.value=true;
             try {
                 const res = await axios.get(`http://localhost:3000/todos/${todoId}`);
                 todo.value = res.data;
                 originalTodo.value = {...res.data};
                 //같은 객체를 다른곳에 담게되면 같은 주소를 바라보고 있기 때문에
                 //res값이 변경되면 나머지도 같이 변경 될 수 있다. 따라서 서로 다른 객체에 담는걸로 하자.
                 loading.value = false;
             }catch(err){
                 loading.value = false;
                 console.log(err);
                 triggerToast('Something went wrong','danger');
             }
         };
         const todoUpdated = computed(() => {
             return !_.isEqual(todo.value,originalTodo.value);//값만 비교 (주소비교x)
         })//두 객체가 같지 않다면 업데이트가 된것


         const toggleTodoStatus = () =>{
             todo.value.completed=!todo.value.completed;
         }
         const moveToTodoListPage = () => {
             router.push({
                 name: 'Todos'
             })
         };
         const onSave = async () => {
             if(!todo.value.subject) {
                 subjectError.value = 'Subject is required';
                 return;
             }
             try {
                 let res; //let은 값 재설정 가능, const는 재설정 불가
                 const data = {
                     subject: todo.value.subject,
                     completed: todo.value.completed,
                     body : todo.value.body
                 }
                 if(props.editing) {//editing모드이면 put으로 update쿼리, create모드이면 insert쿼리를 진행
                    res = await axios.put(`http://localhost:3000/todos/${todoId}`, data);
                     originalTodo.value = {...res.data};
                 } else {
                     res = await axios.post(`http://localhost:3000/todos`, data);
                     todo.value.subject = '';
                     todo.value.body = '';

                 }

                 const message = 'Successfully ' + (props.editing ? 'Updated' : 'Created');
                 triggerToast(message);
             } catch(err) {
                 console.log(err);
                 triggerToast('Something went wrong','danger');
             }
         }
         if(props.editing){
         getTodo();
         }
         return {
             todo,loading,toggleTodoStatus,moveToTodoListPage,subjectError,
             onSave,todoUpdated,showToast,toastMessage,toastAlertType,
         }
     }
 }
 </script>
<style scoped>
/*scoped는 해당 컴포넌트에서만 적용된다는 의미*/

.fade-enter-active,
.fade-leave-active {
    transition: all 0.5s ease;
}
.fade-enter-from,
.fade-leave-to {
    opacity: 0;
    transform : translateY(-30px);
}
.fade-enter-to,
.fade-leave-from {
    opacity: 1;
    transform : translateY(0px);
}
</style>