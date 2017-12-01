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
    marginTop: theme.spacing.unit * 3,
  }),
});

function WelcomeMessage(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={4}>
        <Typography type="headline" component="h3" align="center">
          Your email has been confirmed!
        </Typography>
        <Typography type="body1" component="p" align="center">
          Thank you for using CalDevSupplyChain!
        </Typography>
      </Paper>
    </div>
  );
}

WelcomeMessage.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(WelcomeMessage);