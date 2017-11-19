import React, { Component } from 'react'
//import example.json from 'example.json';
class MyOrderPage extends Component {

    //var data = require('../file.json');
    render() {
        return (
            <div>
                <button class="button button4">MyOrder</button>
                <button class="button button4">Manufacturer</button>
                <button class="button button4">Designer</button>

                <div className= "create_order">
                    <a href="/createOrder" class="button_link button4"> Create Order</a>
                </div>
                <div className= "active-order">
                    <h1>Active Order</h1>
                </div>


                <div className= "table">
                    <table>
                        <tr>
                            <th>Order#</th>
                            <th>Created on</th>
                            <th>Expect delivery</th>
                            <th>Progress</th>
                            <th>Agent</th>
                            <th>Total</th>
                        </tr>
                        <br/>
                        {/*<tr>*/}
                            {/*<td>#001</td>*/}
                            {/*<td>09/05/2017</td>*/}
                            {/*<td>12/05/2017</td>*/}
                        {/*</tr>*/}
                        {/*<tr>*/}
                            {/*<td>#002</td>*/}
                            {/*<td>08/05/2017</td>*/}
                            {/*<td>01/05/2018</td>*/}
                        {/*</tr>*/}
                        {/*<tr>*/}
                            {/*<td>#003</td>*/}
                            {/*<td>10/05/2017</td>*/}
                            {/*<td>02/05/2018</td>*/}
                        {/*</tr>*/}

                    </table>
                </div>

                <div className= "active-order">
                    <h1>Process Order</h1>
                </div>

                <div className= "table">
                    <table>
                        <tr>
                            <th>Order#</th>
                            <th>Created on</th>
                            <th>Delivery</th>
                            <th>Progress</th>
                            <th>Agent</th>
                            <th>Total</th>
                        </tr>
                        {/*<br/>*/}
                        {/*<tr>*/}
                            {/*<td>#004</td>*/}
                            {/*<td>09/05/2017</td>*/}
                            {/*<td>12/05/2017</td>*/}
                        {/*</tr>*/}
                        {/*<tr>*/}
                            {/*<td>#005</td>*/}
                            {/*<td>08/05/2017</td>*/}
                            {/*<td>01/05/2018</td>*/}
                        {/*</tr>*/}
                        {/*<tr>*/}
                            {/*<td>#006</td>*/}
                            {/*<td>10/05/2017</td>*/}
                            {/*<td>02/05/2018</td>*/}
                        {/*</tr>*/}

                    </table>
                </div>



            </div>
        )
    }
}

export default MyOrderPage