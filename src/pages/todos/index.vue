<template>
  <div class="container">
    <h2>To Do List</h2>
    <input class="form-control" v-model="searchText" placeholder="Search" @keyup.enter="searchTodo">
    <hr/>
    <TodoSimpleForm @add-todo="addTodo"/>
    <!-- @emit을 통해 자식컴포넌트에서 받은 객체명="실행할 함수" -->
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
</template>

<script>
import { ref, computed, watch } from 'vue'; // watchEffect, 
import TodoSimpleForm from '@/components/TodoSimpleForm.vue';
import TodoList from '@/components/TodoList.vue';
import axios from 'axios';

export default{
  components:{
    TodoSimpleForm,TodoList
  },
  setup(){
    const todo = ref([]);
    const searchText = ref('');
    const error = ref('');
    const numberOfthings = ref(0);
    let limit = 5;
    const currentPage = ref(1);

    
    //총 페이지 수 계산
    const numberOfPages = computed(() => {
        return Math.ceil(numberOfthings.value/limit);
    });

    //검색기능을 watch로 구현
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

    //todo리스트에서 값을 불러오기 (get)
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
        error.value = 'Something went wrong';
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
        error.value = 'Something went wrong';
      }
    };
    //todo리스트 값 삭제하기
    const deletething = async (index) => {
      error.value = '';
      try{
      const id = todo.value[index].id;
      await axios.delete('http://localhost:3000/todos/'+id);
      getTodo(1);
    } catch (err){
      console.log(err);
      error.value = 'Something went wrong';
     }
    };
    //todo리스트 값 수정하기
    const toggleTodo = async (index) =>{
      const id = todo.value[index].id;
      try{
      await axios.patch('http://localhost:3000/todos/'+id,{
        completed : !todo.value[index].completed
      });
      todo.value[index].completed = !todo.value[index].completed;
     } catch (err) {
        console.log(err);
     }
    };

    return {
      todo,searchText,//filteredTodos
      error,numberOfPages,currentPage,
      addTodo,deletething,toggleTodo,getTodo,searchTodo,
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

