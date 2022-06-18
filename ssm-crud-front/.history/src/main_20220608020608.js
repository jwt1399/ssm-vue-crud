import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)
installElementPlus(app)
app.use(router).mount('#app')