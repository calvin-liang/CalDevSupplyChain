import axios from 'axios'
import { API_ROOT } from '../config/ApiConfig'


// const headers = {
//   'Accept': 'application/json',
//   'Authorization': token
// }
let username = "ngchwanlii@gmail.com"
let password = "jayngpass"


const headers = new Headers();
headers.append('Authorization', 'Basic ' + window.btoa(username + ":" + password));

export const getAllUsers = () => {
  return axios.get(`${API_ROOT}/account/users`)
}

export const signup = (signUpFormInput) => {
  return axios.post(`${API_ROOT}/account/signup`, signUpFormInput)
}

export const activateAccount = (token) => {
  return axios.get(`${API_ROOT}/account/activate/${token}`)
}

export const login = (loginData) =>
fetch(`${API_ROOT}/account/issue-token`, {
  method: 'POST',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  }
  // headers: {
  //   'Accept': 'application/json',
  //   'Content-Type': 'application/json',
  //   'Authorization': token
  // }
})
.then(res => res.json())
.then(data => console.log(data))









  // let config = axios.create({
  //   auth: {
  //     username: loginData.username,
  //     password: loginData.password
  //   },
  //   headers: {
  //     // "Access-Control-Allow-Origin": "*",
  //     // "Access-Control-Allow-Credentials":"true",
  //     "Content-Type": "application/json"
  //   },
  //   // withCredentials: false,
  // })

  // return axios.post(`${API_ROOT}/account/issue-token`, config)

  /* partially working one */
  // return axios({
  //   method: 'post',
  //   url: `${API_ROOT}/account/issue-token`,
  //   auth: {
  //     username: loginData.username,
  //     password: loginData.password
  //   },
  //   headers: {
  //     'Accept': 'application/json',
  //     "Content-Type": "application/json"
  //   },
  //
  // })

  // return axios.post(`${API_ROOT}/account/issue-token`, "", {
  //     auth: {
  //       username: loginData.username,
  //       // password: loginData.password
  //       // username: "asd",
  //       password: loginData.password
  //     },
  //     headers: {
  //       "Accept": "application/json",
  //       "Content-Type": "application/json",
  //       // "Access-Control-Allow-Origin": "*",
  //       // "Access-Control-Allow-Headers": "Authorization",
  //     },
  //     // withCredentials: true,
  //   }
  // )


  // return axios.post
