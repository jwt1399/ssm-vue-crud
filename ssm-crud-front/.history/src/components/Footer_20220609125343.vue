<template>
<el-footer class="footer"> 
    <span>SSM-VUE-CRUD ©2022 Created by </span> 
    <a href="https://jwt1399.top" style="text-decoration: none;"> jwt1399.top</a>
 </el-footer>
</template>


<script>
export default{
    name:'Footer',
}
</script>

 <style scoped>
.footer {
   /*等于footer的高度 */
    /* margin-top: 60px;  */
    

    position: fixed;
    text-align: center; 
    /* left: 0px; */
    bottom: 0px;
    width: 100%;
    height: 50px;
    background-color: #eee;
    z-index: 9999;
    border-top:1px solid #E7E7E7;
} 

</style>