import React, { Component } from 'react'
import axios from 'axios'

import { MuiThemeProvider, createMuiTheme} from 'material-ui/styles';
import {grey, lightBlue, red} from 'material-ui/colors';

import SimpleAppBar from '../components/Bar/HomeBar'

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
      <MuiThemeProvider>
        <SimpleAppBar></SimpleAppBar>
      </MuiThemeProvider>
    )
  }
}

export default App
