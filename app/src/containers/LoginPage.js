import React, { Component } from 'react'
import AppBar from 'material-ui/AppBar';
//import Background from '/Users/liusongyu/Desktop/FE/CalDevSupplyChain/app/src/blue-background.jpg';
import Background from '../asset/img/bg2.jpg';
var sectionStyle = {
    width: "100%",
    height: "1000px",
    backgroundImage: "url(" + Background + ")"
};

class LoginPage extends Component {

    render() {
        return (
            <div>
                <div className="t">
                    <h1>Welcome back!</h1>
                </div>
                <section style={ sectionStyle }>

                    {/*<button type="button" class="btn btn-primary">Primary</button>*/}
                    {/*<a class="btn btn-primary" href="#" role="button">Link</a>*/}
                    <a href="/myorderPage" class="button_link button4"> MyOrder</a>
                    <a href="#" class="button_link button4"> Manufacturer</a>
                    <a href="#" class="button_link button4"> Designer</a>


                </section>

            </div>

        )
    }
}

export default LoginPage