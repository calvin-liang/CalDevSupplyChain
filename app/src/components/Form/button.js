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
            checkLogin(userInput).then(res => {
                // TODO 假设返回的code＝0未登录成功
                console.log("checkLogin", res);
                //console.log("here", res.headers.toString());
                console.log("before");
                console.log("[ ",res.headers["authorization"],"]");
                //console.log("header is:", res.getResponseHeader('X-Request-Id'));
                //var response = res.headers["authorization"].replace("Bearer ", "");
                //console.log(response);
                console.log("after");

                //debugger;
                //window.authorizationToken = res.headers["authorization"];
                //if (!cookies.get('authorizationToken')) {
                    debugger;
                    cookies.set('authorizationToken', res.headers["authorization"]);
                //}

                console.log("cookies:",cookies);
                console.log("after header");
                if(res.status == 200) {

                    this.setState({
                        redirectToMyOrderPage: true
                    })

                    // window.location.href = '/myorderPage';
                } else {
                    alert("error");
                    // TODO 处理服务器提示 输入用户名 或 密码 错误
                }
            }).catch(err => {
                console.log("err", err);
                //console.log('1服务异常');
            })
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
                    {/*<Button color="accent" className={classes.button}>*/}
                    {/*Accent*/}
                    {/*</Button>*/}
                    {/*<Button color="contrast" className={classes.button}>*/}
                    {/*Contrast*/}
                    {/*</Button>*/}
                    {/*<Button disabled className={classes.button}>*/}
                    {/*Disabled*/}
                    {/*</Button>*/}
                    {/*<Button href="#flat-buttons" className={classes.button}>*/}
                    {/*Link*/}
                    {/*</Button>*/}
                    {/*<Button disabled component={Link} href="/" className={classes.button}>*/}
                    {/*Link disabled*/}
                    {/*</Button>*/}
                    {/*<Button dense className={classes.button}>*/}
                    {/*Dense*/}
                    {/*</Button>*/}
                    {/*<Button className={classes.button} onClick={doSomething} data-something="here I am">*/}
                    {/*Does something*/}
                    {/*</Button>*/}
                </div>
            )
        )

     }
}

FlatButtons.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(FlatButtons);