// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import Grid from 'material-ui/Grid';

import ShipmentDate from './ShipmentDate';
import OrderType from './OrderType';
import Currency from './Currency';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.1,
  }),
});

function Paper2(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={1}>
        <Typography>
        <Grid container spacing={24}>
          <Grid item xs={6}>
            Assigned To
            <br />
            <br />
            <ShipmentDate />
            <br />
            <Currency />
          </Grid>
          <Grid item xs={6}>
            SKU
            <br />
            <OrderType />
          </Grid>
        </Grid>
      </Typography>
      </Paper>
    </div>
  );
}

Paper2.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Paper2);