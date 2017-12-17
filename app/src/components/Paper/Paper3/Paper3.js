// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import Grid from 'material-ui/Grid';

import Item from './Item';
import Color from './Color';
import Fabric from './Fabric';
import Note from './Note';
import Price from './Price';
import Quantity from './Quantity';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.2,
  }),
});

function Paper3(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={1}>
        <Typography type="body1" component="p">
        <Grid container spacing={24}>
          <Grid item xs={1}>
            <Item />
          </Grid>
          <Grid item xs={1}>
            <Color />
          </Grid>
          <Grid item xs={1}>
            <Fabric />
          </Grid>
          <Grid item xs={3}>
            <Quantity />
          </Grid>
          <Grid item xs={3}>
            <Price />
          </Grid>
          <Grid item xs={3}>
            <Note />
          </Grid>
        </Grid>
        </Typography>
      </Paper>
    </div>
  );
}

Paper3.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Paper3);