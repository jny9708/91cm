<template>
    <div contenteditable v-html="text" @focusout="handle">click</div>
</template>
<script>
export default {
    name:'Test',
    data(){
        return{
            text:'&lt;'
        }
    },
    methods:{
        handle(e){
            let obj = {content:e.target.innerText}
                this.$http.post('/api/task/update/date', obj)
                .then(res => {
                    
                }).catch(error => {
                    console.error(error)
                })
        }
    }
}
</script>