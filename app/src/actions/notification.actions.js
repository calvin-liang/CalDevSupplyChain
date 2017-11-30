import { notificationConstant } from '../constants';
import { loaderActions }  from './loader.actions'

export const notificationActions = {
    sendSignupEmailActivation,
};

function sendSignupEmailActivation() {
    return dispatch => {
      dispatch(loaderActions.load())
      setTimeout(() => {
        dispatch(loaderActions.clear())
        dispatch(success())
      }, 3000)
      setTimeout(() => {
        dispatch(reset())
      }, 5000)

    }

    // function request() { return { type: notificationConstant.SIGNUP_EMAIL_ACTIVATION_REQUEST }}
    function success() { return { type: notificationConstant.SIGNUP_EMAIL_ACTIVATION_SENT }}
    function reset() { return { type: notificationConstant.RESET_SIGNUP_EMAIL_ACTIVATION }}
}

// function clear() { return { type: notificationConstant.RESET_SIGNUP_EMAIL_ACTIVATION }}
