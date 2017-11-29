import React, { Component } from 'react';
import Paper2 from '../components/Paper/Paper2/Paper2';
import Paper3 from '../components/Paper/Paper3/Paper3';
import Paper4 from '../components/Paper/Paper4/Paper4';
import Button from 'material-ui/Button';
import Grid from 'material-ui/Grid';
import TextField from 'material-ui/TextField';
import PropTypes from 'prop-types';
import Paper from 'material-ui/Paper';
import { withStyles } from 'material-ui/styles';
import CreateButton from '../components/Paper/Paper4/CreateButton';

const styles = theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
    },
});

class CreateOrder extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userInput: {
                SKU: '',
                date: '',
                orderType: '',
                currency: '',
                itemName: '',
                color: '',
                fabric: '',
                xs: '',
                s: '',
                m: '',
                l: '',
                xl: '',
                xxl: '',
                price: '',
                note: '',
                totalNum: '',
                totalPrice: ''
            }
        };
    }

    getShipment =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.date = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.date);
    }

    getCurrency =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.currency = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.currency);
    }

    getOrderType =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        //console.log("value", value);
        if (value == "10") {
            userInput.orderType = "SAMPLE";
        }
        else {
            userInput.orderType = "PRODUCTION";
        }
        //userInput.orderType = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.orderType);
    }

    getQuantity1 =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.xs = Number(value);
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.xs);
    }

    getQuantity2 =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.s = Number(value);
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.s);
    }

    getQuantity3 =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.m = Number(value);
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.m);
    }

    getQuantity4 =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.l = Number(value);
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.l);
    }

    getQuantity5 =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.xl = Number(value);
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.xl);
    }

    getQuantity6 =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.xxl = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.xxl);
    }

    getItem =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.itemName = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.itemName);
    }

    getColor =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.color = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.color);
    }

    getFabric =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.fabric = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.fabric);
    }

    getPrice =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.price = Number(value);
        this.setState({
            userInput: userInput
        })
        //console.log("this is :", userInput.price);
    }

    getNote =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.note = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.note);
    }

    getSKU =(value) => {
        let userInput = Object.assign({},this.state.userInput);
        userInput.SKU = value;
        this.setState({
            userInput: userInput
        })
        console.log("this is :", userInput.SKU);
    }

    render() {
        return (
            <div>
                <a href="/myorderPage" class="button_link1 button4"> MyOrder </a>
                <button class="button button4">Manufacturer</button>
                <button class="button button4">Designer</button>
                <div className="detail">
                    <h2>Create Order</h2>
                </div>
                <Paper2
                    getShipment={this.getShipment}
                    getCurrency={this.getCurrency}
                    getOrderType={this.getOrderType}
                    getSKU={this.getSKU}
                />
                <Paper3
                    getQuantity1={this.getQuantity1}
                    getQuantity2={this.getQuantity2}
                    getQuantity3={this.getQuantity3}
                    getQuantity4={this.getQuantity4}
                    getQuantity5={this.getQuantity5}
                    getQuantity6={this.getQuantity6}
                    getItem={this.getItem}
                    getColor={this.getColor}
                    getFabric={this.getFabric}
                    getPrice={this.getPrice}
                    getNote={this.getNote}
                />
                <Paper4 />
                <CreateButton
                    userInput={this.state.userInput}
                ></CreateButton>
            </div>

        );
    }
}

CreateOrder.propTypes = {
    classes: PropTypes.object.isRequired,
};
export default CreateOrder;