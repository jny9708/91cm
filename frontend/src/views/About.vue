<template>
  <div>
      <quill-editor 
      v-model="quillContent"
      :options="editorOption"
      ref="myQuillEditor"
      >
        <!-- <quill-editor > -->
        <div id="toolbar" slot="toolbar">
          <span class="ql-formats">
            <select class="ql-size">
              <option value="small"></option>
              <option selected></option>
              <option value="large"></option>
              <option value="huge"></option>
            </select>
          </span>
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
        </div>
      </quill-editor>

      <!-- <button @click = "click">click</button>
      <div class="ql-editor" v-html="quillContent">

      </div> -->
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
    },
    mounted() {
    },
    computed: {},
    data() {
      return {
        bindings :{
          custom: {
            key: 13,
            shiftKey: true,
            handler: function(range, context) {
              console.log(range,'range')
              console.log(context,'context')
            }
          }
        },
        
       quillContent:'',
       editorOption: {
          modules: {
            keyboard: {
              bindings:{
                custom: {
                  key: 13,
                  name:'my',
                  handler: function(range, context) {
                    console.log(range)
                    console.log(context)
                  }
                }
              }
            },
            toolbar: [
              [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
              ['bold', 'italic', 'underline', 'strike'],
               [{ 'color': [] }, { 'background': [] }],  
               [{ 'list': 'ordered'}, { 'list': 'bullet' }],
                ['clean']    
              ],
            markdownShortcuts: {}
          }
        }
      };
    },
    methods: {
      click:function(){
        console.log(this.quillContent)
        console.log(CommonClass.byteCount(this.quillContent))
        // var delta = .getContents();
        // console.log(delta);
      }
    }
  }

</script>
<style scoped>
  .ql-custom-button {
    width: 100px;
  }
</style>
