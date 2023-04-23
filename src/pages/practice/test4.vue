<template>
    <div class="box">
        <p>
        1. 1) 'Add a' 버튼을 만들고 클릭 시 배열 a 에 요소추가  <br>
            2) 'a와 c동기화' 버튼을 만들고 클릭 시 a를 c 배열에 객체복사(...이용) <br>
            3) onUpdated 에 lodash(_)를 이용하여 a와 c의 값이 같은지 비교하여 로그에 true/false값 출력하기<br>
            <div style="color:cadetblue">참고 ) 요소들은 v-for로 출력한다.</div>
        </p>
    </div>
    <p>
    1. a에 추가 : <input type="text" v-model="element">
    <button @click="addA">Add a</button>
        <button @click="aEqualb">a와 c 동기화</button>
    </p>
    a 의 요소 :<span v-for="(el,index) in a" :key="index">
         {{el}},
    </span>
    <br>
    c 의 요소 :<span v-for="(el,index) in c" :key="index">
         {{el}},
    </span>
    <br>

</template>
<script>
import { onUpdated, ref} from 'vue';
import _ from "lodash";
export default {
setup() {
    const count = ref(0);
    const element = ref("");
    const a = ref([]);
    const c = ref([]);
    onUpdated(() => {
        console.log("lodash 비교 : "+_.isEqual(a,c));

    })
    const addA = () => {
        a.value.push(element.value);
        element.value = "";
    }
    const aEqualb = () => {
        c.value = [...a.value];
    }
    const increment = () => {
      count.value ++;
    }

    return {
      count,
      increment,
        a,
        addA,
        c,
        element,
        aEqualb,
    }
}
}

</script>
<style></style>
