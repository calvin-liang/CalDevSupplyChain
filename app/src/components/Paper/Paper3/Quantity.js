/* eslint-disable flowtype/require-valid-file-annotation */

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import MenuItem from 'material-ui/Menu/MenuItem';
import TextField from 'material-ui/TextField';
import Grid from 'material-ui/Grid';

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
  menu: {
    width: 200,
  },
});

class Quantity extends React.Component {
  state = {
    xs: 0,
    s: 0,
    m: 0,
    l: 0,
    xl: 0,
    xxl: 0
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
        <Grid container spacing={24}>
            <Grid item xs={1}>
                <TextField
                    id="xs"
                    label="XS"
                    className={classes.textField}
                    value={this.state.xs}
                    onChange={this.handleChange('xs')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={1}>
                <TextField
                    id="s"
                    label="S"
                    className={classes.textField}
                    value={this.state.s}
                    onChange={this.handleChange('s')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={1}>
                <TextField
                    id="m"
                    label="M"
                    className={classes.textField}
                    value={this.state.m}
                    onChange={this.handleChange('m')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={1}>
                <TextField
                    id="l"
                    label="L"
                    className={classes.textField}
                    value={this.state.l}
                    onChange={this.handleChange('l')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={1}>
                <TextField
                    id="xl"
                    label="XL"
                    className={classes.textField}
                    value={this.state.xl}
                    onChange={this.handleChange('xl')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={1}>
                <TextField
                    id="xxl"
                    label="XXL"
                    className={classes.textField}
                    value={this.state.xxl}
                    onChange={this.handleChange('xxl')}
                    margin="normal"
                />
            </Grid>
        </Grid>
      </form>
    );
  }
}

Quantity.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Quantity);