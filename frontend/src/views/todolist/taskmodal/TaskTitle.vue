<template>
    
              <!-- task title edit -->
              <input v-if="enableTaskTitleInput" @keydown.enter.exact="saveTaskTitle" class="task-title-editor" type="text" v-model="taskTitle"/>
              <!-- task title edit end -->

              <!-- task title pannel -->
              <div v-else class="task-title-pannel" 
                @click="enableTaskTitleInput=true" 
                
                >

                

                <h4 class="task-title-content" v-if="taskTitle==null||taskTitle==''">
                  taskTitle을 입력해주세요.
                </h4>

                <h4 class="task-title-content" v-else>
                  {{ taskTitle }}
                </h4>

                
                <v-icon class="task-title-icon-pen">edit</v-icon>
              </div>
            <!-- task title pannel end -->
        
</template>
<script>
export default {
    name: 'TaskTitle',
    props:['taskTitle'],
    data() {
      return {
            enableTaskTitleInput:false
      }
    },
    methods:{
        saveTaskTitle:function(){
            this.enableTaskTitleInput = false
        },
     
    }
    

}
</script>

