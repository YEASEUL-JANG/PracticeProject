<template>
<div v-for="(item,index) in items" :key="index">
    <slot :item="item"></slot>
</div>
</template>
<script>
export default {
props: {
    items:{
    }
}
}
</script>