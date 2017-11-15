import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Button from 'material-ui/Button';
import { checkLogin } from '../../api/AccountAPI';

const styles = theme => ({
    button: {
        margin: theme.spacing.unit,
    },
});

function doSomething(event) {
    // eslint-disable-next-line no-console
    console.log(event.currentTarget.getAttribute('data-something'));
}

function FlatButtons(props) {
    const { classes } = props;

    const doLogin = () => {
        const email = props.userInput.email;
        const password = props.userInput.password;
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
                console.log("here", res.headers.toString());
                if(res.status == 200) {
                    window.location.href = '/loginPage';
                } else {
                    alert("error");
                    // TODO 处理服务器提示 输入用户名 或 密码 错误
                }
            }).catch(err => {
                console.log('服务异常');
            })
        }
    }

    return (
        <div>
            {/*<Button className={classes.button}>Default</Button>*/}
            <Button color="primary" className={classes.button} onClick={doLogin} >
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
    );
}

FlatButtons.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(FlatButtons);