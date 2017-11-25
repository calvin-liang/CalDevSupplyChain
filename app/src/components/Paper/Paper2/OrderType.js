/* eslint-disable flowtype/require-valid-file-annotation */

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Input, { InputLabel } from 'material-ui/Input';
import { MenuItem } from 'material-ui/Menu';
import { FormControl, FormHelperText } from 'material-ui/Form';
import Select from 'material-ui/Select';

const styles = theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  formControl: {
    marginTop: theme.spacing.unit * 2.4,
    marginLeft: theme.spacing.unit * 3.5,
    minWidth: 120,
  },
  selectEmpty: {
    marginTop: theme.spacing.unit * 2,
  },
});

class OrderType extends React.Component {
  state = {
    age: '',
    name: 'hai',
  };

  handleChange = name => event => {
    this.setState({ [name]: event.target.value });
    this.props.getOrderType(event.target.value);

  };

  render() {
    const { classes } = this.props;

    return (
      <form className={classes.container} autoComplete="off">
        <FormControl className={classes.formControl}>
          <InputLabel htmlFor="age-simple">Order Type</InputLabel>
          <Select
            value={this.state.age}
            onChange={this.handleChange('age')}
            input={<Input id="age-simple" />}
          >
          <MenuItem value={10}>Quote</MenuItem>
          <MenuItem value={20}>Production</MenuItem>
          </Select>
        </FormControl>

      </form>
    );
  }
}

OrderType.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(OrderType);