export default {
  channelUsers: [],
  taskBoard: [],
  syncSignal: {
    syncChannel: false,
    syncChannelUser: false,
  },
  currentChannel: {},
  channelList: [],
  subscribeList: [],
  stompClient: null,
  selectComponent: 'main',
  oldComponent: 'main',
  currentUser: {},
  userList: [],
  currentChannelUser: [],
  isLActive: false,
  isRActive: false,
  isfocus: true,
  isLogout: false,
  isSearchMode: false,
  isInviteMode: false,
  searchText: '',
  isSmallWidth:false,
  isCreateListActive : false,
  msgArray: [],
  isVideoMode: false,
  channelModal:false,
  inviteUserList:[],
  lang:'ko',
  channelFiles: [],
  fileCursorPoint:{
    channel_id: 0,
    first: true,
    cursorId: 0,
    empty: false
  },

  // ===========================================
  isFileUpload: false,
  progressValue: 0,
  sendMail: false,
  tempImg: '',
  stringByteLength: 0,
  previewObj: {
    content: '',
    username: ''
  },
  channelArr: [],
  message: {
    channel_id: 0,
    content: '',
    sender: '',
    user: {}
  },
  cursorPoint: {
    channel_id: 0,
    first: true,
    cursorId: 0,
    empty: false
  },
  oldScrollHeight: 0,
  wrapperEl: null,
  isGetMsgForPreview: false,
  isGetMsgForImgLoad: false,
  selectedUserEmail: '',
  firstLoad:true,
  scrollPosition:0,
  isUpScroll:false
}
