import React, { Component } from 'react'
import HomePage from './HomePage'
import WelcomePage from './WelcomePage'
import ActivateAccountProcess from '../process/ActivateAccountProcess'
import { Switch, Route } from 'react-router-dom'
import * as AccountAPI from '../api/AccountAPI'

class App extends Component {

  state = {
    user: null,
    apiToken: null,
  }

  handleSetupUserInfo = (user) => {
    console.log("App.js method- in handle setup user info: ", user);
    this.setState({user})
  }

  handleSetupApiToken = (token) => {
    this.setState({apiToken: token})
  }

  render() {

    const {user} = this.state

    return (
        <Switch>
          <Route exact path="/" render={() =>
              <HomePage
                user={user}
                onSetupUserInfo={this.handleSetupUserInfo}
              />
          }/>
          <Route path="/welcomePage" render={() =>
            <WelcomePage
              user={user}
            />
          }/>
          <ActivateAccountProcess
            path="/activating/:token"
            successRedirectTo="/welcomePage"
            failRedirectTo="/"
            onSetupUserInfo={this.handleSetupUserInfo}
            onSetupApiToken={this.handleSetupApiToken}
          />
        </Switch>
    )
  }
}

export default App
