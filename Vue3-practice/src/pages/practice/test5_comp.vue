<template>
<div>
    <p>Test Page</p>
    <p><textarea v-model="text"></textarea></p>
    <p><button @click="alert">alert버튼</button></p>
</div>
</template>
    <script>
    import {ref} from "vue";
    import { messageAlert } from "@/pages/practice/testModule";

    export default{
    setup(){
    const text = ref('');
    const {test} = messageAlert();
    const alert = () => {
        test(text.value);
    }
    return{
    text,alert,
    }
    }
    }
    </script>

