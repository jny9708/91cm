<template>
    <div>
        <v-tabs
            v-model="tab"
            grow
            @change="tabClick"
        >
            <v-tab >Item One</v-tab>
            <v-tab >Item Two</v-tab>
            <v-tab>Item Three</v-tab>
        </v-tabs>
        <TaskDetails :tab="tab"></TaskDetails>
     </div>
</template>
<script>
import TaskDetails from './TaskDetails'
export default {
    name: 'TaskTab',
    components:{TaskDetails},
    data(){
        return{
            tab:0,
        }
    },
    methods:{
        tabClick:function(){
            console.log(this.tab)
        }
    }

}
</script>