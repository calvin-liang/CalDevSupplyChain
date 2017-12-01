import React, { Component } from 'react'
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Button from 'material-ui/Button';
import { checkLogin } from '../../api/AccountAPI';

import {
    Redirect
} from 'react-router-dom'

var cookies = require('browser-cookies');

const styles = theme => ({
    button: {
        margin: theme.spacing.unit,
    },
});

function doSomething(event) {
    // eslint-disable-next-line no-console
    console.log(event.currentTarget.getAttribute('data-something'));
}



class FlatButtons extends Component {

    constructor() {
        super();
        this.state = {
            redirectToMyOrderPage: false
        };


    }

    doLogin = () => {
        const email = this.props.userInput.email;
        const password = this.props.userInput.password;
        console.log("this is ", email);

        if(!email) {
            alert(`Please enter email～`);
            return;
        }
        if(!password) {
            alert(`Please enter password`);
            return;
        }

        let userInput = {}
        userInput["emailAddress"] = email;
        userInput["password"] = password;

        if(email && password) {
            console.log('here!!!!');
            console.log("email varify: ", email.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/i));
            if (email.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/i)) {
                console.log('in');
                debugger;
                console.log("this is: ", checkLogin(userInput));
                if (checkLogin(userInput)) {
                    console.log("111");
                }
                checkLogin(userInput).then(res => {
                    console.log("checkLogin", res);
                    // console.log("before");
                    // console.log("[ ",res.headers["authorization"],"]");
                    // console.log("after");


                    cookies.set('authorizationToken', res.headers["authorization"]);

                    console.log("cookies:",cookies);
                    // console.log("after header");
                    if(res.status == 200) {
                        this.setState({
                            redirectToMyOrderPage: true
                        })
                    } else {
                        alert("error");
                    }
                }).catch( function (error)  {
                    // const response = error.response
                    // console.log("res ",response);
                    alert(`Email and password do not match!`);
                })
            }
            else {
                alert(`Please enter correct email!`);
            }


        }
    };

    render () {
        const { classes } = this.props;

        return (
            (
                <div>
                    {this.state.redirectToMyOrderPage == true ?
                        <Redirect to={{
                            pathname: '/myorderPage'
                        }}/> :
                        null
                    }

                    {/*<Button className={classes.button}>Default</Button>*/}
                    <Button color="primary" className={classes.button} onClick={this.doLogin} >
                        login
                    </Button>
                </div>
            )
        )

     }
}

FlatButtons.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(FlatButtons);