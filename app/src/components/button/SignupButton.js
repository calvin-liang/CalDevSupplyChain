import React, { Component }from 'react';
import PropTypes from 'prop-types';
import TextField from 'material-ui/TextField';
import Button from 'material-ui/Button';
import SignupForm from '../form/SignupForm';
import ActivationEmailDialog from '../dialog/ActivationEmailDialog'
import Dialog, {
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle
} from 'material-ui/Dialog';
import { withStyles } from 'material-ui/styles';
import { connect } from 'react-redux';
import { compose } from 'recompose';


const styles = theme => ({
  signupButton: {    
    color: "black",
    backgroundColor: "#58D3F7",
    fontFamily: "Roboto",
    fontWeight: "500",
    '&:hover': {
      backgroundColor: "white",
    }
  },
})


class SignupButton extends Component {
  state = {
    open: false,
  };

  componentWillReceiveProps(nextProps){
    if(this.props !== nextProps){
      // this.setState({open: !nextProps.loader.isLoading && !nextProps.successSentEmailActivation })
      let closeCondition = nextProps.loader.isLoading ? true : nextProps.successSentEmailActivation ? true : nextProps.signupCompleted ? true : false
      this.setState({open: !closeCondition })
    }
  }

  handleClickOpen = () => {
    this.setState({ open: true });
  };

  handleRequestClose = () => {
    this.setState({ open: false });
  };

  render() {

    const { classes } = this.props;

    return (
      <div>
        <Button
          raised
          className={classes.signupButton}
          onClick={this.handleClickOpen}
        >
          Signup
        </Button>
        <Dialog
          open={this.state.open}
          onRequestClose={this.handleRequestClose}
        >
          <SignupForm/>
        </Dialog>
        {this.props.successSentEmailActivation && <ActivationEmailDialog/>}
      </div>
    );
  }
}

function mapStateToProps(state) {

    const { loader } = state
    const { successSentEmailActivation } = state.notification
    const { signupCompleted } = state.signup

    return {
      loader,
      successSentEmailActivation,
      signupCompleted,
    }
}

export default compose(
  withStyles(styles),
  connect(mapStateToProps)
)(SignupButton);
