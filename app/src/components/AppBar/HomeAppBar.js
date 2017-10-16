import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Menu';
import {grey, red, blue} from 'material-ui/colors';
import Paper from 'material-ui/Paper';
import Grid from 'material-ui/Grid';
import TextField from 'material-ui/TextField';

const styles = theme => ({
  root: {
    width: '100%',
  },
  flex: {
    flex: 1,
  },
  toolBar: {
    backgroundColor: grey[100],
    color: 'black',
  },
  typography: {
    flex: 1,
    color: 'inherit',
  },
  // change to Dialog for loginButton
  loginButton: {
    color: 'white',
    background:  blue[500],
    borderColor: 'black',
    fontWeight: 400,
    '&:hover': {
      background: blue[500],
    }
  },
  paper: {
    padding: 16,
    textAlign: 'center',
  },
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200,
  },

});

function HomeAppBar(props) {
  const { classes } = props;
  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar className={classes.toolBar}>
          <Typography type="title" className={classes.typography}>
            CalDevSupplyChain
          </Typography>
          <Button raised className={classes.loginButton}>Login</Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}

HomeAppBar.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(HomeAppBar);
