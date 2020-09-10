<template>
    <v-menu 
      disabled
      v-model="menu"
      :close-on-content-click="false"
      :nudge-width="200"
      offset-x
      max-width="350"
      min-width="350"
    >
        <template v-slot:activator="{ on, attrs }">
        
            <button v-if="taskObj.tasklist_id==null" class="task-add-button" @click="activeMenu" v-bind="attrs" v-on="on">
                <v-icon class="task-icon-add">add</v-icon>
            </button>     

            <v-chip v-else
                    v-bind="attrs" v-on="on"
                            color="#e6e8ec"
                            label
                            text-color="black"
                            @click="activeMenu"
                            >
                            {{ taskObj.channel_name + ' > ' + taskObj.tasklist_name}}
            </v-chip>    
        </template>

        <v-card>
            <div v-if ="view=='locaMain'" class="loca-title loca-title-wrap">위치변경</div>
            <div v-if="view=='locaStepOne'" class="loca-title-wrap myflex">
                <button @click="view='locaMain'"><v-icon>arrow_back_ios</v-icon></button>
                <span class="loca-title verti-align">채널 선택</span>
            </div>
            <div v-if="view=='locaStepTwo'" class="loca-title-wrap myflex">
                <button @click="view='locaMain'"><v-icon>arrow_back_ios</v-icon></button>
                <span class="loca-title verti-align">업무리스트 선택</span>
            </div>
            
            <v-card-text class="loca-card-text">  
                <div v-if="view=='locaMain'">
                    <div  class="loca-btn-wrapper" @click="view='locaStepOne'">
                            <div class="loca-btn-content">
                                <span>채널</span>
                                <span>{{taskLocation.channel.channel_id==null? '채널을 선택해주세요.':taskLocation.channel.name }}</span>
                            </div> 
                            <v-icon>arrow_forward_ios</v-icon>
                    </div>

                    <div class="loca-btn-wrapper" @click="view='locaStepTwo'">
                            <div class="loca-btn-content">
                                <span>업무리스트</span>
                                <span>{{taskLocation.tasklist.tasklist_id==null? '업무리스트를 선택해주세요.':taskLocation.tasklist.name }}</span>
                            </div> 
                            <v-icon>arrow_forward_ios</v-icon>
                    </div>
                </div>    
                <div v-if="view=='locaStepOne'">
                    <div class="search-box"></div>
                    <div class="">
                        <div class="loca-list-item-wrap" :class="{'select':taskLocation.channel.channel_id==channel.channel_id}"
                        v-for="(channel,index) in testlist" :key="index" 
                        @click="selectLocation(channel,'channel')">
                            <span class="myflex-grow verti-align">{{channel.name}}</span>
                            <v-icon >check</v-icon>
                        </div>
                    </div>
                </div>  

                <div v-if="view=='locaStepTwo'">
                    <div class="search-box"></div>
                    <div class="">
                        <div class="loca-list-item-wrap" :class="{'select':taskLocation.tasklist.tasklist_id==tasklist.tasklist_id}"
                        v-for="(tasklist,index) in tastlist2" :key="index" 
                        @click="selectLocation(tasklist,'tasklist')">
                            <span class="myflex-grow verti-align">{{tasklist.name}}</span>
                            <v-icon >check</v-icon>
                        </div>
                    </div>

                </div> 


            </v-card-text>
            <div v-if="view=='locaMain'" class="popup-btn-wrapper">
                <v-btn text @click="menu = false">Cancel</v-btn>
                <v-btn color="primary" text @click="changeLocation">Save</v-btn>
            </div>
        </v-card>
    </v-menu>

</template>
<script>

export default {
    name:'LocationPopup',
    components:{},    
    props:['taskObj'],
    data(){
        return{
            menu:false,
            view:'locaMain',
            tastlist2:[{
                name:'리스트명1',
                tasklist_id:1
            },{
                name:'리스트명2',
                tasklist_id:2
            },{
                name:'리스트명3',
                tasklist_id:3
            },
            {
                name:'리스트명4',
                tasklist_id:4
            },
            ],
            testlist:[{
                name:'채널이름1',
                channel_id:1,
            },{
                name:'채널이름2',
                channel_id:2,
            },{
                name:'채널이름3',
                channel_id:3,
            },{
                name:'채널이름4',
                channel_id:4,
            }],
            taskLocation:{
                channel:{
                    name:null,
                    channel_id:null,
                },
                tasklist:{

                }
            }
        }
    },
    methods:{
        //  taskLocationDelete:function(){

        //     //  axios 통신..
        //     this.testobj.tasklist_id = null
        //     this.testobj.channel_name = null
        //     this.testobj.tasklist_name=null
        // },
        changeLocation:function(){
            
            this.menu=false
        },
        activeMenu:function(){
            this.menu=true
        },
        clickTaskChannel:function(){
            
        },
        selectLocation:function(obj,type){
            if(type=='channel'){
                let channel = obj
                this.taskLocation.channel = channel // 이렇게 해도 반응형 변수로 만들어지나..?
                // this.taskLocation.channel.name = task.name
                // this.taskLocation.channel.channel_id = task.channel_id
            }else if(type=='tasklist'){
                let tasklist = obj
                this.taskLocation.tasklist = tasklist;
            }
            
            this.view='locaMain'
        }
    }

}
</script>