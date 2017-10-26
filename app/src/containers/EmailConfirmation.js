import React, { Component } from 'react';
import TopBar from '../components/AppBar/TopBar';
import WelcomeMessage from '../components/Paper/WelcomeMessage';

class EmailConfirmation extends Component {

    render() {
        return (
            <div>
                <TopBar />
                <WelcomeMessage />
            </div>
        );
    }
}

export default EmailConfirmation;