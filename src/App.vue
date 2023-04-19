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
</template>

<script>
export default {

}
</script>

<style>
.box{
  background-color:antiquewhite;
  border-radius: 10px;
  margin: 5px;
  padding: 30px;
}
</style>
