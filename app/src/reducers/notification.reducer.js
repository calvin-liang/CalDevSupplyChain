import { notificationConstant } from '../constants';

export function notification(state = {}, action) {
  switch (action.type) {
    case notificationConstant.SIGNUP_EMAIL_ACTIVATION_SENT:
      return {
        successSentEmailActivation: true
      }
    case notificationConstant.RESET_SIGNUP_EMAIL_ACTIVATION:
      return {}
    default:
      return state
  }
}
