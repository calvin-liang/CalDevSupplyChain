import React, { Component } from 'react'
import HomeAppBar from '../components/AppBar/HomeAppBar'
import HomeBanner from '../components/Banner/HomeBanner'
import TokenForm from '../components/Form/TokenForm'
import WelcomePage from './WelcomePage'
import PropTypes from 'prop-types'
import { Route } from 'react-router-dom'
import * as AccountAPI from '../api/AccountAPI'


class HomePage extends Component {
  state = {
    sentToken: false,
  }

  handleTokenProcess = () => {
    this.setState((prevState) =>
      ({sentToken: !prevState.sentToken})
    )
  }

  componentDidMount() {
    // add data that need to fetch from server during startup
  }

  render() {

    const {sentToken} = this.state
    const {user} = this.props

    return (
      <div style={{maxWidth: '100%'}}>
        <HomeAppBar />
        <HomeBanner                  
          onTokenProcess={this.handleTokenProcess}
          onSetupUserInfo={this.props.onSetupUserInfo}
        />
        {sentToken &&
          <TokenForm
            show={sentToken}
            onTokenProcess={this.handleTokenProcess}
          >
          </TokenForm>
        }
      </div>
    )
  }
}

HomePage.propTypes = {
  user: PropTypes.object,
  onTokenProcess: PropTypes.func,
  onSetupUserInfo: PropTypes.func,
}

export default HomePage
