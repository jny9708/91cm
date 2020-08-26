<template>
  <div>
      <quill-editor 
      :options="editorOption"
      ref="myQuillEditor"
      >
        <!-- <quill-editor > -->
        <div id="toolbar" slot="toolbar" class="verti-align">
          <span class="ql-formats">          
            <button class="ql-bold"></button>
            <button class="ql-italic"></button>
            <button class="ql-underline"></button>
            <button class="ql-link"></button>
          </span>
          <span class="ql-formats">
            <button class="ql-list" value="ordered"></button>
            <button class="ql-list" value="bullet"></button>
          </span>

          <span class="ql-formats">
            <button class="ql-clean"></button>
          </span>

          <v-menu
            v-model="menu"
            :close-on-content-click="false"
            :nudge-width="200"
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <span  v-bind="attrs"
                v-on="on" style="cursor:pointer; display:inline-block;" class="dropdown-toggle" href="#" id="moreDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><i class="ik ik-more-horizontal"></i></span>
            </template>

            <v-card>
              <v-list style="padding:0px;">
                
                <v-list-item style="display: flex;flex-wrap: wrap;">

                  <label for="file-input" style="display: flex;margin-bottom: 0;">
                  <div class="toolbar-menu-icon">
                      <v-icon color="#2C3E50" >note_add</v-icon>
                  </div>
                  </label>
                  <input id="file-input" type="file" ref="fileInput" multiple @change="attachFile"  hidden/>
                  
                  <div class="toolbar-menu-icon">
                    <v-icon color="#2C3E50" @click="searchModeKeyHandler" >search</v-icon>
                  </div>

                  <div class="toolbar-menu-icon">
                  <v-icon color="#2C3E50"  @click="inviteModeKeyHandler" >people_alt</v-icon>
                  </div>

                  <div class="toolbar-menu-icon">
                  <v-icon color="#2C3E50" v-bind:class="{'active-m': sendMail}"
                        @click="sendMailToggle" >mail</v-icon>
                  </div>
                  
                  <div class="toolbar-menu-icon">
                  <v-icon color="#2C3E50" v-bind:class="{'active-m': translate}" @click="translateToggle">translate</v-icon>
                  </div>

                
                  
                </v-list-item>
              </v-list>
            </v-card>
          </v-menu>
        </div>
      </quill-editor>

  </div>
</template>

<script>

import { quillEditor } from 'vue-quill-editor'
import CommonClass from '../service/common'
  export default {
    name: 'About',
    components: {
      quillEditor
    },
    created() {
      this.editorOption.modules.keyboard.bindings.simpleEnter.handler = this.simpleEnterHandler;
      this.editorOption.modules.keyboard.bindings.searchModeKey.handler = this.searchModeKeyHandler
      this.editorOption.modules.keyboard.bindings.inviteModeKey.handler = this.inviteModeKeyHandler
    },
    mounted() {
    },
    computed: {},
    data() {
      return {
        menu:false,
       editorOption: {
          modules: {
            keyboard: {
              bindings:{
                simpleEnter: {
                  key: 13,
                  name:'my',
                  handler: null
                },
                searchModeKey:{
                  key: 70,
                  shiftKey: true,
                  ctrlKey:true,
                  handler: null
                },
                inviteModeKey:{
                  key:50,
                  shiftKey: true,
                  altKey: true,
                  handler: null
                },
                

              }
            },
            toolbar: '#toolbar' ,
            markdownShortcuts: {}
          }
        }
      };
    },
    methods: {
      simpleEnterHandler:function(range, context){   
        
        let el = document.getElementsByClassName("quill-editor")[0].getElementsByClassName("ql-editor")[0] 
        
        console.log(el);
        if(!el.innerText.trim() == ''){
          this.message.content = el.innerHTML
          this.sendMessage()
          this.$refs.myQuillEditor.quill.setContents([])
        }
        
      },
      scrollToEnd:function(bool){
        this.$emit('scrollToEnd',bool);
      },
      searchModeKeyHandler:function(range,context){
        this.$store.state.isSearchMode = !this.$store.state.isSearchMode
        this.$store.state.isInviteMode = false
      },
      inviteModeKeyHandler:function(range,context){
        this.$emit('inviteToggle');
      },
      translateToggle: function () {
        this.translate = !this.translate
        if (this.translate) {
          this.$_alert('지금부터 보내는 메시지는 번역 내용과 같이 보내집니다.')
        }
      },
       sendMailToggle() {
        this.sendMail = !this.sendMail
        if (this.sendMail) {
          this.$_alert('지금부터 보내는 메시지는' + this.$store.state.currentChannel.name + ' 채널 사용자들에게 ' + '메일로 보내집니다.')
        }
      },
      attachFile(e){
        this.$emit('addFile',e.target.files);
         this.$refs.fileInput.value = null
      }
      
    }
  }

</script>
<style scoped>
  
  .ql-custom-button {
    width: 100px;
  }

</style>

<style>

  .ql-container{
    max-height: 101px !important;
    overflow: auto !important;
  }
  .toolbar-menu-icon{
    padding:5px;
    cursor:pointer;
  }
   .active-m{
     color: #FF4848 !important;
   }
  .icon-list{
    color: #2C3E50 !important;
  }
  

  .ql-snow .ql-editor pre{
    font-family: Monaco,Menlo,Consolas,Courier New,monospace!important;
  }

</style>

