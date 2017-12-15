import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import ImageAvatars from './ImageAvatars';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 3,
  }),
});


function Paper3(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={4}>
        <Typography type="headline" component="h3">
          Invoice
        </Typography>
        <Typography component="p">
          Check your messages here.
          <ImageAvatars />
        </Typography>
      </Paper>
    </div>
  );
}

Paper3.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Paper3);