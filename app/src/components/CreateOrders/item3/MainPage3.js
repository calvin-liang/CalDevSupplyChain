// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import Grid from 'material-ui/Grid';
import TextField from 'material-ui/TextField';

import TextBox from './TextBox';
import CreateButton from './CreateButton';

const styles = theme => ({
    container: {
        display: 'flex',
        flexWrap: 'wrap',
        marginLeft: theme.spacing.unit,
        marginTop: theme.spacing.unit,
    },
  root: theme.mixins.gutters({
    paddingTop: 1,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.01,
  }),
    textField: {
        marginLeft: theme.spacing.unit * 2.5,
        marginTop: theme.spacing.unit * 9,
        marginRight: theme.spacing.unit,
        width: 200,
    },
});


function MainPage3(props) {
  const { classes } = props;
  return (
    <div>
      <Paper className={classes.root} elevation={1}>
        <Typography>
        <Grid container spacing={24}>
          <Grid item xs={6}>
            <TextBox />
          </Grid>
          {/*<Grid item xs={3}>*/}
            {/*Total Units*/}
            {/*<br />*/}
            {/*<br />*/}
            {/*Total*/}
          {/*</Grid>*/}

          <Grid item xs={1}>
              <form className={classes.container} noValidate autoComplete="off">
                  {/*<TextField*/}
                      {/*id="TotalUnit"*/}
                      {/*label="TotalUnit"*/}
                      {/*className={classes.textField}*/}
                      {/*// value={this.state.name}*/}
                      {/*// onChange={this.handleChange('name')}*/}
                      {/*margin="normal"*/}
                  {/*/>*/}
                  <TextField
                      id="Total"
                      label="Total"
                      className={classes.textField}
                      // value={this.state.name}
                      // onChange={this.handleChange('name')}
                      margin="normal"
                  />
              </form>
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

MainPage3.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(MainPage3);