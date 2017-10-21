import axios from 'axios'
import { BASE_API_URL } from '../constants/UrlLink'

export const getAllUsers = () => {
  return axios.get(`${BASE_API_URL}/account/v1/users`)
}

export const signup = (signUpFormInput) => {
  return axios.post(`${BASE_API_URL}/account/v1/signup`, signUpFormInput)
}

export const activateAccount = (token) => {
  return axios.get(`${BASE_API_URL}/account/v1/activate/${token}`)
}
