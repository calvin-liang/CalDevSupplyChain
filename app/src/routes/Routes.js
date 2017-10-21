import React, { Component } from 'react'
import {Route, Redirect } from 'react-router'
import * as AccountAPI from '../api/AccountAPI'
//
const activateAccountAuth = {
  isAuthenticated: false,
  authenticate() {
    this.isAuthenticated = true
  }
}

export const ActivateAccountRoute = ({successDirectTo, failRedirectTo, ...rest }) => {

  const {onSetupUserInfo, onSetupApiToken} = rest
  const {token} = rest.computedMatch.params

  AccountAPI.activateAccount(token)
    .then(res => {
      onSetupUserInfo(res.data)
      onSetupApiToken(token)
      // activateAccountAuth.authenticate()
    })
    .catch(err => console.log(err))

  if(!activateAccountAuth.isAuthenticated){
    return <div>Checking activation...</div>
  }

  console.log("is it? : ", activateAccountAuth.isAuthenticated);

  return <Route {...rest} render={props =>
    activateAccountAuth.isAuthenticated
    ? <Component {...props}/>
    : <Redirect to={{
        pathname: failRedirectTo,
        state: { from: props.location }
      }}/>
  }/>
}
