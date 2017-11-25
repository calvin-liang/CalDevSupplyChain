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
    marginLeft: theme.spacing.unit * 5,
    marginRight: theme.spacing.unit * 5,
    width: 50,
  },
  menu: {
    width: 200,
  },
});

class Quantity extends React.Component {
  state = {
    xs: '',
    s: '',
    m: '',
    l: '',
    xl: '',
    xxl: ''
  };

    handleChange1 = name => event => {
        this.setState({
            [name]: event.target.value,
        });
        this.props.getQuantity1(event.target.value);
    };

    handleChange2 = name => event => {
        this.setState({
            [name]: event.target.value,
        });
        this.props.getQuantity2(event.target.value);
    };

    handleChange3 = name => event => {
        this.setState({
            [name]: event.target.value,
        });
        this.props.getQuantity3(event.target.value);
    };

    handleChange4 = name => event => {
        this.setState({
            [name]: event.target.value,
        });
        this.props.getQuantity4(event.target.value);
    };
    handleChange5 = name => event => {
        this.setState({
            [name]: event.target.value,
        });
        this.props.getQuantity5(event.target.value);
    };

    handleChange6 = name => event => {
        this.setState({
            [name]: event.target.value,
        });
        this.props.getQuantity6(event.target.value);
    };

  render() {
    const { classes } = this.props;

    return (
      <form className={classes.container} noValidate autoComplete="off">
        <Grid container spacing={18}>
            <Grid item xs={3}>
                <TextField
                    id="xs"
                    label="XS"
                    className={classes.textField}
                    value={this.state.xs}
                    onChange={this.handleChange1('xs')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={3}>
                <TextField
                    id="s"
                    label="S"
                    className={classes.textField}
                    value={this.state.s}
                    onChange={this.handleChange2('s')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={3}>
                <TextField
                    id="m"
                    label="M"
                    className={classes.textField}
                    value={this.state.m}
                    onChange={this.handleChange3('m')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={3}>
                <TextField
                    id="l"
                    label="L"
                    className={classes.textField}
                    value={this.state.l}
                    onChange={this.handleChange4('l')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={3}>
                <TextField
                    id="xl"
                    label="XL"
                    className={classes.textField}
                    value={this.state.xl}
                    onChange={this.handleChange5('xl')}
                    margin="normal"
                />
            </Grid>
            <Grid item xs={3}>
                <TextField
                    id="xxl"
                    label="XXL"
                    className={classes.textField}
                    value={this.state.xxl}
                    onChange={this.handleChange6('xxl')}
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