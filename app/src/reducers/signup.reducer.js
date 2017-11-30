import { userConstants } from '../constants';

export function signup(state = {}, action) {
  switch (action.type) {
    case userConstants.SIGNUP_REQUEST:
      return {
        signing: true
      }
    case userConstants.SIGNUP_SUCCESS:
      return {
          success: true,
          user: action.user,
       }
     case userConstants.RESET_SUCCESS_SIGNUP:
       return {
          signupCompleted: true,
          user: action.user,
        }
    case userConstants.RESET_SIGNUP_FORM:
      return {}
    default:
      return state
  }
}
