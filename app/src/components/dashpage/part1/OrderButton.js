import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Button from 'material-ui/Button';

const styles = theme => ({
  button: {
    margin: theme.spacing.unit,
  },
  input: {
    display: 'none',
  },
});

function OrderButton(props) {

  const { classes } = props;
  return (
    <div>
      <Button raised color="primary" className={classes.button}>
        Create an Order
      </Button>
    </div>
  );
}

export default withStyles(styles)(OrderButton);