<template>
    <div>
        <List :items="todo">
<!--           해당 컴포넌트 사이의 코드가 List안의 slot으로 들엉가게 됨.-->
         <template #default="{item, index}">
<!--           item과 index를 List 컴포넌트  -->
          <div class="card-body p-2 d-flex align-itmes-center" style="cursor: pointer" @click="moveToPage(item.id)" >
            <div class="flex-grow-1" >
              <input class="ml-2 mr-2" type="checkbox" :checked="item.completed" @click.stop="toggleTodo(index,$event)">
              <span :class="{thing: item.completed} ">{{item.subject}}</span>
              <!-- :style="thing.completed?todoStyle:{}" 으로 스타일을 바인딩해서 사용할 수도 있다.  -->
              <!-- :class 속성을 바인딩할때 {클래스이름 : 속성} 에서 속성이 true일때만 클래스가 동작하게 된다.  -->
            </div>
            <div><!--이벤트 버블링 현상을 @click.stop으로 막자-->
              <button class="btn btn-danger btn-sm" @click.stop="openModal(item.id)">Delete</button>
            </div>
          </div>
         </template>
        </List>
    </div>
    <teleport to="#modal">
  <DeleteModal
  v-if="showModal"
  @close="closeModal"
  @delete="deletething"/>
    </teleport>
</template>

<script>
import {useRouter} from 'vue-router';
import {ref} from 'vue';
import List from "@/components/List.vue";
import DeleteModal from "@/components/DeleteModal.vue";
export default {
    components: {DeleteModal,List},
    props:['todo'],
    emits:['toggle-todo','delete-thing'],//emit으로 보낸값도 따로 적어주는것이 좋다. 
// props로 todo를 부모컴포넌트로 부터 받아온다.
    setup(props, {emit}){//context.emit을 {emit}으로 바로 사용가능
      // watchEffect(() => {
      //   console.log(props.todo.length);
      // });
        const router=useRouter();
        const showModal = ref(false);
        const deleteId = ref(null);
        const toggleTodo=(index,event)=>{
            emit('toggle-todo',index, event.target.checked);
            //부모컴포넌트로 자식컴포넌트의 값이 변경되었음을 알린다.
            //event객체의 checked값을 보내서 check이면 true, 아니면 false를 보냄.
        };
        const openModal = (id) => {
         showModal.value = true;
         deleteId.value = id;
        }
        const closeModal = () => {
         deleteId.value = null; //todo초기화
         showModal.value = false;
        }
        const deletething=()=>{
            emit('delete-thing',deleteId.value);
            showModal.value = false;//모달을 닫는다.
            deleteId.value = null; //todo초기화
        }
        const moveToPage =  (id) => {
          router.push('/todos/'+id);
        }
        return{
            toggleTodo,deletething,moveToPage,showModal,openModal,closeModal
        };
    }
}
</script>

<style>

</style>