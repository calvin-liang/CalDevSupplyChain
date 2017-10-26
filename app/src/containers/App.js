import React, { Component } from 'react'
import HomePage from './HomePage'
import LoginPage from './LoginPage'
import MyOrderPage from './MyOrderPage'
// import { ActivateAccountRoute } from '../routes/Routes'
import { Switch, Route } from 'react-router-dom'
import * as AccountAPI from '../api/AccountAPI'
import { BASE_API_URL } from '../constants/UrlLink'

class App extends Component {


    render() {
      return (
          <Switch>
            <Route exact path="/" render={() =>
                <HomePage
                    // user={user}
                    // onSetupUserInfo={this.handleSetupUserInfo}
                />
            }/>
            <Route path="/loginPage" render={() =>
                <LoginPage
                    // user={user}
                />
            }/>
              <Route path="/myorderPage" render={() =>
                  <MyOrderPage
                      // user={user}
                      // onSetupUserInfo={this.handleSetupUserInfo}
                  />
              }/>
              {/* WORKING ONE!!!! */}

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

export default App
