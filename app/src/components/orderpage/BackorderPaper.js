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

function BackorderPaper(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={40}>
        <Typography type="headline" component="h3">
            Congratulations! No Orders To Deal With.
        </Typography>
        <Typography type="body1" component="p">
            We couldn't find any sales orders with the selected filters. Please re-enable some filters, or create a new one.
        </Typography>
      </Paper>
    </div>
  );
}

BackorderPaper.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BackorderPaper);