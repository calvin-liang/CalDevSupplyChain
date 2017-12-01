import React, { Component } from 'react'
import data from '../oneorder.json';
class OrderDetail extends Component {

    render() {
        debugger;
        console.log("this is window data", window.oneOrder);
        const oneOrder = window.oneOrder;
        debugger;
        const tableDatas = oneOrder.items.map((item, index) => {
            console.log("[",item, "after", index);
                return (<tr key="{index}">

                    <td>{item.uuid}</td>
                    <td>{item.color}</td>
                    <td>{item.description}</td>
                    <td>{item.fabric}</td>
                    <td>{item.quantity.XS}</td>
                    <td>{item.quantity.S}</td>
                    <td>{item.quantity.M}</td>
                    <td>{item.quantity.L}</td>
                    <td>{item.price}</td>
                </tr>)
        });

        return (
            <div>
                <a href="/myorderPage" class="button_link1 button4"> MyOrder </a>
                <button class="button button4">Manufacturer</button>
                <button class="button button4">Designer</button>

                <div className="detail">
                <h2>Order Detail</h2>
                </div>

                <div className= "table">
                    <table>
                        <tbody>
                        <tr>
                            <th>Itemid</th>
                            <th>Color</th>
                            <th>Description</th>
                            <th>Farbic</th>
                            <th>Size(XS) Quantity</th>
                            <th>Size(S) Quantity</th>
                            <th>Size(M) Quantity</th>
                            <th>Size(L) Quantity</th>
                            <th>Price</th>
                        </tr>
                        {tableDatas}
                        </tbody>
                    </table>
                </div>

            </div>



        )
    }
}

export default OrderDetail