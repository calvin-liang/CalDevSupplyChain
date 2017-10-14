import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
            urls: [],
            price: '',
            description: '',
            data: ''
        };

        this.handlechange = this.handlechange.bind(this);
    }

    // updateState({event}) {
    //     this.setState({data: event.target.value});
    // }

    handlechange(event) {
        this.setstate({ data: event.target.data});

    }

   // componentDidMount() {
   //     var self = this;
   //     fetch('https://api.github.com/gists', {
   //         method: 'get'
   //     }).then(function(response) {
   //         // return [
   //         //     {
   //         //         "orderNo": "3333332",
   //         //         "description": "Tshirt",
   //         //         "price": "20"
   //         //     },  /* json[0] */
   //         //     {
   //         //         "orderNo": "32",
   //         //         "description": "pants",
   //         //         "price": "30"
   //         //     }    /* json[1] */
   //         // ]
   //         return response.json();
   //        //fawe fawefw
   //         // fawjelfkjwelkfj
   //         // fawelfkjwelkfj
   //
   //         /**
   //          * flaewjflkwjfkljawe
   //          * fawjflkwjefklwje
   //          * flkjawelkfj
   //          */
   //     }).then(function(json) {
   //         console.log(json);
   //
   //
   //         self.setState({
   //            price: json[0]['created_at'],
   //             description: json[1]
   //         });
   //
   //     }).catch(function(err) {
   //         // Error :(
   //     });
   //
   // }



  render() {
    return (
      <div className="App">
        <header className="App-header">
          {/*<img src={logo} className="App-logo" alt="logo" />*/}
          <h1 className="App-title">Supply Chain</h1>
            {/*<div>I'm inside react</div>*/}
        </header>
        <p className="App-intro">
          To get started, sign up or log in please and save to reload.
        </p>


          {/*<div className="t"> this is what we have</div>*/}
          < form onSubmit={this.onsubmit}>
          <div className="name">
              <label classname = "control-label">First Name</label>
              <input
                  onChange = {this.handleChange}
                  type = "text"
                  placeholder="first name"/>
              <label classname = "control-label">last Name</label>
              <input
                  onChange = {this.handleChange}
                  type = "text"
                  placeholder="last name"/>
          </div>

          <div className="email">
              <label classname = "control-label">email address</label>

              <input onChange={this.handleChange}  type = "text"  placeholder="Please enter email address"/>
          </div>

          <div className="password">
              <label classname = "control-label">password</label>

              <input onChange={this.handleChange}  type = "text"  placeholder="Please enter your password"/>
          </div>

          <div className ="sign_up_button" >
         <button classname="btn btn-primary btn-lg">
             sign up
         </button>
          </div>

          </form>






          {/*<div className="container">*/}
              {/*<div className="row">*/}
                  {/*<div className="col">*/}
                      {/*<div>Price: {this.state.price}</div>*/}
                  {/*</div>*/}
                  {/*<div className="col">*/}
                      {/*<div>Order Number: {this.state.description.orderNo}</div>*/}
                  {/*</div>*/}
              {/*</div>*/}
              {/*<div className="row">*/}
                  {/*<div className="col">*/}
                      {/*1 of 3*/}
                  {/*</div>*/}
                  {/*<div className="col">*/}
                      {/*2 of 3*/}
                  {/*</div>*/}
                  {/*<div className="col">*/}
                      {/*3 of 3*/}
                  {/*</div>*/}
              {/*</div>*/}
          {/*</div>*/}
      </div>





    );
  }
}

export default App;
