// @flow weak

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

function CreateButton(props) {
  const { classes } = props;
  return (
    <div>
      <Button raised color="primary" className={classes.button}>
        Create
      </Button>
    </div>
  );
}

CreateButton.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(CreateButton);