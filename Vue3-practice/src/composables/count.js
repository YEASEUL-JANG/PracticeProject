import {reactive, toRefs} from "vue";

export const useCount = () => {
    const state = reactive({
        count: 0
    });
    return toRefs(state);//보내는 ref깂을 ref로 바꿔준다.
}
//export default useCount로 배포하면 import 시 중가로 없이 useCount 바로 쓰면되고
//export만 쓰게되면 import { useCount } 로 사용한다.