export default {
    namespaced: true,
    state: {
        count: 0,
        show: false,
        message: '',
        popupmessage: '',
        showpopup: false
    },
    mutations: {
        ADD_COUNT(state){
            state.count++;
        },
        MINUS_COUNT(state){
            state.count--;
        },
        SHOW_MODAL(state,payload) {
            state.showpopup=true;
            state.popupmessage=payload;
        },
        DISSHOW_MODAL(state) {
            state.showpopup=false;
            state.popupmessage='';
        }
    },
    getters: {
        getCount (state) {
            if(state.count===0){
                state.message='값이 없습니다. 값을 변경해주세요.';
                state.show=false;
            }else{
                state.message='';
                state.show=true;
            }
            return state;
        }
    }
}