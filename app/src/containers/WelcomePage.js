import React, { Component } from 'react'

class WelcomePage extends Component {
  render() {
    return (
      <div>
        <h1>{`Welcome Page, Hello ${this.props.user.username}`}</h1>
      </div>
    )
  }
}

export default WelcomePage
