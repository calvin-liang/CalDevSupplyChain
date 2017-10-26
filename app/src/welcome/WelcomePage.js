import React, { Component } from 'react';
import TopBar from './TopBar';
import Message from './Message';

class WelcomePage extends Component {
    
    constructor(props) {
        super(props);

        this.state = {

        }
    }

    render() {
        return (
            <div>
                <TopBar />
                <Message />
            </div>
        );
    }
}

export default WelcomePage;