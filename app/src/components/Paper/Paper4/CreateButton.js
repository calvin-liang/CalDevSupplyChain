// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Button from 'material-ui/Button';
import axios from 'axios'
import { API_ROOT } from '../../../config/ApiConfig'

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
      userInput["SKU"] = props.userInput.SKU;
      userInput["date"] = props.userInput.date;
      userInput["orderType"] = props.userInput.orderType;
      userInput["currency"] = props.userInput.currency;
      userInput["itemName"] = props.userInput.itemName;
      userInput["color"] = props.userInput.color;
      userInput["fabric"] = props.userInput.fabric;
      userInput["xs"] = props.userInput.xs;
      userInput["s"] = props.userInput.s;
      userInput["m"] = props.userInput.m;
      userInput["l"] = props.userInput.l;
      userInput["xl"] = props.userInput.xl;
      userInput["xxl"] = props.userInput.xxl;
      userInput["price"] = props.userInput.price;
      userInput["note"] = props.userInput.note;
      userInput["totalNum"] = props.userInput.totalNum;
      userInput["totalPrice"] = props.userInput.totalPrice;

      console.log("this is", userInput);
      if(props.userInput.SKU && props.userInput.date) {
          // axios.post(`${API_ROOT}/api/v1/orders/order`, userInput)
          //     .then(function (response) {
          //         console.log("res is :", response);
          //         if(response.status == 200) {
          //             window.location.href = '/myorderPage';
          //         } else {
          //             alert("error");
          //             // TODO 处理服务器提示 输入用户名 或 密码 错误
          //         }
          //     }).catch(err => {
          //     console.log("[err]:", err);
          // })
          const client = axios.create({
              auth: {
                  SKU: userInput["SKU"],
                  date: userInput["date"]
              },
              headers: {
                  "Content-Type": "application/json"
              }
          });
          client.post(`${API_ROOT}/api/v1/orders/order`);
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