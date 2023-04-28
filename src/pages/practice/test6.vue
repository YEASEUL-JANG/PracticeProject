<template>
  <div>
      1.<div>
      <button @click="plus">++</button>
      <span v-if="state.show">{{state.count}}</span>
      <span v-else>{{state.message}}</span>
      <button @click="minus">--</button>
  </div>
  </div>
</template>
<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    setup() {
        const store = useStore();
        const state = computed(() => store.getters["test/getCount"])

        const plus = () => {
            store.commit('test/ADD_COUNT');
        }
        const minus = () => {
            store.commit('test/MINUS_COUNT');
        }
        return{
            state,
            plus,minus
        }
    }
}
</script>