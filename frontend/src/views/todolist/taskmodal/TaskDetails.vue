<template>
       <v-card-text style="padding: 20px;max-height: 500px;overflow-y: auto;" v-if="tab==0">
        <div>
            <!-- 설명 에디터 비활성 -->
            <div @click="enableTaskDescEditor"
                    v-show="!isTaskDescEditor"
                    class="task-title-pannel" >
                <span class="task-title-content">
                    <span v-if="taskObj.content==''||taskObj.content==null">설명 추가</span>
                    <span v-else v-html="taskObj.content"></span>
                </span>
                <v-icon class="task-title-icon-pen">edit</v-icon>   
            </div>
            <!-- 설명 에디터 비활성 끝 -->    
            <!-- 설명 에디터 활성 -->
            <div  v-show="isTaskDescEditor" >
                <quill-editor ref="taskDescEditor" :options="editorOption"></quill-editor>
                <div class="task-desc-editor-footer" >
                    <v-btn depressed small dark color="cyan" @click="saveTaskDesc" >저장</v-btn>
                    <v-btn depressed small @click="isTaskDescEditor=false" >취소</v-btn>
                </div>
            </div>
            <!-- 설명 에디터 활성 끝 -->
           </div>
                <hr/>
                <!-- 실제데이터로 구현할때는 v-if 잘 살펴보고 수정해야함 지금은 임시로 설정해둠 -->
                <div class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">list_alt</v-icon>
                        <span>위치</span>
                    </div>
                    <div>
                        <!-- <button class="task-add-button">
                            <v-icon class="task-icon-add">add</v-icon>
                        </button> -->
                        <LocationPopUp :taskObj="taskObj" />
                        
                    </div>    
                </div>

                <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">calendar_today</v-icon>
                        <span>기한</span>
                    </div>
                    <div style="display: flex;">
                        <!-- <DatePopUp></DatePopUp> -->

                        
                    <button v-if="taskObj.start_date==null&&taskObj.end_date==null"  class="task-add-button" @click="pickerOpenFunc" >
                        <v-icon class="task-icon-add">add</v-icon>
                    </button>  
                    <div v-else >
                    <v-chip 
                        close
                        color="#e6e8ec"
                        label
                        text-color="black"
                        @click="pickerOpenFunc"
                        @click:close="taskDateDelete($event)"
                        >
                        {{ obj.dateValue }}
                    </v-chip>    
                    </div>  
                    <!-- <span v-else>{{ visibleDateValue}}</span> -->

                     <date-picker v-model="taskDate" @change="handleDateChange" :open.sync="pickerOpen"
                      style="visibility: hidden;display:flex;" type="date" range ></date-picker> 

                    </div>    
                    
                </div>

                <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">group</v-icon>
                        <span>담당자</span>
                    </div>
                    <div>
                        <MemberPopUp :assignee="taskObj.assignee" :assignee_name="taskObj.assignee_name"/>
                    </div>    
                    
                </div>    

                <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">color_lens</v-icon>
                        <span>컬러</span>
                    </div>
                    <div>
                        <v-swatches swatch-size=20 shapes="circles" v-model="taskObj.color" @input="seleteTaskColor" inline></v-swatches>
                    </div>    
                </div>

                 <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">grade</v-icon>
                        <span>중요도</span>
                    </div>
                    <div>
                        <v-rating
                            v-model="taskObj.importance"
                            background-color="purple lighten-3"
                            color="purple"
                            size="22"
                        >
                        </v-rating>
                    </div>    
                </div> 

                   <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">donut_large</v-icon>
                        <span>진행률</span>
                    </div>
                    <div>
                         <v-col cols="12">
                             <div @click="activeRateEditor" :class="{'progress-rate':!isProgressRate}">
                            <v-slider
                            v-model="taskObj.prog_rate"
                            readonly
                            :thumb-size="24"
                            hide-details
                            thumb-label="always">
                            <template v-slot:append>
                                <v-text-field v-if="isProgressRate"
                                    v-model="taskObj.prog_rate"
                                    autofocus
                                    @focusout="handleRateFocusOut"
                                    class="mt-0 pt-0"
                                    hide-details
                                    single-line
                                    type="number"
                                    style="width: 60px"
                                ></v-text-field>
                            </template>
                            </v-slider>
                            </div>
                        </v-col>
                    </div>    
                </div> 

                 </v-card-text>
</template>
<script>
import { quillEditor } from 'vue-quill-editor'
import LocationPopUp from './popupmenu/LocationPopUp'
import VSwatches from 'vue-swatches'
  import 'vue-swatches/dist/vue-swatches.css'
 import DatePicker from "vue2-datepicker";
 import MemberPopUp from './popupmenu/MemberPopUp'
