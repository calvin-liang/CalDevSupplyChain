import React from 'react';
import { withStyles } from 'material-ui/styles';
import Typography from 'material-ui/Typography';
import {DashboardFrame, SearchBar} from '../components'
import Grid from 'material-ui/Grid';
import Button from 'material-ui/Button';
import Dialog from 'material-ui/Dialog';
import {history} from '../util';
import IconButton from 'material-ui/IconButton';
import StarBorder from 'material-ui-icons/StarBorder';
import Paper1 from '../components/dashpage/part1/Paper1';
import Paper2 from '../components/dashpage/part2/Paper2';
import Paper3 from '../components/dashpage/part3/Paper3';

const styles = theme => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    width: '100%',
    height: '100%',
    zIndex: 1,
    overflow: 'hidden',
  },
  createNewOrderButton: {
    marginLeft: theme.spacing.unit * 1,
    marginRight: theme.spacing.unit * 1,
    color: "#292c34",
    borderRadius: 25,
    cursor: 'pointer',
    backgroundColor: "#66dbf9",
    '&:hover': {
      backgroundColor: "white",
    }
  },
  helpButton: {
    marginLeft: theme.spacing.unit * 1,
    marginRight: theme.spacing.unit * 1,
    color: "#292c34",
    borderRadius: 25,
    cursor: 'pointer',
    backgroundColor: "white",
  },
  subSectionRootContainer: {
    width: '100vw',
    backgroundColor: theme.palette.background.default,
    // backgroundColor: "red",
    paddingTop: theme.spacing.unit * 0.5,
    paddingBottom: theme.spacing.unit * 0.5,
    marginTop: 56,
    height: 50,
    [theme.breakpoints.up('sm')]: {
      width: '100vw',
      height: 50,
      marginTop: 64,
    },
  },
  subSectionContainer: {
    padding: theme.spacing.unit * 0.5
  },
  subSectionButtonContainer: {
    paddingLeft: theme.spacing.unit * 5
  },
  subSectionTitle: {
    marginLeft: theme.spacing.unit * 2.5
  },

  bodyRootContainer: {
    width: '100vw',
    // backgroundColor: theme.palette.background.default,
    backgroundColor: "red",
    paddingTop: theme.spacing.unit * 0.5,
    paddingBottom: theme.spacing.unit * 0.5,
  },
  subBodyContainer: {
    padding: theme.spacing.unit * 0.5
  }

});

class DashboardPage extends React.Component {
  state = {
    mobileOpen: false,
    nestedAccountListOpen: false,
  };

  handleCreateNewOrderButtonClick = () => {
      history.push('/dashboard/orders/new')
  }

  handleHelpButtonClick = () => {

  }

  render() {
    const { classes, theme } = this.props;
    return (
      <div className={classes.root}>
        <DashboardFrame
          appBarTitle={`Dashboard`}
          content={
            <div className={classes.subSectionRootContainer}>
              <Grid container alignItems="center" justify="center" className={classes.subSectionContainer}>
                <Grid item xs={2}>
                  <Typography className={classes.subSectionTitle} align="left" type="body">Dashboard</Typography>
                </Grid>
                <Grid item xs>
                    <SearchBar
                      placeholder={`🔍  Search...`}
                    />
                </Grid>
                <Grid item xs={5}>
                  <div className={classes.subSectionButtonContainer}>
                    {/* <Button
                      raised
                      className={classes.createNewOrderButton}
                      onClick={this.handleCreateNewOrderButtonClick}
                    >
                      Create New Order
                    </Button> */}
                    <Button
                      raised
                      className={classes.helpButton}
                      onClick={this.handleHelpButtonClick}
                    >
                      Help
                    </Button>
                  </div>
                </Grid>
              </Grid>

            <Grid container className={classes.subBodyContainer}>
            <Grid item xs={12}>
              <Paper1 />
            </Grid>
            <Grid item xs={12}>
                  <Paper2 />
            </Grid>
            <Grid item xs={12}>
                  <Paper3 />
            </Grid>
            </Grid>

            </div>
          }
        />
      </div>
    )
  }
}

export default withStyles(styles, { withTheme: true })(DashboardPage);