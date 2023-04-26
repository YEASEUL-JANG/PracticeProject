import {onUnmounted, ref} from "vue";

export const messageAlert = () => {
    const timeout = ref();
    const test = (message) => {
        timeout.value = setTimeout(() => {
            alert(message);
        },1000);
    }
    onUnmounted(()=> {
        clearTimeout(timeout.value);
    })
    return{
        test,timeout
    }
}