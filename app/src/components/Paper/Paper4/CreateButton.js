// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Button from 'material-ui/Button';
import axios from 'axios'
import { API_ROOT } from '../../../config/ApiConfig'
import data1 from '../../../oneorder.json';
var cookies = require('browser-cookies');

const styles = theme => ({
  button: {
    marginTop: theme.spacing.unit * 0.1,
      marginLeft: theme.spacing.unit * 150
  },
  input: {
    display: 'none',
  },
});

function CreateButton(props) {
  const { classes } = props;
  const create = () => {

      let userInput = {}
      userInput["userUuid"] = "28968fa1-df8e-42e5-92cd-3a14123a831f";
      userInput["agentUuid"] = "cfc12d37-91d0-480d-97e1-625140823110";
      userInput["sku"] = props.userInput.SKU;

      userInput["orderType"] = props.userInput.orderType;
      userInput["orderStatus"] = "PENDING";

      let item = {}

      // userInput["color"] = props.userInput.color;
      // userInput["description"] = props.userInput.note;
      // userInput["fabric"] = props.userInput.fabric;
      // userInput["xs"] = props.userInput.xs;
      // userInput["s"] = props.userInput.s;
      // userInput["m"] = props.userInput.m;
      // userInput["l"] = props.userInput.l;
      // userInput["xl"] = props.userInput.xl;
      // userInput["xxl"] = props.userInput.xxl;

      item["color"] = props.userInput.color;
      item["description"] = props.userInput.note;
      item["fabric"] = props.userInput.fabric;
      let quan = {}
      quan["XS"] = props.userInput.xs;
      quan["S"] = props.userInput.s;
      quan["M"] = props.userInput.m;
      quan["L"] = props.userInput.l;
      // quan["XL"] = props.userInput.xl;
      // quan["XLL"] = props.userInput.xxl;
      item["quantity"] = quan;



      item["price"] = props.userInput.price;
      item["note"] = "Be careful";
      userInput["items"] = [item];
      userInput["currency"] = props.userInput.currency;
      //userInput["totalNum"] = props.userInput.totalNum;
      userInput["totalPrice"] = 0;
      userInput["shippingInstruction"] = "Ship to China";
      userInput["orderNote"] = "lease make sure the quality is good!";

      console.log("this is", userInput);
      if(props.userInput.SKU && props.userInput.orderType && props.userInput.color && props.userInput.note &&
          props.userInput.fabric && props.userInput.xs && props.userInput.s && props.userInput.m &&
          props.userInput.l && props.userInput.price && props.userInput.currency)
      {
          // axios.post(`${API_ROOT}/api/v1/orders/order`, userInput)
          //     .then(function (response) {
          //         console.log("res is :", response);
          //         if(response.status == 200) {
          //             window.location.href = '/myorderPage';
          //         } else {
          //             alert("error");
          //         }
          //     }).catch(err => {
          //     console.log("[err]:", err);
          // })
          // const client = axios.create({
          //     Authorization : 'Bearer ' + ''
          //
          // });

          console.log("cookie is ",cookies.get('authorizationToken'));
          console.log("!!!", data1);
          var config = {

              headers: {
                  'Authorization': decodeURIComponent(cookies.get('authorizationToken')),
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
              }
          }
          //var bodyParameters = userInput;
          //console.log(bodyParameters);

          axios.post(

              `${API_ROOT}/orders`,
              userInput,
              config

          )
          .then(res=>
          {
              console.log("data:", res);
          }
          ).catch(error=>{
              console.log("error is: ", error.response);
          });

          window.location.href = '/myorderPage';
      }

  }
  return (
    <div>
      <Button raised color="primary" className={classes.button} onClick={create}>
        Create
      </Button>
    </div>
  );
}

CreateButton.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CreateButton);