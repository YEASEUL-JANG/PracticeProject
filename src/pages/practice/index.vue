<template>
  <div class="container">
  <h1>Practice</h1>
  <div class="box">
    1. 클릭 시 개인정보 보이게 출력 <br>
    / @click 사용 <br>
    /name 은 ref, address는 reactive를 사용해서 두개의 객체(addr1, addr2) 삽입 <br>
    /phone은 v-model을 사용해서 클릭 시 phone 변수에 값이 저장 (console에 출력해서 확인) <p></p>
    2. 계절 배열을 생성하고 v-for를 사용해서 출력하기<p></p>
    3. show 토글버튼 만들기 (@click, v-if)


  </div>
  1.
  <div> name : {{name}}</div>
  <div>address : {{address.addr1}}, {{address.addr2}}</div>
  <div> phone : <input v-model="phone" ></div>
  <button @click="changename">click</button>
  <hr>
  2.
  <div v-for="s in season" :key="s.id">
    {{s.value}},
  </div>
  <hr>
  3.
  <div v-if="show">v-if TEST</div><button @click="showword">show</button>


  </div>
</template>

<script>
import {ref, reactive} from 'vue';

export default {
  setup(){
    const name = ref("");
    const address = reactive({
      addr1: "",
      addr2: ""
  });
    const phone = ref("");
    const season = ref([
      {id:1,value:"Spring"},
      {id:2,value:"Summer"},
      {id:3,value:"Fall"},
      {id:4,value:"Winter"}
      ]);
    const show = ref(true);


    const changename = () => {
      name.value = "yeaseul";
      address.addr1="서울 강서구 공항대로103";
      address.addr2="1234-234";
      console.log(phone.value);
    }
    const showword = () => {
      show.value=!show.value;
    }
    return{
       changename, name, address, phone, season,showword,show
    };
  }
}
</script>

<style>
.box{
  background-color:antiquewhite;
  border-radius: 10px;
  margin: 5px;
  padding: 30px;
}
</style>