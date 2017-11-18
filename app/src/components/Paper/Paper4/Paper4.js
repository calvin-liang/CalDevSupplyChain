// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import Grid from 'material-ui/Grid';

import TextBox from './TextBox';
import CreateButton from './CreateButton';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.1,
  }),
});


function Paper4(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={1}>
        <Typography>
        <Grid container spacing={24}>
          <Grid item xs={6}>
            <TextBox />
          </Grid>
          <Grid item xs={3}>
            Total Units
            <br />
            <br />
            Total
          </Grid>
          <Grid item xs={3}>
            <CreateButton />
          </Grid>
          </Grid>
        </Typography>
      </Paper>
    </div>
  );
}

Paper4.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Paper4);