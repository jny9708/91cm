export default {
  channelUsers: [],
  taskBoard: [],
  syncSignal: {
    syncChannel: false,
    syncChannelUser: false,
  },
  currentChannel: {},
  userChannelList: [],
  stompClient: null,
  selectComponent: 'main',
  oldComponent: '',
  currentUser: {},
  userList: [],
  currentChannelUser: [],
  isLActive: false,
  isRActive: false,
  isfocus: true,
  isLogout: false,
  isSearchMode: false,
  isInviteMode: false,
  searchText: ''
}