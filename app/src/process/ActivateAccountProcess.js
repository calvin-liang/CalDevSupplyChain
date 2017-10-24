import React from 'react'
import * as AccountAPI from '../api/AccountAPI'
import { Redirect, Route } from 'react-router-dom'
import WelcomePage from '../containers/WelcomePage'


class ActivateAccountProcess extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      isFetching: true,
      success: false
    };
  }

  componentDidMount(){
    const {onSetupUserInfo, onSetupApiToken} = this.props
    const {token} = this.props.computedMatch.params

    AccountAPI.activateAccount(token)
    .then(res => {
      onSetupApiToken(token)
      onSetupUserInfo(res.data)
      // TODO: change back to true after testing
      this.setState({isFetching: false, success: true})
    })
    .catch(error => {
      this.setState({isFetching: false, success: false})
      console.log(error)
    })
  }

  render() {

    const {successRedirectTo, failRedirectTo} = this.props
    const {isFetching, success} = this.state
    console.log("success status: ", success);

    return (
      <Route {...this.props} render={(props) => {
        if(isFetching){
          return <div>Fetching...</div>
        }
        return success
        ? <Redirect to={{          
          pathname: successRedirectTo
        }}/>
        : <Redirect to={{
          pathname: failRedirectTo
        }}/>
      }}/>
    )
  }
}
export default ActivateAccountProcess
