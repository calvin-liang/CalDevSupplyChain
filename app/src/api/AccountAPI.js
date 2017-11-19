import axios from 'axios'
import base64 from 'base-64'
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

export const login = (loginData) => {
  return axios.post(`${API_ROOT}/account/issue-token`, '', {
    auth: {
      username: loginData.username,
      password: loginData.password
    },
  })
}
