import React, { Component } from 'react'
//import data from '../example.json';
import axios from 'axios'
import { API_ROOT } from '../config/ApiConfig'
import Button from 'material-ui/Button';
import {
    Redirect
} from 'react-router-dom'

//import cookies from 'browser-cookies';
var cookies = require('browser-cookies');


const styles = theme => ({
    button: {
        margin: theme.spacing.unit,
    },
});

class MyOrderPage extends Component {

    constructor() {
        super();
        this.state = {
            redirectToOrderDetail: false,
            gettingData: true,
            data: {}
        }
    }

    handleClick = (val) => {
        console.log("this", val);
        for (var i = 0; i < this.state.data.length; i++) {
            if (this.state.data[i].uuid == val) {
                var oneOrder = this.state.data[i];
                window.oneOrder = oneOrder;
                break;
            }
        }
        console.log("this is", oneOrder);
        this.setState({
            redirectToOrderDetail: true
        })

    }

    componentDidMount() {
        console.log("i am here!");
        debugger;
        console.log("cookie: ", cookies.get('authorizationToken'));
        const axiosIntance = axios.create({
            baseURL: `${API_ROOT}`,
            headers: {Authorization: decodeURIComponent(cookies.get('authorizationToken'))}
        });
        axiosIntance.get('/orders?userUuid=28968fa1-df8e-42e5-92cd-3a14123a831f').then((res)=>
        {
            debugger;
            console.log("data:", res);
            this.setState({
                data: res.data,
                gettingData: false
            })
        }
        ).catch((error)=>{
                console.log(error);
        })
    }

    render() {
        const { classes } = this.props;

        if (this.state.gettingData)
            return (
                <div>Loading</div>
            )
        else {
            //console.log(data);
            //const tableDatas = data.map((item, index) => {
                const tableDatas = this.state.data.map((item, index) => {
                return (<tr key="{index}">
                    <td>{item.sku}</td>
                    <td>{"12/01/2017"}</td>
                    <td>{}</td>
                    <td>{item.orderStatus}</td>
                    <td>{item.agentUuid}</td>
                    <td>{item.totalPrice}</td>
                    <td>
                        <button onClick={() => this.handleClick(item.uuid)} >Detail</button>
                    </td>
                </tr>)
            });
            //console.log(tableDatas);
            console.log("before");
            return (
                <div>

                    {this.state.redirectToOrderDetail == true ?
                        <Redirect to={{
                            pathname: '/orderDetail'
                        }}/> :
                        null
                    }

                    <a href="/myorderPage" class="button_link1 button4"> MyOrder </a>
                    <button className="button button4">Manufacturer</button>
                    <button className="button button4">Designer</button>

                    <div className="create_order">
                        <a href="/createOrder" className="button_link button4"> Create Order</a>
                    </div>
                    <div className="active-order">
                        <h1>Active Order</h1>
                    </div>


                    <div className="table">
                        <table>
                            <tbody>
                            <tr>
                                <th>Order#</th>
                                <th>Created on</th>
                                <th>Expect delivery</th>
                                <th>Progress</th>
                                <th>Agent</th>
                                <th>Total</th>
                                <th></th>
                            </tr>
                            {tableDatas}

                            </tbody>
                        </table>
                    </div>

                    <div className="active-order">
                        <h1>Process Order</h1>
                    </div>

                    <div className="table">
                        <table>
                            <tbody>
                            <tr>
                                <th>Order#</th>
                                <th>Created on</th>
                                <th>Delivery</th>
                                <th>Progress</th>
                                <th>Agent</th>
                                <th>Total</th>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            )
        }
    }
}

export default MyOrderPage