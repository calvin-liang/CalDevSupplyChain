import React, { Component } from 'react';
import TopBar from '../components/AppBar/TopBar';
import Table from '../components/Table/BasicTable';
import NewOrderButton from '../components/Paper/NewOrderButton';

const a = {
    background: 'lightGreen'
}

class MyOrders extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div style={a}>
                <TopBar />
                <NewOrderButton />
                <Table />
            </div>
        );
    }
}

export default MyOrders;