import React from 'react';
import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
//import RaisedButton from 'material-ui/RaisedButton';
import FlatButtons from './button';
import axios from 'axios';
//import { API_ROOT } from '../config/ApiConfig';

import Dialog, {
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
} from 'material-ui/Dialog';

import {
    BrowserRouter as Router,
    Route,
    Link,
    Redirect,
    withRouter
} from 'react-router-dom'

const Public = () => <h3>Public</h3>



export default class FormDialog extends React.Component {


    state = {
        open: false,
        userInput: {
            email: '',
            password: ''
        }
    };

    handleClickOpen = () => {
        this.setState({ open: true });
    };

    handleRequestClose = () => {
        this.setState({ open: false });
    };

    handleEmailChange = (e) => {
        let userInput = Object.assign({}, this.state.userInput);

        userInput.email = e.target.value;
        this.setState({
            userInput: userInput
        });
    };

    handlePasswordChange = (e) => {
        let userInput = Object.assign({}, this.state.userInput);

        userInput.password = e.target.value;
        this.setState({
            userInput: userInput
        });
    };

    // handleClick(event) {
    //     // const { classes } = props;
    //     //
    //     // const doLogin = () => {
    //     //     const email = props.userInput.email;
    //     //     const password = props.userInput.password;
    // var payload ={
    //     "email":this.userInput.email,
    //     "password":this.userInput.password,
    //
    //     }
    //     axios.post(`${API_ROOT}/account/login`,payload)
    //         .then(function(response){
    //             if(response.data.code ==200) {
    //                      window.location.href = '/loginPage';
    //                     }
    //                     else{
    //                 console.log("Error");
    //             }
    //         })
    //         .catch(function(error) {
    //             console.log("Error");
    //         });
    //     }


    render() {
        return (
            <div>
                <Button raised color="primary" onClick={this.handleClickOpen}>log in</Button>
                <Dialog
                    open={this.state.open}
                    onRequestClose={this.handleRequestClose}>
                    <DialogTitle>Welcome back!</DialogTitle>
                    <DialogContent>
                        <DialogContentText>
                            Please enter your email and password
                        </DialogContentText>
                        <TextField
                            autoFocus
                            margin="dense"
                            id="name"
                            label="Email Address"
                            type="email"
                            onChange={this.handleEmailChange}
                            fullWidth
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="name"
                            label="Password"
                            type="password"
                            onChange={this.handlePasswordChange}
                            fullWidth
                        />

                    </DialogContent>
                    <DialogActions>
                        <div className= "cancelButtons">
                            <Button onClick={this.handleRequestClose} color="primary">
                                Cancel
                            </Button>
                        </div>
                        {/*<RaisedButton label="Login" primary={true} onclick={(event) =>this.handleClick(event)}/>*/}
                        <FlatButtons
                            userInput={this.state.userInput}
                        ></FlatButtons>
                    </DialogActions>
                </Dialog>
            </div>
        );
    }
}