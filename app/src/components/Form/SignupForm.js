import React from 'react'
import PropTypes from 'prop-types'
import { withStyles } from 'material-ui/styles'
import TextField from 'material-ui/TextField'
import Typography from 'material-ui/Typography'
import { blue, red, grey, blueGrey } from 'material-ui/colors'
import Paper from 'material-ui/Paper'
import Button from 'material-ui/Button'
import * as AccountAPI from '../../api/AccountAPI'
import TokenForm from './TokenForm'
import emailMask from 'text-mask-addons/dist/emailMask'
import Icon from 'material-ui/Icon'

const styles = theme => ({
  root: {
    display: 'flex',
    paddingTop: 50,
    paddingLeft: 100,
    paddingBottom: 50,
  },
  formContainer: {
    display: 'flex',
    flexDirection: 'column',
    flexWrap: 'wrap',
    textAlign: 'center',
    paddingLeft: 20,
    paddingRight: 20,
    paddingTop: 20,
    width: 400,
    height: 350
  },
  textField: {
    backgroundColor: grey[200],
    borderRadius: 4,
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: '90%',
  },
  // text field label styling
  label: {
    paddingLeft: 3,
  },
  input: {
    paddingLeft: 3,
    //   borderBottom: "1px solid",
    //   borderBottomColor: blue[500],
  },
  signupButton: {
    color: 'white',
    background: blue[500],
    borderColor: 'black',
    fontWeight: 400,
    '&:hover': {
      background: blue[500]
    },
    width: "95%",
    height: 50,
    padding: "10",
    margin: "auto",
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  iconBaseColor: {
    color: grey
    // can adjust icon -> fontSize: xxx
  },
  iconErrorColor: {
    color: red[500]
  }
})

const textFieldBoxStyle = {
  borderRadius: 'inherit',
  borderBottomLeftRadius: 0,
  borderBottomRightRadius: 0,
  backgroundColor: blue[500],
  height: 100,
  width: '100%',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
}

const signupFormHeadlineStyle = {
  color: 'white',
}

const signupFieldStyle = {
  position: 'relative',
  display: 'inline-block',
  width: '100%'
}

const iconOptions = {
  username: "account_box",
  email: "email",
  password: "lock"
}

class SignupForm extends React.Component {
  state = {
    username: '',
    email: '',
    password: '',
    error: false,
  }

  handleUserSignUpInput = name => event => {
    // may need to add validation here during user input on form field
    this.setState({
      [name]: event.target.value.toLowerCase().replace(/\s+/g, '')
    })
  }

  handleFormSubmit = event => {
    // deep copy
    let data = {}
    data["username"] = this.state.username
    data["emailAddress"] = this.state.email
    data["password"] = this.state.password
    // package data to be sent to server (remove these debug message afterward)
    console.log("before submit user signup form data={}", data);
    AccountAPI.signup(data).then(res => {
      // response from server
      console.log("successfully submit user signup form response data={}", res)
      // update onCreatedAccount state - show a pop up dialog specify token has been sent to user email address in HomePage
      this.props.onTokenProcess()
    })
    .catch(error => {
      console.log(error);
    })
  }

  render() {

    const { classes } = this.props
    const { error } = this.state

    /* condition check */
    // icon color
    let iconColor = !error ? classes.iconBaseColor : classes.iconErrorColor;

    return (
        <div className={classes.root}>
          <Paper elevation={24} style={{borderRadius: 10}}>
            <div style={textFieldBoxStyle}>
              <Typography
                type="title"
                gutterBottom
                style={signupFormHeadlineStyle}
              >
                Create Account
              </Typography>
            </div>
            <div>
              <form className={classes.formContainer} noValidate autoComplete="off">
                {Object.keys(this.state).filter(s => s !== "error").map(s => {
                  return (
                  <div key={s} style={signupFieldStyle}>
                    <TextField
                      required
                      key={s}
                      id={s}
                      type={s}
                      margin="normal"
                      value={this.state[s]}
                      label={s.charAt(0).toUpperCase() + s.slice(1)}
                      onChange={this.handleUserSignUpInput(s)}
                      className={classes.textField}
                      labelClassName={classes.label}
                      InputClassName={classes.input}
                      /* depth customization - damn... took me 20mins to figure out this. Goodluck guys for future customization part :)
                        InputProps={{
                          disableUnderline: true
                          className: classes.input
                        }}
                      */

                      // TODO; need to add errorText when dynamically checking user input
                      // errorText = helperText (why MATERIAL UI don't add a more concise migration note!!)
                      // ref = see the experimented code -> DemoValidationFormFlow.js
                    />
                    <Icon classes={{
                      root: iconColor
                    }}>
                      {iconOptions[s]}
                    </Icon>
                  </div>)
                })}

                <Button raised className={classes.signupButton} onClick={this.handleFormSubmit}>
                  Signup
                </Button>
              </form>
            </div>
          </Paper>
        </div>
    )
  }
}

SignupForm.propTypes = {
  classes: PropTypes.object.isRequired,
  onTokenProcess: PropTypes.func
}

export default withStyles(styles)(SignupForm)
