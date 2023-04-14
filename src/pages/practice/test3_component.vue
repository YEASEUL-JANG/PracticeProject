<template>
    <div>
        <form @submit.prevent="submit">
      Team명 :   <input v-model="teamname"><br>
      인원 :   <input type="number" v-model="num">
            <button type="submit">제출</button><br>
        </form>
    </div>
</template>

<script>
import {ref} from 'vue';

export default {
    setup(props,{emit}){
        const teamname = ref("");
        const num = ref(0);

        const submit = () => {
            emit('numsubmit',{
                teamname:teamname.value,
                num: num.value,
                show: true
            });
            teamname.value="";
            num.value=0;
        }
        return{
            teamname, num, submit,
        };
    }
}
</script>

<style>
</style>