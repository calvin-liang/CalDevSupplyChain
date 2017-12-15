import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import OrderButton from './OrderButton';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 3,
  }),
});


function Paper1(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={4}>
        <Typography type="headline" component="h3">
          Get started with Design a Difference.
        </Typography>
        <Typography component="p">
          Begin by placing an order.
        </Typography>
        <Typography component="p">
          <OrderButton />
        </Typography>
      </Paper>
    </div>
  );
}

Paper1.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Paper1);