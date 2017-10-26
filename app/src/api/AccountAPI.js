import axios from 'axios'
import { API_ROOT } from '../config/ApiConfig'

export const getAllUsers = () => {
  return axios.get(`${API_ROOT}/account/users`)
}

export const signup = (signUpFormInput) => {
  console.log(API_ROOT)
  return axios.post(`${API_ROOT}/account/signup`, signUpFormInput)
}

export const activateAccount = (token) => {
  return axios.get(`${API_ROOT}/account/activate/${token}`)
}
