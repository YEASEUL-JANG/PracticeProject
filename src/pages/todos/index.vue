<template>
    <div>
        <div class="d-flex justify-content-between mb-3">
        <h2>To Do List</h2>
            <button class="btn btn-primary"
            @click="moveToCreatePage">Create Todo</button>
        </div>
    <input class="form-control" v-model="searchText" placeholder="Search" @keyup.enter="searchTodo">
    <hr/>
    <div>{{ error }}</div>
    <div v-if="!todo.length">Todo를 추가해주세요</div>
    <TodoList :todo="todo" @toggle-todo="toggleTodo" @delete-thing="deletething"/>
    <!-- :자식컴포넌트로 보낼 이름="데이터이름" : props -->
    <hr/>
    <!-- 페이지네이션 -->
    <nav aria-label="Page navigaion example">
      <ul class="pagination">
        <li v-if="currentPage != 1" class="page-item">
          <a class="page-link" @click="getTodo(currentPage -1)" style="cursor:pointer">Previous</a></li>
        <li v-for="page in numberOfPages" :key="page" class="page-item" 
        :class="currentPage == page ? 'active' : ''">
        <a class="page-link" @click="getTodo(page)" style="cursor:pointer">{{ page }}</a></li>
        <li class="page-item" v-if="numberOfPages != currentPage">
          <a class="page-link" @click="getTodo(currentPage +1)" style="cursor:pointer">Next</a></li>
      </ul>
    </nav>
  </div>
    <Toast v-if="showToast"
           :message="toastMessage"
           :type="toastAlertType"/>
</template>

<script>
import { ref, computed, watch } from 'vue'; // watchEffect,
import TodoList from '@/components/TodoList.vue';
import axios from 'axios';
import Toast from '@/components/Toast.vue';
import {useToast} from "@/composables/toast";
import {useRouter} from "vue-router";
export default{
  components:{
    TodoList,Toast
  },
  setup(){
    const todo = ref([]);
    const searchText = ref('');
    const error = ref('');
    const numberOfthings = ref(0);
    let limit = 5;
    const currentPage = ref(1);
    const router = useRouter();
const {
    toastMessage,
    toastAlertType,
    showToast,
    triggerToast
} = useToast();
    
    //총 페이지 수 계산
    const numberOfPages = computed(() => {
        return Math.ceil(numberOfthings.value/limit);
    });

    // 검색기능을 watch로 구현
    let timeout = null;
    watch(searchText, () => {
      clearTimeout(timeout);//중복요청 방지를 위해 이전 timeout 요청을 삭제한다.
      timeout = setTimeout(() =>{
        getTodo(1);//검색데이터의 첫페이지
      }, 2000);//2초후에 함수가 실행된다.
    });

    const searchTodo = () =>{
      clearTimeout(timeout);
      getTodo(1);
    }


    // todo 리스트에서 값을 불러오기 (get)
    const getTodo = async (page = currentPage.value) =>{
      error.value = ''; 
      currentPage.value=page;
      try{
        const res = await axios.get(
          //백틱으로 감싸면 안에 자바스크립트 변수 입력가능
          `http://localhost:3000/todos?_sort=id&_order=desc&subject_like=${searchText.value}&_page=${page}&_limit=${limit}`
          );
          numberOfthings.value = res.headers['x-total-count'];
          todo.value = res.data;
      }catch (err) {
        console.log(err);
          triggerToast('Something went wrong','danger');
      }
    };
    getTodo();

    // todo 리스트에 값을 저장하기 (post)
    const addTodo = async (thing) =>{
      error.value = '';
      try {      
       await axios.post('http://localhost:3000/todos',{
        subject: thing.subject,
        completed: thing.completed,
      });
        getTodo(1);
    } catch (err){
        console.log(err);
          triggerToast('Something went wrong','danger');
      }
    };
    // todo 리스트 값 삭제하기
    const deletething = async (id) => {
      error.value = '';
      try{
      await axios.delete('http://localhost:3000/todos/'+id);
      getTodo(1);
    } catch (err){
      console.log(err);
      triggerToast('Something went wrong','danger');
     }
    };
    // todo 리스트 값 수정하기
    const toggleTodo = async (index, checked) =>{
      const id = todo.value[index].id;
      try{
      await axios.patch('http://localhost:3000/todos/'+id,{
        completed : checked
      });
      todo.value[index].completed = checked
     } catch (err) {
        console.log(err);
          triggerToast('Something went wrong','danger');
     }
    };

    const moveToCreatePage= () => {
        router.push({
            name: 'TodoCreate'
        })
    }

    return {
      todo,searchText,//filteredTodos
      error,numberOfPages,currentPage,
      addTodo,deletething,toggleTodo,getTodo,
        searchTodo,showToast,toastMessage,toastAlertType,triggerToast,moveToCreatePage
    };
  }
}
</script>

<style>
.thing{
  color: gray;
  text-decoration: line-through;
}
</style>

