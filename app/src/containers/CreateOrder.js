import React, { Component } from 'react';
import TopBar from '../components/AppBar/TopBar';
import Paper1 from '../components/Paper/Paper1/Paper1';
import Paper2 from '../components/Paper/Paper2/Paper2';
import Paper3 from '../components/Paper/Paper3/Paper3';
import Paper4 from '../components/Paper/Paper4/Paper4';

const a = {
    background: 'blue'
}

class CreateOrder extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div style={a}>
                <TopBar />
                <Paper1 />
                <Paper2 />
                <Paper3 />
                <Paper4 />
            </div>
        );
    }
}

export default CreateOrder;