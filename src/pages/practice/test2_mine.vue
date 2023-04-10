<template>
<div class="container">
  <test2_comp_mine @add-hobby="addHobby" />
  1. 제 취미는 
  <test2_comp2_mine :hobbys=hobbys />
   입니다!
    
    <hr>

   2.(세정님_computed) <br>
    사과의 가격은 <input v-model="price">원 이다.<br>
    세정이는 사과 {{count}}개가 먹고싶다. <button @click="countUp"> click</button> <br>
    세정이 지불해야 할 총 금액은 {{computeCount}}원이다.
    <hr>
    3. (민정님_async)<br>
    1. 등록 <br>
    <input v-model="data"> <button @click="insertdata">등록</button><br>
    2. 불러오기 
    <div v-for="(d,index) in datas" :key="index"> 
      {{d.content}} 
      <input type="checkbox" @click="deleteData(index)">
      </div>
      <button @click="getdata">불러오기</button>
    
  </div>
</template>

<script>
import {computed, ref} from 'vue';
import test2_comp_mine from './test2_comp_mine.vue'
import test2_comp2_mine from './test2_comp2_mine.vue'
import axios from 'axios';
export default {
  components: { test2_comp_mine, test2_comp2_mine },
  emit:['addHobby'],
  setup(props,{emit}){
    const hobbys = ref([]);
    const count = ref(0);
    const price = ref(0);
    const data = ref("");
    const datas = ref([]);
    const addHobby = (hobby) => {
        hobbys.value.push(hobby);
    }
    const deleteData = async(index) => {
      try{
        const id = datas.value[index].id;
        await axios.delete('http://localhost:3000/test/'+id);
    }catch(err){
      console.log(err);
    }
    getdata();
    }
    const countUp = () => {
      count.value++;
      console.log(count.value);
    }
    const insertdata = async() => {
      try{
        await axios.post('http://localhost:3000/test',{
          content: data.value
        });
        data.value="";
      }catch(err){
        console.log(err);
      }
    }
    const getdata = async() => {
      try{
        const res = await axios.get('http://localhost:3000/test');
        datas.value = res.data;
      }catch(err){
        console.log(err);
      }
    }
    const computeCount = computed(()=> {
      return 1500*count.value;
    });



    return{
        addHobby,hobbys,countUp,computeCount,count,
        insertdata,data,price,getdata,datas,deleteData,

    }
  }
}
</script>

<style>

</style>