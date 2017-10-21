import React, { Component } from 'react'
import HomePage from './HomePage'
import WelcomePage from './WelcomePage'
import ActivateAccountProcess from '../process/ActivateAccountProcess'
// import { ActivateAccountRoute } from '../routes/Routes'
import { Switch, Route } from 'react-router-dom'
import * as AccountAPI from '../api/AccountAPI'
import { BASE_API_URL } from '../constants/UrlLink'

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

    console.log(this.state.user);

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
          {/* WORKING ONE!!!! */}
          <ActivateAccountProcess
            path="/activating/:token"
            successDirectTo="/welcomePage"
            failRedirectTo="/"
            onSetupUserInfo={this.handleSetupUserInfo}
            onSetupApiToken={this.handleSetupApiToken}
          />

          {/* Buggy */}
          {/* <ActivateAccountRoute
            path="/activating/:token"
            onSetupApiToken={this.handleSetupApiToken}
            onSetupUserInfo={this.handleSetupUserInfo}
            // successDirectTo="welcomePage"
            component={WelcomePage}
            failRedirectTo="/"
          /> */}

        </Switch>
    )
  }
}

{/* <Redirect to={`/activating/${uuid}`}/> */}

export default App
