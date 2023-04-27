<template>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <router-link class="navbar-brand" to="/">YEASEUL</router-link>
      <ul class="navbar-nav mr-auto ">
        <li class="nav-item">
          <router-link class="nav-link" to="/todos">Todos</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to="/practice">Practice</router-link>
        </li>
      </ul>
     
</nav>
    <div class="container">
  <router-view/>
    </div>
    <transition name="slide">
  <Toast/>
    </transition>
</template>

<script>
import Toast from "@/components/Toast.vue";

export default {
components:{
    Toast
},
}
</script>

<style scoped>
</style>
