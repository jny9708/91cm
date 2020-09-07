<template>
    
              <!-- task title edit -->
              <input v-if="enableTaskTitleInput" @keydown.enter.exact="saveTaskTitle" class="task-title-editor" type="text" v-model="taskTitle"/>
              <!-- task title edit end -->

              <!-- task title pannel -->
              <div v-else class="task-title-pannel" 
                @click="enableTaskTitleInput=true" 
                
                >

                <h4 class="task-title-content"   >
                  {{ taskTitle }}
                </h4>
                
                <v-icon class="task-title-icon-pen">edit</v-icon>
              </div>
            <!-- task title pannel end -->
        
</template>
<script>
export default {
    name: 'TaskTitle',
    data() {
      return {
            taskTitle:'Task Title 입니다.',
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

