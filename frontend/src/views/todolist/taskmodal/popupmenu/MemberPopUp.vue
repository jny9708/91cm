<template>

<v-menu 
      v-model="menu"
      :close-on-content-click="false"
      :nudge-width="200"
      offset-x
      max-width="350"
      min-width="350"
    >
     <template v-slot:activator="{ on, attrs }">
        
            <button v-if="assignee==null" class="task-add-button" @click="activeMenu" v-bind="attrs" v-on="on">
                <v-icon class="task-icon-add">add</v-icon>
            </button>     

            <v-chip v-else
                close
                v-bind="attrs" v-on="on"
                        color="#e6e8ec"
                        label
                        text-color="black"
                        @click="activeMenu"
                        @click:close="deleteAssginedMember"
                        >
                        {{ assignee_name }}
            </v-chip>    
        </template>
         <v-card>
            <div class="loca-title loca-title-wrap">담당자</div>
            
            
            <v-card-text class="loca-card-text">  
                <div>
                    <div class="search-box"></div>
                    <div class="">
                        <!-- <div class="loca-list-item-wrap" 
                        v-for="(member,index) in testlist" :key="index"  :class="{'select':addSelectClass(member)}"
                        @click="selectAssignedMember(member)">
                            <span class="myflex-grow verti-align">{{member.name}}</span>
                            <v-icon >check</v-icon>
                        </div> -->
                    </div>
                </div>  
                

            </v-card-text>
            <div  class="popup-btn-wrapper">
                <v-btn text @click="menu = false">Cancel</v-btn>
                <v-btn color="primary" text @click="saveAssignedMember">Save</v-btn>
            </div>
        </v-card>
    </v-menu>
</template>

<script>
export default {
    name:'MemberPopUp',
    props:['assignee_name','assignee'],
    computed:{
    
    },
    methods:{
         addSelectClass:function(member){
            return this.testobj.member.filter(mem=>{
                return mem.id == member.id
            }).length>0
        },
        activeMenu:function(){
            this.menu=true
        },
        deleteAssginedMember:function(){

        },selectAssignedMember:function(mem){
            this.testobj.member.push(mem)
        },
        saveAssignedMember:function(){
            // axios...
        }
    },
    data(){
        return{
            menu:false,
             testlist:[{
                name:'홍길동1',
                id:1,
            },{
                name:'홍길동2',
                id:2,
            },{
                name:'홍길동3',
                id:3,
            },{
                name:'홍길동4',
                id:4,
            }],
        }
    }
}
</script>