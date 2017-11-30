import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {withStyles} from 'material-ui/styles';
import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Button from 'material-ui/Button';
import Typography from 'material-ui/Typography';
import Typist from 'react-typist'
import TypistLoop from 'react-typist-loop'
import Grid from 'material-ui/Grid';
import Paper from 'material-ui/Paper';
import {OrderSvgIcon, AccountSvgIcon, InventoryIcon, ReportIcon, SignupButton} from '../components'
import '../index.css'

const styles = theme => ({
  loginButton: {
    color: "white",
    backgroundColor: "transparent",
    border: "1px",
    borderColor: "white",
    borderStyle: "solid",
    fontFamily: "Roboto",
    fontWeight: "500",
    '&:hover': {
      backgroundColor: "transparent",
      borderColor: "#66dbf9",
      color: "#58D3F7"
    }
  },
  featuresRootGridContainer: {
    flexGrow: 1
  },
  root: {
    flexGrow: 1,
    height: '100%',
    width: '100%'
  },
  appBarContainer: {
    width: '100%'
  },
  appBarRoot: {
    backgroundColor: '#21242a'
  },
  toolBarRoot: {
    flexGrow: 1
  },
  flex: {
    flex: 1
  },
  logo: {
    color: '#58D3F7',
    fontSize: 24,
    fontWeight: 500,
  },
  navbarMenuRoot: {
    paddingTop: 2.5
  },
  navbarMenuText: {
    paddingLeft: 20,
    paddingRight: 20,
    fontSize: 18,
    fontStyle: 'normal',
    fontWeight: 300,
    '&:hover': {
      color: '#66dbf9'
    }
  },
  paper: {
    paddingLeft: theme.spacing.unit * 10.5,
    paddingRight: theme.spacing.unit * 10.5,
    height: '100%',
  },
  subFeaturesRoot: {
    flex: 1,
    height: '100vh',
  },
  subFeaturePaper: {
    flex: 1,
    paddingLeft: theme.spacing.unit * 10.5,
    paddingRight: theme.spacing.unit * 10.5,
  }
})

const style = {
  featuresTitle: {
    fontSize: 22,
    fontWeight: 550
  },
  featuresTitleGridContainer: {
    paddingTop: 5,
    paddingBottom: 10,
    paddingLeft: 0,
    paddingRight: 0,
    marginBottom: 0
  },
  featureContentGridContainer1: {
    paddingRight: 50
  },
  featureContentGridContainer2: {
    paddingLeft: 50
  },
  featureContentGridContainerMid1: {
    paddingLeft: 10,
    paddingRight: 35
  },
  featureContentGridContainerMid2: {
    paddingLeft: 35,
    paddingRight: 10
  },
  featureIconColor: "#00c5fc"
}

const url = {
  homeVideo: "http://res.cloudinary.com/jayng/video/upload/q_100/v1511331379/home-vid_y3oumb.webm"
}

class HomePage extends Component {

  state = {
    signupModalOpen: false,
    loginModalOpen: false
  }

  handleClickSignupButton = () => {
    this.setState({signupModalOpen: true})
  }

  handleCloseSignupButton = () => {
    this.setState({signupModalOpen: false})
  }

  handleClickLoginButton = () => {
    this.setState({loginModalOpen: true})
  }

  handleCloseLoginButton = () => {
    this.setState({loginModalOpen: false})
  }

  handleSubmitSignupForm = () => {
    console.log("clicked submit");
  }

