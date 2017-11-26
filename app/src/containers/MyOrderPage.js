import React, { Component } from 'react'
import data from '../example.json';
import axios from 'axios'
import { API_ROOT } from '../config/ApiConfig'
//import cookies from 'browser-cookies';
var cookies = require('browser-cookies');

class MyOrderPage extends Component {

    constructor() {
        super();
        this.state = {
            gettingData: false,
            data: {}
        }
    }
    componentDidMount() {
        console.log("i am here!");
        //const client = axios.create();
        //console.log("auth: ", window.authorizationToken);
        // axios.defaults.headers.common['Authorization'] =  cookies.get('authorizationToken');
        // axios.get(`${API_ROOT}/orders/order?userUuid=28968fa1-df8e-42e5-92cd-3a14123a831f`).then((data)=>
        //     {
        //     console.log("data:", data);
        // }
        // ).catch((error)=>{
        //         console.log(error);
        // })
        debugger;
        const axiosIntance = axios.create({
            baseURL: `${API_ROOT}`,
            headers: {Authorization: decodeURIComponent(cookies.get('authorizationToken'))}
        });
        axiosIntance.get('/orders?userUuid=28968fa1-df8e-42e5-92cd-3a14123a831f').then((data)=>
        {
            console.log("data:", data);
        }
        ).catch((error)=>{
                console.log(error);
        })


        //var result =  client.get(`${API_ROOT}/orders?userUuid=28968fa1-df8e-42e5-92cd-3a14123a831f`);

        // export default function() {
        //     axios.interceptors.request.use(function (config) {
        //         config.headers.Authorization = 'Bearer ' + 'eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eNpUjUEOgyAQAP-yZ2lYBBVPfgUB6zZoSVBj0_TvXQ89NJnTzGHeQKVAD96lEI-y55xefna0QgVlH7mgqpnhvjhKN_9cOJDboEeDqLlYXcE6Tv8invknjL3EY6PrMnlUoW6FxSCF7mQQto0oGmVQy45PKOHzBQAA__8.GO3TNjT3SeolsXpvYlHU-7ZHSo079L4_7JV3C5yXErY';
        //         return config;
        //     });
        // }

        // console.log("after result");
        // result.then(res => {
        //
        //     //debugger;
        //     console.log(res);
        //
        //     this.setState({
        //         gettingData: true,
        //         data: res
        //     });
        //
        // });

        // var data = null;
        //
        // var xhr = new XMLHttpRequest();
        // xhr.withCredentials = true;
        //
        // xhr.addEventListener("readystatechange", function () {
        //     if (this.readyState === 4) {
        //         console.log(this.responseText);
        //     }
        // });
        //
        // xhr.open("GET", "http://localhost:8080/api/v1/orders?userUuid=28968fa1-df8e-42e5-92cd-3a14123a831f");
        // xhr.setRequestHeader("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eNpUzE0OwiAQQOG7zLqYDBQKXXmVKT86hlYSSqMx3l1cuHD7veS9gGuFGTzlEI_aSslPfyXeYIDall5q5jbJ82Ulzid_X3tg2mFGjSid0sYMsC3pH-Kj_GDCL9x27i9pnbGJUIRkoxhl1MJJH4QiHFEqsgoTvD8AAAD__w.HxFVs4HDgC4Qvs6e2Q7RYN2TfbfBKPFN5wY1DZ5Humc");
        // xhr.setRequestHeader("cache-control", "no-cache");
        // xhr.setRequestHeader("postman-token", "9195b1e9-394e-237b-1f13-50689e3cfede");
        //
        // xhr.send(data);


    }

    //var data = require('../file.json');
    render() {
        if (this.state.gettingData)
            return (
                <div>Loading</div>
            )
        else {
            console.log(data);
            const tableDatas = data.map((item, index) => {
                // const tableDatas = this.state.data.map((item, index) => {
                return (<tr key="{index}">
                    <td>{item.sku}</td>
                    <td>{null}</td>
                    <td>{null}</td>
                    <td>{item.orderStatus}</td>
                    <td>{item.agentUuid}</td>
                    <td>{item.totalPrice}</td>
                </tr>)
            });
            console.log(tableDatas);
            return (
                <div>
                    <button className="button button4">MyOrder</button>
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