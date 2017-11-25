import React, { Component } from 'react'
import data from '../oneorder.json';
class OrderDetail extends Component {

    render() {
        debugger;
        const items = data[0].items;

        const tableDatas = items.map((item, index) => {
            console.log("[",item, "after", index);
            //console.log("length",item.items.length);
            //for (var i = 0; i < item.items.length; i++) {
                return (<tr key="{index}">

                    <td>{item.uuid}</td>
                    <td>{item.color}</td>
                    <td>{item.description}</td>
                    <td>{item.fabric}</td>
                    <td>{item.quantity.L}</td>
                    <td>{item.price}</td>
                </tr>)
            //}

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
                            <th>Size Quantity</th>
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