// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import Grid from 'material-ui/Grid';
import TextField from 'material-ui/TextField';

import ShipmentDate from './ShipmentDate';
import OrderType from './OrderType';
import Currency from './Currency';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.1,
  }),
    textField: {
        marginLeft: theme.spacing.unit * 3.5,
        marginTop: theme.spacing.unit * 4.3,
    },
});

class MainPage1 extends React.Component {
  //const { classes } = props;
    constructor(props) {
        super(props);
        const { classes } = props;

        this.state = {
            value: '',
        };
    }

    // handleChange = (event) => {
    //     this.setState({
    //         value: event.target.value,
    //     });
    //     this.props.getSKU(event.target.value);
    // };

    handleChange = event => {
        this.setState({
            value: event.target.value,
        });
        this.props.getSKU(event.target.value);
    };

    getShipment = (value) => {
        console.log(value);
        this.props.getShipment(value)
    }
    getCurrency = (value) => {
        console.log(value);
        this.props.getCurrency(value)
    }
    getOrderType = (value) => {
        console.log(value);
        this.props.getOrderType(value)
    }

    render() {
        return (
            <div>
                <Paper className={this.props.classes.root} elevation={1}>
                    <Typography>
                        <Grid container spacing={24}>
                            <Grid item xs={6}>
                                Assigned To
                                <br />
                                <br />
                                <ShipmentDate getShipment={this.getShipment}/>
                                <br />
                                <Currency getCurrency={this.getCurrency}/>
                            </Grid>

                            <Grid item xs={6}>
                                <TextField
                                    id="SKU"
                                    label="SKU"
                                    className={this.props.classes.textField}
                                    value={this.state.name}
                                    onChange={this.handleChange}
                                    margin="normal"
                                />
                                <br />
                                <OrderType getOrderType={this.getOrderType}/>
                            </Grid>
                        </Grid>
                    </Typography>
                </Paper>
            </div>
        );
    }

}

MainPage1.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(MainPage1);