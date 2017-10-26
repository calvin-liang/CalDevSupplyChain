import React, { Component } from 'react'
import HomeAppBar from '../components/AppBar/HomeAppBar'
import HomeBanner from '../components/Banner/HomeBanner'
import TokenForm from '../components/Form/TokenForm'
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

    return (
      <div style={{maxWidth: '100%'}}>
        <HomeAppBar />
        <HomeBanner onTokenProcess={this.handleTokenProcess}/>
        {sentToken &&
          <TokenForm
            show={sentToken}
            onTokenProcess={this.handleTokenProcess}
          >
          </TokenForm>
        }
        {/*<p>ADD SOMETHING HERE AND MAKE IT LOOK NICE!!</p>*/}
        {/*<p>Reference: https://www.tradegecko.com/supply-chain-management-software</p>*/}
        {/*<p>I'm going to my cybersecurity project. Good luck guys :)</p>*/}
      </div>
    )
  }
}

export default HomePage
