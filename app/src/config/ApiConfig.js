const apiVersion = process.env.REACT_APP_API_VERSION
const protocol = window && window.location && window.location.protocol
const hostname = window && window.location && window.location.hostname || process.env.REACT_APP_API_LOCAL_HOST

let apiRoot;

console.log("protocol: ", protocol)

if(hostname === process.env.REACT_APP_API_LOCAL_HOST){

  apiRoot = `${protocol}//${hostname}:${process.env.REACT_APP_API_LOCAL_SERVER_PORT}/api/${apiVersion}`
}
else {
  apiRoot = `${protocol}//${hostname}/api/${apiVersion}`
}

export const API_ROOT = apiRoot
