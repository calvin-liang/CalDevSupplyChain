// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Button from 'material-ui/Button';
import Paper from 'material-ui/Paper';
//import Link from 'docs/src/modules/components/Link';

const styles = theme => ({
  button: {
    margin: theme.spacing.unit,
  },
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.1,
  }),
});

function doSomething(event) {
  // eslint-disable-next-line no-console
  console.log(event.currentTarget.getAttribute('data-something'));
}

function NewOrderButton(props) {
  const { classes } = props;
  return (
    <div>
    <Paper className={classes.root} elevation={4}>
      <Button color="primary" raised className={classes.button}>
        New Order
      </Button>
    </Paper>
    </div>
  );
}

NewOrderButton.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(NewOrderButton);