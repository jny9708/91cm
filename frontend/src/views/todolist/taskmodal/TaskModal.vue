<template>
<div>
    <v-dialog v-model="dialog" scrollable persistent max-width="600px" > 
      <template v-slot:activator="{ on, attrs }">
        <!-- <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Open Dialog
        </v-btn> -->

        <a class="dropdown-item" v-bind="attrs" v-on="on" >Details</a>

      </template>
      <v-card>
        
        <div style="padding: 30px 30px 0px 30px;">

          <div>
            <div class="myflex">
              <div class="verti-align" style="font-size:11px;">
                <span>작성자</span> <span>{{taskObj.reg_username}}</span> <span> 작성일</span> <span>{{taskObj.register_date.substring(0,10)}}</span>
              </div>
              
              <div class="grow-end">
                <v-icon style="margin-right:5px;">more_vert</v-icon>
                <v-icon @click="closeModal">close</v-icon>
              </div>
            </div>

            <TaskTitle :taskTitle="taskObj.title"></TaskTitle>
            
          </div>
        </div>
          
            <v-tabs
            v-model="tab"
            grow
            color="#00bcd4"
            @change="click"
            >
                <v-tab >상세</v-tab>
                <v-tab >코멘트</v-tab>
                <v-tab>첨부파일</v-tab>
            </v-tabs>
            <TaskDetails :tab="tab" :taskObj="taskObj" ></TaskDetails>
        <!-- <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
        </v-card-actions> -->
      </v-card>
    </v-dialog>
  
  </div>
</template>
<script>
import TaskDetails from './TaskDetails'
import TaskTitle from './TaskTitle'
 export default {
    name: 'TaskModal',
    props:['taskObj'],
    components:{TaskDetails,TaskTitle},
     data() {
      return {
            dialog: false,
            tab:'',
            taskTitle:'스크립트 수정',
            enableTaskTitleInput:false
      }
    },
    methods: {
      closeModal:function(){
        this.dialog=false
      },
      click:function(){
        console.log(this.tab)
      },
      // saveTaskTitle:function(){
      //   this.enableTaskTitleInput = false
      // },
      // visibleTaskTitleEditIcon:function(){
      //   this.$nextTick(() => {
      //         let el = document.getElementsByClassName("task-title-icon-pen")[0]
      //         if(el!=null){
      //           el.style.visibility = 'visible'
                
      //         }
      //   })
      // },
      // hiddenTaskTitleEditIcon:function(){
      //   this.$nextTick(() => {
      //         let el = document.getElementsByClassName("task-title-icon-pen")[0]
      //         if(el!=null){
      //           el.style.visibility = 'hidden'
      //         }
      //   })
      // }

    }

 }

</script>
<style lang="scss" >
@import '@/assets/css/taskmodal/taskmodal.scss';
 

</style>
<style>

</style>