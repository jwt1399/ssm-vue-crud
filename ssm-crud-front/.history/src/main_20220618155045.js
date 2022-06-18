import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { use as localeUse } from 'element-plus/lib/locale'
import lang from 'element-plus/lib/locale/lang/zh-cn'
import { use as localeUse } from 'element-plus/lib/locale'

const app = createApp(App)
localeUse(lang)
//配置全局icon
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

app.use(router).use(ElementPlus).mount('#app')