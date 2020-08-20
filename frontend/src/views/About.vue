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

      <button @click = "click">click</button>
      <div class="ql-editor" v-html="quillContent">

      </div>
  </div>
</template>

<script>

import { quillEditor } from 'vue-quill-editor'

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
       quillContent:'',
       editorOption: {
          modules: {
            toolbar: '#toolbar',
            markdownShortcuts: {}
          }
        }
      };
    },
    methods: {
      click:function(){
        console.log(this.quillContent)
        
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
