<template>
       <v-card-text style="padding: 20px;max-height: 500px;overflow-y: auto;" v-if="tab==0">
        <div>
            <!-- 설명 에디터 비활성 -->
            <div @click="enableTaskDescEditor=true"
                    v-if="!enableTaskDescEditor"
                    class="task-title-pannel" >
                <span class="task-title-content">설명 추가</span>
                    <v-icon class="task-title-icon-pen">edit</v-icon>   
            </div>
            <!-- 설명 에디터 비활성 끝 -->    
            <!-- 설명 에디터 활성 -->
            <div  v-else >
                <quill-editor :options="editorOption"></quill-editor>
                <div class="task-desc-editor-footer" >
                    <v-btn depressed small dark color="cyan" @click="enableTaskDescEditor=false" >저장</v-btn>
                    <v-btn depressed small @click="enableTaskDescEditor=false" >취소</v-btn>
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
                        <LocationPopUp :testobj="testobj" />
                        
                    </div>    
                </div>

                <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">calendar_today</v-icon>
                        <span>기한</span>
                    </div>
                    <div style="display: flex;">
                        <!-- <DatePopUp></DatePopUp> -->

                        
                    <button v-if="realobj.taskDate.length==0"  class="task-add-button" @click="pickerOpenFunc" >
                        <v-icon class="task-icon-add">add</v-icon>
                    </button>  
                    <div v-else >
                    <v-chip v-for="(date, index) in obj.dateValue" :key="index"
                        close
                        color="#e6e8ec"
                        label
                        text-color="black"
                        @click="pickerOpenFunc"
                        @click:close="taskDateDelete(index)"
                        >
                        {{ date }}
                    </v-chip>    
                    </div>  
                    <!-- <span v-else>{{ visibleDateValue}}</span> -->

                     <date-picker v-model="realobj.taskDate" @change="handleDateChange" :open.sync="pickerOpen"
                      style="visibility: hidden;display:flex;" type="date" range ></date-picker> 

                    </div>    
                    
                </div>

                <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">group</v-icon>
                        <span>담당자</span>
                    </div>
                    <div>
                        <MemberPopUp :testobj="testobj"/>
                    </div>    
                    
                </div>    

                <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">color_lens</v-icon>
                        <span>컬러</span>
                    </div>
                    <div>
                        <v-swatches swatch-size=20 shapes="circles" v-model="testobj.color" @input="seleteTaskColor" inline></v-swatches>
                    </div>    
                </div>

                 <div  class="task-attribute-container">
                    <div class="task-attribute-title">
                        <v-icon class="task-attribute-icon">grade</v-icon>
                        <span>중요도</span>
                    </div>
                    <div>
                        <v-rating
                            v-model="importance"
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
                            v-model="testobj.progressRate"
                            readonly
                            :thumb-size="24"
                            hide-details
                            thumb-label="always">
                            <template v-slot:append>
                                <v-text-field v-if="isProgressRate"
                                    v-model="testobj.progressRate"
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
    props:['tab'],
    mounted(){
        this.$nextTick(()=>{
            document.getElementsByClassName('mx-input')[0].hidden=true
        })

     
        let val1 = this.testobj.start_date
        let val2 = this.testobj.end_date
        let year=val1.substring(0,4)
        let month = val1.substring(5,7)
        let day = val1.substring(8,10)

        let year2=val2.substring(0,4)
        let month2 = val2.substring(5,7)
        let day2 = val2.substring(8,10)

        let date1=new Date(year,month,day)
        let date2=new Date(year2,month2,day2)
        this.realobj.taskDate.push(date1)
        this.realobj.taskDate.push(date2)
        this.$nextTick(()=>{
                this.obj.dateValue.push(document.getElementsByClassName('mx-input')[0].value)
        })
    },
    data(){
        return{
            isProgressRate:false,
            pickerOpen:false,
            importance:0,
            obj:{
                dateValue:[]
            },
            realobj:{
                taskDate:[],
            },
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
            enableTaskDescEditor: false,
            editorOption: {
                modules: {
                    toolbar: [ ['bold', 'italic', 'underline'], [{ 'list': 'ordered'}, { 'list': 'bullet' }],['clean']    ] ,
                    markdownShortcuts: {}
                }
            }
        }
    },methods:{
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
            // this.$nextTick(()=>{
                this.pickerOpen=true
                // console.log(this.realobj.taskDate)
            // })
            
        },
        handleDateChange:function(value, type){
            console.log(this.realobj.taskDate)
            this.obj.dateValue=[]
            this.$nextTick(()=>{
                this.obj.dateValue.push(document.getElementsByClassName('mx-input')[0].value)
                // this.visibleDateValue = document.getElementsByClassName('mx-input')[0].value
            })
            
        },
        taskDateDelete:function(index){
            console.log(index)
            this.obj.dateValue.splice (index,1)
            this.realobj.taskDate = []
        }
        
   
    }
}
</script>
