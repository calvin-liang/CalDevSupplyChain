import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import SimpleAppBar from './sign_up'
import RaisedButtons from './button'
import ButtonAppBar from './bar'
import TextFieldExampleSimple from './form'
import FormDialog from './dialog'
import Background from '/Users/liusongyu/Documents/cs490/CalDevSupplyChain/front_end_sign_up/src/background.jpg';

var sectionStyle = {
    width: "100%",
    height: "1000px",
    backgroundImage: "url(" + Background + ")"
};

class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
        };
    }

  render() {
    return (

      <div className="App">

          {/*<SimpleAppBar></SimpleAppBar>*/}
          {/*<RaisedButtons></RaisedButtons>*/}
          <ButtonAppBar></ButtonAppBar>
          <section style={ sectionStyle }>
          </section>
          {/*<FormDialog></FormDialog>*/}
          {/*<TextFieldExampleSimple></TextFieldExampleSimple>*/}

      </div>





    );
  }
}

export default App;
