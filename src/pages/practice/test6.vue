<template>
  <div>
      1.<div>
      <button @click="plus">++</button>
      <span v-if="state.show">{{state.count}}</span>
      <span v-else>{{state.message}}</span>
      <button @click="minus">--</button>
  </div>
      <div>
          2. list 출력 <br>
          <input type="text"
                 v-model="word">
          <button @click="onsubmit">제출</button>
          <div v-if="state.showpopup">{{state.popupmessage}}</div>
          <button @click="golist">리스트보기</button>
      </div>
  </div>
</template>
<script>
import {useStore} from "vuex";
import {computed, ref} from "vue";
import axios from "@/axios";
import {useRouter} from "vue-router";

export default {
    setup() {
        const store = useStore();
        const router = useRouter();
        const state = computed(() => store.getters["test/getCount"]);
        //const popupshow = computed(() => store.state["test/showpopup"]);
        //const popupmessage = computed(() => store.state["test/popupmessage"]);
        const word = ref('');
        const plus = () => {
            store.commit('test/ADD_COUNT');
        }
        const minus = () => {
            store.commit('test/MINUS_COUNT');
        }
        const onsubmit = async () => {
            await axios.post('todolist',
                {
                    subject: word.value
                });
            word.value='';
            store.commit('test/SHOW_MODAL','제출이 완료되었습니다.');
            console.log(state.value.showpopup);
        }
        const golist = () => {
            router.push({
                name: 'TodoList'
            })
        }

        return{
            state,
            plus,minus,word,onsubmit,golist
        }
    }
}
</script>