import React from 'react'
import Button from 'material-ui/Button'
import PropTypes from 'prop-types'
import { withStyles } from 'material-ui/styles'
import TextField from 'material-ui/TextField'
import { blue, red } from 'material-ui/colors'
import Dialog, {
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from 'material-ui/Dialog';
import * as AccountAPI from '../../api/AccountAPI'

const styles = theme => ({
  // for customize token form css
})

class TokenForm extends React.Component {
  state = {
    show: false
  }

  handleRequestClose = () => {
    this.setState({ open: false });
    this.props.onTokenProcess();
  };

  render() {

    const {classes, show} = this.props
    const {token} = this.state

    return (
      <div>
        <Dialog open={show} onRequestClose={this.handleRequestClose}>
          <DialogTitle>
            Confirmation Email Sent
          </DialogTitle>
          <DialogContent>
            <DialogContentText>
              To verify your account, please click the activation link sent to your email
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Button onClick={this.handleRequestClose} color="primary">
              Close
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

// TODO: redirect
// class MyComponent extends React.Component {
//   state = {
//     redirect: false
//   }
//
//   handleSubmit () {
//     axios.post(/**/)
//       .then(() => this.setState({ redirect: true }));
//   }
//
//   render () {
//     const { redirect } = this.state;
//
//      if (redirect) {
//        return <Redirect to='/somewhere'/>;
//      }
//
//      return <RenderYourForm/>;
// }

TokenForm.propTypes = {
  classes: PropTypes.object.isRequired,
  show: PropTypes.bool,
  onTokenProcess: PropTypes.func,
}

export default withStyles(styles)(TokenForm)
