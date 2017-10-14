import React, { Component } from 'react'
import axios from 'axios'

import HomeAppBar from '../components/AppBar/HomeAppBar'
import SignupForm from '../components/Form/SignupForm'

import {BASE_API_URL} from '../constants/UrlLink'


class App extends Component {
  componentDidMount() {
    // WORKING!
    axios
      .get(`${BASE_API_URL}/account/v1/users`)
      .then(response => {
        console.log(response.data)
      })
      .catch(function(error) {
        console.log(error)
      })
  }

  render() {
    return (
      <div>
        <HomeAppBar></HomeAppBar>
        <SignupForm></SignupForm>
      </div>
    )
  }
}

export default App
