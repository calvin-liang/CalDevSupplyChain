
// NOTE: this is a demo validation form flow - might be helpful in some validation case
// For more granular input masking control. Check this ref: https://material-ui-next.com/demos/text-fields/
// May need to install 3rd library for masking or we can create a custom one
// @author: Jay Ng

// import React from 'react'
// import Button from 'material-ui/Button'
// import PropTypes from 'prop-types'
// import { withStyles } from 'material-ui/styles'
// import TextField from 'material-ui/TextField'
// import { blue, red } from 'material-ui/colors'
// import Dialog, {
//   DialogActions,
//   DialogContent,
//   DialogContentText,
//   DialogTitle,
// } from 'material-ui/Dialog';
// import * as AccountAPI from '../../api/AccountAPI'
//
// const styles = theme => ({
//   // for customize token form css
// })
//
//
// class TokenForm extends React.Component {
//   state = {
//     token: '',
//     show: false,
//     activatedToken: false,
//     error: false,
//     errorText: null,
//   }
//
//   handleTokenInput = (event) => {
//     this.setState({
//       token: event.target.value
//     })
//   }
//
//   handleRequestClose = () => {
//     this.setState({ open: false });
//     this.props.onTokenProcess();
//   };
//
//   handleActivateToken = () => {
//     let token = this.state.token
//     if(token) {
//       AccountAPI.activateAccount(token).then(res => {
//           console.log(res);
//           this.setState({
//             activatedToken: true
//           })
//       })
//       .catch(error => {
//           console.log(error);
//           this.setState({
//             error: true,
//             errorText: "Invalid Token"
//           })
//       })
//     }
//     else {
//       this.setState({
//         error: true,
//         errorText: "Please enter your verification token"
//       })
//     }
//   }
//
//   render() {
//
//     const {classes, show} = this.props
//     const {token} = this.state
//
//     return (
//       <div>
//         <Dialog open={show} onRequestClose={this.handleRequestClose}>
//           <DialogTitle>{
//             `Confirmation Email Sent`
//           }</DialogTitle>
//           <DialogContent>
//             <DialogContentText>
//               To verify your account, please click the activation link sent to your email
//             </DialogContentText>
//             <TextField
//               id={token}
//               label="Token"
//               type="token"
//               value={token}
//               onChange={this.handleTokenInput}
//               fullWidth
//               error={this.state.error}
//               helperText={this.state.errorText}
//               FormHelperTextProps={{
//                 error: this.state.error
//               }}
//             />
//           </DialogContent>
//           <DialogActions>
//             <Button onClick={this.handleRequestClose} color="primary">
//               Cancel
//             </Button>
//             <Button onClick={this.handleActivateToken} color="primary">
//               Verify
//             </Button>
//           </DialogActions>
//         </Dialog>
//       </div>
//     );
//   }
// }
//
// // signupButton: {
// //   color: 'white',
//
// TokenForm.propTypes = {
//   classes: PropTypes.object.isRequired,
//   show: PropTypes.bool,
//   onTokenProcess: PropTypes.func,
// }
//
// export default withStyles(styles)(TokenForm)
