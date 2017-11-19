import React, { Component } from 'react'
import Button from 'material-ui/Button'
import PropTypes from 'prop-types'
import { blue } from 'material-ui/colors'
import { withStyles } from 'material-ui/styles'
import * as AccountAPI from '../api/AccountAPI'

const styles = theme => ({
  loginButton: {
    color: 'white',
    background: blue[500],
    borderColor: 'black',
    fontWeight: 400,
    '&:hover': {
      background: blue[500]
    },
    width: "10%",
    height: 50,
    padding: "10",
    margin: "auto",
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
});

class LoginPage extends Component {

  state = {
    username: 'ngchwanlii@gmail.com',
    password: 'jayngpass'
  }

  handleLoginForm = event => {
    // console.log("clicked login");
    let data = {...this.state}

    AccountAPI.login(data)
    .then(res => {
        // console.log("got response from server");
        // console.log(res.headers);
        console.log(res);
    })
    .catch(err => {
      console.log("got error");
      console.log(err);
    })
  }


  render() {

    const { classes } = this.props

    return (
      <div>
        <h1>{`Login Page Test`}</h1>
        <Button raised color="primary" className={classes.loginButton} onClick={this.handleLoginForm}>
         Login Button
       </Button>
      </div>

    )
  }
}

LoginPage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(LoginPage)
