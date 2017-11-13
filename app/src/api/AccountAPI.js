import axios from 'axios'
import { API_ROOT } from '../config/ApiConfig'

export const getAllUsers = () => {
  return axios.get(`${API_ROOT}/account/users`)
}

export const signup = (signUpFormInput) => {
  return axios.post(`${API_ROOT}/account/signup`, signUpFormInput)
}

export const activateAccount = (token) => {
  return axios.get(`${API_ROOT}/account/activate/${token}`)
}

// TODO 更改登陆接口
export const checkLogin = (userInfo) => {
    return axios.post(`${API_ROOT}/account/issue-token`, userInfo)
}