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
  <router-view/>
</template>

<script>
export default {

}
</script>

<style>

</style>
