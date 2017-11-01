import React from 'react';
import Button from 'material-ui/Button';
import TextField from 'material-ui/TextField';
import FlatButtons from './button';
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
    };

    handleClickOpen = () => {
        this.setState({ open: true });
    };

    handleRequestClose = () => {
        this.setState({ open: false });
    };

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
                            fullWidth
                        />
                        <TextField
                            autoFocus
                            margin="dense"
                            id="name"
                            label="Password"
                            type="password"
                            fullWidth
                        />

                    </DialogContent>
                    <DialogActions>
                        <div className= "cancelButtons">
                            <Button onClick={this.handleRequestClose} color="primary">
                                Cancel
                            </Button>
                        </div>
                        <FlatButtons></FlatButtons>
                    </DialogActions>
                </Dialog>
            </div>
        );
    }
}