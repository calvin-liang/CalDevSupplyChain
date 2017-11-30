import { combineReducers } from 'redux';
import { alert } from './alert.reducer';
import { signup } from './signup.reducer';
import { notification } from './notification.reducer';
import { loader } from './loader.reducer';

const appReducer = combineReducers({
  signup,
  alert,
  loader,
  notification,
});

const rootReducer = (state, action) => {
  if (action.type === 'USER_LOGOUT') {
     state = undefined
  }
  return appReducer(state, action)
}

export default rootReducer;
