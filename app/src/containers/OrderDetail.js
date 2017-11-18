import React, { Component } from 'react'
class OrderDetail extends Component {

    render() {
        return (
            <div>
                <a href="/myorderPage" class="button_link1 button4"> Order </a>
                <button class="button button4">Manufacturer</button>
                <button class="button button4">Designer</button>

                <div className="detail">
                <h2>Order Detail</h2>
                </div>

            </div>



        )
    }
}

export default OrderDetail