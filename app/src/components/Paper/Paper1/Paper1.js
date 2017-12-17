// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.1,
  }),
});

function Paper1(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={1}>
        <Typography type="body1" component="p">
          #1324
        </Typography>
      </Paper>
    </div>
  );
}

Paper1.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Paper1);