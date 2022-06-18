<template>
      <Header/>
      <Form/>
      <Footer/>
</template>


<script>
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import CrudForm from '@/components/Form.vue'
export default {
    name: 'CrudView',
    components: {
        Header,
        Footer,
        CrudForm,
    }
}
</script>