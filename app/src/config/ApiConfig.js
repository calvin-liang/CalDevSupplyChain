const apiVersion = process.env.REACT_APP_API_VERSION
const protocol = window && window.location && window.location.protocol
const hostname = window && window.location && window.location.hostname || process.env.REACT_APP_API_LOCAL_HOST

let apiRoot;

if(hostname === process.env.REACT_APP_API_LOCAL_HOST){
  apiRoot = `${protocol}//${process.env.REACT_APP_API_LOCAL_SERVER_URL}/api/${apiVersion}`
}
else {
  apiRoot = `${protocol}//${hostname}/api/${apiVersion}`
}
export const API_ROOT = apiRoot
