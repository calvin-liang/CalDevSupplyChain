import React, {Component} from 'react';
import { connect } from 'react-redux';
import { compose } from 'recompose';
import Dialog, {
  DialogContent,
  DialogContentText,
  DialogTitle,
  withMobileDialog,
} from 'material-ui/Dialog';

class ActivationEmailDialog extends Component {

  constructor(props) {
      super(props);

      this.state = {
        open: true
      }
  }

  handleRequestClose = () => {
    this.setState({ open: false });
  };

  render() {

    return (
        <Dialog
          fullScreen={false}
          open={this.state.open}
          // open={true}
          onRequestClose={this.handleRequestClose}
        >
          <DialogTitle>{"Activation Email Link Sent"}</DialogTitle>
          <DialogContent>
            <DialogContentText>
              Please check your email.
            </DialogContentText>
          </DialogContent>
        </Dialog>
    );
  }
}

function mapStateToProps(state) {
    const {close} = state.signup
    return { close }
}

export default compose(
  // withStyles(styles),
  withMobileDialog(),
  connect(mapStateToProps)
)(ActivationEmailDialog);
