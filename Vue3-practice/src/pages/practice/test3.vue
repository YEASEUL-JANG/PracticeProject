<template>
    <div class="container">
        <h1>Practice</h1>
        <div class="box">
            1. 자식컴포넌트에서 보낸 team{teamname:"",num:0,show:false}의 props를 부모컴포넌트에서 받아 watchEffect 로 team변경을 관찰하여 팀이름과 총금액 출력하기
        </div>

        1.
        <test3_component @numsubmit="numSubmit"/>
        <div v-if="team.show">팀이름은 {{team.teamname}}이고 지원경비는 인당 만원으로 총 {{money}}원 이다.</div>
        <hr>
        2.
        <router-link to="/test">Go test</router-link>
        <hr>
        3.
        <router-link to="/homepage">홈 페이지</router-link> <br>
        <router-link to="/list">리스트 이동</router-link>
        <router-view/>
    </div>

</template>

<script>
import {ref, watchEffect} from 'vue';
import test3_component from "@/pages/practice/test3_component.vue";

export default {
    components: {test3_component},
    props:['numsubmit'],
    setup(){
        const team =ref({
            teamname:"",
            num:0,
            show: false
        });
        const money = ref(0);
        watchEffect(() => {
            money.value = team.value.num * 10000;
        });

        const numSubmit=(propteam)=>{
            team.value.teamname=propteam.teamname;
            team.value.num = propteam.num;
            team.value.show = propteam.show;
        }
        return{
            numSubmit,team,money,
        };
    }
}
</script>

<style>
</style>