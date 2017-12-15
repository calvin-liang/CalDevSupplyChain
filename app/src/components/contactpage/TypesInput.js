
/* eslint-disable flowtype/require-valid-file-annotation */

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import MenuItem from 'material-ui/Menu/MenuItem';
import TextField from 'material-ui/TextField';

const styles = theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    width: 700,
  },
  menu: {
    width: 700,
  },
});

const currencies = [
  {
    value: 'Business Customer',
    label: 'Business Customer',
  },
  {
    value: 'Consumer',
    label: 'Consumer',
  },
  {
    value: 'Supplier',
    label: 'Supplier',
  },
];

class TypesInput extends React.Component {
  state = {
    currency: '',
  };

  handleChange = name => event => {
    this.setState({
      [name]: event.target.value,
    });
  };

  render() {
    const { classes } = this.props;

    return (
      <form className={classes.container} noValidate autoComplete="off">
        <TextField
          id="select-type"
          select
          label="Type"
          className={classes.textField}
          value={this.state.currency}
          onChange={this.handleChange('currency')}
          SelectProps={{
            MenuProps: {
              className: classes.menu,
            },
          }}
          // helperText="Please select your currency"
          margin="normal"
        >
          {currencies.map(option => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
      </form>
    );
  }
}

TypesInput.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(TypesInput);