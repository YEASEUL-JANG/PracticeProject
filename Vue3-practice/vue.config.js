const { defineConfig } = require('@vue/cli-service')
module.exports = 
  defineConfig({
    transpileDependencies: true,
    lintOnSave:false
 })
//  module.exports = {
//   chainWebpack: config => {
//     config.module.rules.delete('eslint');
//   },
//   rules:{
//     'vue/multi-word-component-names':0,
//   },
// }
