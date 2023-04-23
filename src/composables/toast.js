import {onUnmounted, ref} from "vue";

export const useToast = () => {
    const showToast = ref(false);
    const toastMessage = ref('');
    const toastAlertType = ref('');
    const toastTimeout = ref(null);
    const timeout = ref(null);
    const triggerToast = (message,type) => {
        toastMessage.value = message;
        toastAlertType.value = type;
        showToast.value = true;
        toastTimeout.value = setTimeout(() => {
            toastMessage.value = '';
            toastAlertType.value = '';
            showToast.value = false;
        },3000)
    }
    onUnmounted(() => {
        //쓸데없는 메모리 누수 방지
        clearTimeout(timeout.value);
    });
    return {
        triggerToast,
        toastAlertType,
        toastMessage,
        showToast
    }
}