export default {
    name:'TaskDetails',
    components:{ quillEditor,DatePicker,VSwatches,LocationPopUp,MemberPopUp},
    props:['tab','taskObj'],
    watch:{
        taskObj:function(){
        //     this.taskDate = []
        //     console.log(this.taskObj.start_date,'wa')
        //     console.log(this.taskObj.end_date,'wa')
        //    this.taskDate.push(this.replaceDate(this.taskObj.start_date))
        //    this.taskDate.push(this.replaceDate(this.taskObj.end_date))
        //     this.$nextTick(()=>{
        //         console.log(this.taskDate)
        //             this.obj.dateValue=document.getElementsByClassName('mx-input')[0].value
        //     })
        }
    },
    mounted(){
            this.taskDate = []
            this.taskDate.push(this.replaceDate(this.taskObj.start_date))
            this.taskDate.push(this.replaceDate(this.taskObj.end_date))
            this.$nextTick(()=>{
                    console.log(this.taskDate)
                    this.obj.dateValue=document.getElementsByClassName('mx-input')[0].value
            })
        this.$nextTick(()=>{
            document.getElementsByClassName('mx-input')[0].hidden=true
        })
    },
    data(){
        return{
            isProgressRate:false,
            pickerOpen:false,
            importance:0,
            obj:{
                dateValue:''  // v-chip 
            },
            taskDate:[], // datepicker v-model
            testobj:{
                channel_name:'채널이름',
                tasklist_name:'리스트이름',
                channel_id:null,
                id:null,
                tasklist_id:1,
                content:null,
                register_date:null,
                state:null,
                start_date:'2020-09-03T07:02:16.000+0000',
                end_date:'2020-09-24T07:02:16.000+0000',
                color:'#8E43AD',
                title:null,
                member:[{id:1,name:'홍길동'}],
                progressRate:20
            },
            visibleDateValue:'',
            
            menu:false,
            isTaskDescEditor: false,
            editorOption: {
                modules: {
                    toolbar: [ ['bold', 'italic', 'underline'], [{ 'list': 'ordered'}, { 'list': 'bullet' }],['clean']    ] ,
                    markdownShortcuts: {}
                }
            }
        }
    },methods:{
        saveTaskDesc:function(){
            this.taskObj.content =  this.$refs.taskDescEditor.quill.root.innerHTML
            this.$http.post('/api/task/update/content', this.taskObj).then(res => {
                    this.isTaskDescEditor=false
                    this.$store.state.stompClient.send('/sub/todo/' + this.$store.state.currentChannel.id, null, {typename: 'taskUpdate'})
                }).catch(error => {
                    console.error(error)
                })
        },
        enableTaskDescEditor:function(){
            this.isTaskDescEditor = true
            this.$refs.taskDescEditor.quill.clipboard.dangerouslyPasteHTML(this.taskObj.content);
        },
        replaceDate:function(date){
            return new Date(date)
        },
        handleRateFocusOut:function(){
           this.isProgressRate = false
        },
       activeRateEditor:function(){
           this.isProgressRate = true
       },
        seleteTaskColor:function(val){
            console.log(val)
        },
        pickerOpenFunc:function(){
            this.pickerOpen=true
        },
        handleDateChange:function(value, type){
            console.log(this.taskDate)
            this.obj.dateValue=''
            let date
            this.$nextTick(()=>{
                date = document.getElementsByClassName('mx-input')[0].value
                this.taskObj.start_date = this.taskDate[0]
                this.taskObj.end_date = this.taskDate[1]
                this.$http.post('/api/task/update/deadline', this.taskObj)
                .then(res => {
                    this.obj.dateValue=date
                    this.$store.state.stompClient.send('/sub/todo/' + this.$store.state.currentChannel.id, null, {typename: 'taskUpdate'})
                }).catch(error => {
                    console.error(error)
                })
            })
        },
        taskDateDelete:function(index){
            console.log(index)
            this.obj.dateValue=''
            this.taskDate = []
            this.taskObj.start_date = this.taskDate[0]
            this.taskObj.end_date = this.taskDate[1]
            // 나중에 trigger 활용하려면 api 분기해야 할 듯
               // 일단 임시로 이렇게 해놓기.. 
                this.$http.post('/api/task/update/deadline', this.taskObj)
                .then(res => {
                    this.obj.dateValue=date
                    this.$store.state.stompClient.send('/sub/todo/' + this.$store.state.currentChannel.id, null, {typename: 'taskUpdate'})
                }).catch(error => {
                    console.error(error)
                })

        }
        
   
    }
}
</script>