  render() {

    const {classes} = this.props

    return (
      <div className={classes.root}>
        <div className={classes.appBarContainer}>
          <AppBar position="fixed" classes={{
              root: classes.appBarRoot
            }}>
            <Toolbar className={classes.toolBarRoot}>
              <Grid container="container" spacing={24} alignItems='center' justify='center'>
                <Grid item="item" xs="xs">
                  <a className={classes.logo} href="/">Design A Difference</a>
                </Grid>
                <Grid item="item" xs={7}>
                  <Grid container="container" alignItems='center' justify='center' className={classes.navbarMenuRoot}>
                    <Grid item="item">
                      <a className={classes.navbarMenuText} href="/page-product">Product</a>
                    </Grid>
                    <Grid item="item">
                      <a className={classes.navbarMenuText} href="/page-integration">Integration</a>
                    </Grid>
                    <Grid item="item">
                      <a className={classes.navbarMenuText} href="/page-resources">Resources</a>
                    </Grid>
                    <Grid item="item">
                      <a className={classes.navbarMenuText} href="/page-resources">Resources</a>
                    </Grid>
                  </Grid>
                </Grid>
                <Grid item="item" xs="xs">
                  <Grid container="container" alignItems='center' justify='flex-end'>
                    <Grid item="item" sm={6} alignItems='center' justify='center'>
                      <Button raised="raised" className={classes.loginButton}>
                        Login
                      </Button>
                    </Grid>
                    <Grid item="item" sm={6} alignItems='center' justify='center'>
                      <SignupButton></SignupButton>
                    </Grid>
                  </Grid>
                </Grid>
              </Grid>
            </Toolbar>
          </AppBar>
        </div>
        <div className="home-background-video-container">
          <div className="home-background-video-inner-container">
            <video loop="loop" autoPlay="autoPlay">
              <source src={url.homeVideo} type="video/webm"></source>
            </video>
          </div>
          <div className="home-video-content-overlay">
            <span className="home-video-content">
              <h1>Supply Chain Service</h1>
              <TypistLoop interval={5000}>
                {['that provide intelligent, personalized experiences', 'that provide visibility, transparency and insight into supply chain process', 'to power up your business'].map(text => <Typist className="home-video-content-swap-text" key={text} startDelay={1000}>{text}</Typist>)}
              </TypistLoop>
            </span>
          </div>
        </div>
        <div className="home-page-features-titles-container">
          <Grid container="container" justify={'flex-start'} alignItems={'center'}>
            <Grid item="item" xs={12}>
              <Paper elevation={0} className={classes.paper}>
                <h3 style={style.featuresTitle}>DaD supply chain solutions in the marketplace</h3>
              </Paper>
            </Grid>
          </Grid>
        </div>
        <div className="home-page-sub-features-content">
          <Grid container="container" direction={'column'} className={classes.subFeaturesRoot}>
            <Grid item xs={12}>
              <Paper elevation={0} className={classes.subFeaturePaper}>
                <Grid container spacing={16} justify={'space-around'}>
                  <Grid item xs={12} sm={3}>
                    <Grid container direction={'column'} justify={'space-around'} className={classes.flex}>
                      <Grid item>
                        <Paper elevation={0}>
                          <div className="home-features-icon">
                            <AccountSvgIcon fillColor={style.featureIconColor}></AccountSvgIcon>
                          </div>
                          <div className="home-features-subtitle">
                            <h4>B2B Collaboration</h4>
                          </div>
                          <div className="home-features-content">
                            <span>
                              Empowers organizations to improve the information flow across the business relationships that drive the supply chain.
                            </span>
                          </div>
                        </Paper>
                      </Grid>
                    </Grid>
                  </Grid>
                  <Grid item xs={12} sm={3}>
                    <Grid container direction={'column'} justify={'space-around'} className={classes.flex}>
                      <Grid item xs>
                        <Paper elevation={0}>
                          <div className="home-features-icon">
                            <OrderSvgIcon fillColor={style.featureIconColor}></OrderSvgIcon>
                          </div>
                          <div className="home-features-subtitle">
                            <h4>Orders Management & Fullfillment</h4>
                          </div>
                          <div className="home-features-content">
                            <span>
                              Manage all your orders in one place, arrange fulfillments and get paid 3x faster. Deliver the perfect order every time.
                            </span>
                          </div>
                        </Paper>
                      </Grid>
                    </Grid>
                  </Grid>
                  <Grid item xs={12} sm={3}>
                    <Grid container direction={'column'} justify={'space-around'} className={classes.flex}>
                      <Grid item xs>
                        <Paper elevation={0}>
                          <div className="home-features-icon">
                            <InventoryIcon fillColor={style.featureIconColor}></InventoryIcon>
                          </div>
                          <div className="home-features-subtitle">
                            <h4>Inventory Management</h4>
                          </div>
                          <div className="home-features-content">
                            <span>
                              Inventory utilization and fulfillment efficiencies in real time. Keep track products and restock across multiple locations and channels.
                            </span>
                          </div>
                        </Paper>
                      </Grid>
                    </Grid>
                  </Grid>
                  <Grid item xs={12} sm={3}>
                    <Grid container direction={'column'} justify={'space-around'} className={classes.flex}>
                      <Grid item xs>
                        <Paper elevation={0}>
                          <div className="home-features-icon">
                            <ReportIcon fillColor={style.featureIconColor}></ReportIcon>
                          </div>
                          <div className="home-features-subtitle">
                            <h4>Analysis & Optimization</h4>
                          </div>
                          <div className="home-features-content">
                            <span>
                              Enjoy the benefits of advanced analytics and proactively predict, assess and mitigate disruptions and risks without the complexity.
                            </span>
                          </div>
                        </Paper>
                      </Grid>
                    </Grid>
                  </Grid>
                </Grid>
              </Paper>
           </Grid>
         </Grid>
       </div>
    </div>

    )
  }
}

// TODO: put this in right place
// {successSentEmailActivation && <ActivationEmailDialog></ActivationEmailDialog>} */}

HomePage.propTypes = {
  classes: PropTypes.object.isRequired
}

export default withStyles(styles)(HomePage)
