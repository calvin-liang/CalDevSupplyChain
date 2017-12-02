
import axios from 'axios'

export const authUtil = {
  setAuthorizationJwtHeader,
  parseAuthJwtHeaderContent,
}

function setAuthorizationJwtHeader() {
    let user = JSON.parse(localStorage.getItem('user'));
    if (user && user.jwtToken) {
      // set authorization header with jwt token
      axios.defaults.headers.common['Authorization'] = `Bearer ${user.jwtToken}`
    } else {
        delete axios.defaults.headers.common['Authorization']
    }
}

function parseAuthJwtHeaderContent (headerContent) {
  return headerContent.replace('Bearer ', '')
}
