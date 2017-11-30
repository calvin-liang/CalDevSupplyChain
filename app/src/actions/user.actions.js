import { userConstants } from '../constants';
import { history } from '../util';
import * as AccountAPI from '../api/AccountAPI'
import {alertActions}  from './alert.actions'
import {notificationActions}  from './notification.actions'

export const userActions = {
  signup,
  // login,
}

function signup(user) {

  return dispatch => {

    dispatch(request(user))

    AccountAPI.signup(user)
      .then(res => {
        dispatch(success(res.data))
        setTimeout(() => {
            dispatch(notificationActions.sendSignupEmailActivation())
        }, 500)        
        dispatch(successReset(res.data))

      })
      .catch(error => {
        if(error.response && error.response.data){
          dispatch(reset())
          dispatch(alertActions.error(error.response.data.errors))
          setTimeout(() => {
            dispatch(alertActions.clear())
          }, 2000)
        }
      })
  }

  function request(user) { return { type: userConstants.SIGNUP_REQUEST, user }}
  function success(user) { return { type: userConstants.SIGNUP_SUCCESS, user }}
  function successReset(user) { return { type: userConstants.RESET_SUCCESS_SIGNUP, user }}
  function reset() { return { type: userConstants.RESET_SIGNUP_FORM}}
}

// TODO: next
// function login(username, password) {
//     return dispatch => {
//         dispatch(request({ username }));
//
//         userService.login(username, password)
//             .then(
//                 user => {
//                     dispatch(success(user));
//                     history.push('/');
//                 },
//                 error => {
//                     dispatch(failure(error));
//                     dispatch(alertActions.error(error));
//                 }
//             );
//     };
//
//     function request(user) { return { type: userConstants.LOGIN_REQUEST, user } }
//     function success(user) { return { type: userConstants.LOGIN_SUCCESS, user } }
//     function failure(error) { return { type: userConstants.LOGIN_FAILURE, error } }
// }
