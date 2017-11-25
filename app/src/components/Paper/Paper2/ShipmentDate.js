// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import TextField from 'material-ui/TextField';

const styles = theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        marginBottom: theme.spacing.unit * 100,
        marginTop: theme.spacing.unit,
        width: 200,
    },
});

class ShipmentDate extends React.Component {

    constructor(props) {
        super(props);
        const { classes } = props;

        this.state = {
            value: 'Property Value',
        };
    }

    handleChange = (event) => {
        this.setState({
            value: event.target.value,
        });
        this.props.getShipment(event.target.value);
    };

    render() {
        return (
            <form className={this.props.classes && this.props.classes.container} noValidate>
              <TextField
                  id="date"
                  label="Shipment Date"
                  type="date"
                  defaultValue="2017-11-20"
                  className={this.props.classes && this.props.textField}
                  InputLabelProps={{
                      shrink: true,
                  }}
                  onChange={this.handleChange}
              />
            </form>
        );
    }
}

ShipmentDate.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(ShipmentDate);