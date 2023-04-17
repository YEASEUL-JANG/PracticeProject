<template>
  <div>
      1. router test <br>
      <router-link to="/homepage">홈 페이지</router-link> <br>
      <router-link to="/list">리스트 이동</router-link>
  </div>
    <hr>
    <div>
        2. 마시고 싶은 음료의 수량 (음수는 인정안됨)<br>
        <button @click="plus">담기</button>
        {{count}}
        <button @click="minus">빼기</button><br>
        Per Price: 2000 <br>
        Total Price : {{computedcount}} <br>
        <div v-show="show" style="color:red">음수는 인정되지 않습니다.</div>

    </div>
    <router-view/>
</template>

<script>
import {computed, ref} from 'vue';
export default {
setup(){
    const count = ref(0);
    const total = ref(0);
    const show = ref(false);
    const plus = () => {
        count.value ++;
    }
    const computedcount = computed(()=>{
        const totalprice = count.value * 2000;
        if(count.value < 0){
            // eslint-disable-next-line vue/no-side-effects-in-computed-properties
            show.value = true;
        }else{
            // eslint-disable-next-line vue/no-side-effects-in-computed-properties
            show.value =false;
        }
        console.log(show.value);
        return totalprice;
    });
    const minus = () => {
        count.value --;
    }
    return{
        count,plus, minus,total,computedcount,show
    }
}
}
</script>