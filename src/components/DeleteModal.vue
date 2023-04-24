<template>
<modal>
<!--v-on => @
    v-bind => :
    v-slot => # -->
    <template #title>Delete Todo</template>
    <template #body>정말 삭제하시겠습니까?</template>
    <template #footer>
        <button
                type="button"
                class="btn btn-secondary"
                @click="onClose">
            Close
        </button>
        <button type="button" @click="onDelete" class="btn btn-danger">delete</button>
    </template>
</modal>
</template>
<script>
import modal from "@/components/modal.vue";
export default {
    components:{
        modal
    },
    setup(props,{emit}) {
        const onClose = () => {
            emit('close');
        }
        const onDelete = () => {
            emit('delete');
        }
        return{
            onClose,onDelete
        }
    }
}
</script>
<style>

</style>