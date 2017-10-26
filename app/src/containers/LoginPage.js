import React, { Component } from 'react'
import AppBar from 'material-ui/AppBar';
import AppBarExampleIcon from '../components/AppBar/LoginAppBar';
//import Background from '/Users/liusongyu/Desktop/FE/CalDevSupplyChain/app/src/blue-background.jpg';
import Background from '../asset/img/blue-background.jpg';
var sectionStyle = {
    width: "100%",
    height: "1000px",
    backgroundImage: "url(" + Background + ")"
};



class LoginPage extends Component {

    render() {
        return (
            <div>
                <section style={ sectionStyle }>
                    {<h1>{`Login Page`}</h1>}
                    {<h2>{`Welcome back!`}</h2>}
                </section>

                <AppBarExampleIcon></AppBarExampleIcon>
            </div>

        )
    }
}

export default LoginPage