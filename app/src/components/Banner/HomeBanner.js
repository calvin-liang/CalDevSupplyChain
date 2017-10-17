import React from 'react'
import PropTypes from 'prop-types'
import { withStyles } from 'material-ui/styles'
import Typography from 'material-ui/Typography'
import {yellow} from 'material-ui/colors'
import SignupForm from '../Form/SignupForm'
import Grid from 'material-ui/Grid'
import homeBanner from '../../asset/img/home_banner.jpg'
import awards1 from '../../asset/img/awards1.png'
import awards2 from '../../asset/img/awards2.jpeg'
import awards3 from '../../asset/img/awards3.png'
import Icon from 'material-ui/Icon'

const styles = theme => ({
  overlay: {
    position: 'inherit',
    width: '100%',
    height: '100%',
    background: 'rgba(0,0,0,0.35)'
  },
})

const imageContainer = {
  width: '100%',
  height: 600,
}

const homeBannerImgStyle = {
  height: 'auto',
  backgroundImage: `url(${homeBanner}`,
}

const homeBannerTitleStyle = {
  color: 'white',
  paddingLeft: 25,
  paddingTop: '10',
  fontWeight: 'bold'
}

const homeSecondBannerTitleStyle = {
  color: 'inherit',
  fontWeight: 'bold',
  fontStyle: 'italic'
}

const awardsImageStyle = {
  width: 150,
  height: 100
}

let awardsIdentifier = [awards1, awards2, awards3]


// TODO: add star icon on top of awards
// const StarIcon = () => <Icon color={yellow[500]}>star_rate</Icon>

class HomeBanner extends React.Component {
  render() {
    const {classes, onTokenProcess} = this.props
    return (
      <div style={imageContainer}>
        <div style={homeBannerImgStyle}>
          <div className={classes.overlay}>
            <Grid container spacing={40} direction="row">
              <Grid item xs={12} sm={6} alignitems="center" key="banner-grid">
                <div style={{ paddingTop: '30%', paddingLeft: 20 }}>
                  <Typography
                    type="display1"
                    style={homeBannerTitleStyle}
                    gutterBottom
                  >
                    Supply Chain Service to
                    <br/>
                    Power Up Your Business
                  </Typography>
                </div>
                <Grid container style={{ display: 'flex' }}>
                  <Grid item xs={12}>
                    <Grid
                      container
                      className={classes.demo}
                      spacing={8}
                      style={{ paddingLeft: 50 }}
                    >
                      {[0,1,2].map(k => {
                        let keyId = "awards" + k.toString()
                        return (
                          <Grid item key={keyId}>
                            <img src={awardsIdentifier[k]} alt='' style={awardsImageStyle} />
                          </Grid>
                        )
                      })}
                    </Grid>
                  </Grid>
                </Grid>
              </Grid>
              <Grid item xs={12} sm={6} key="signup-grid">
                  <SignupForm onTokenProcess={onTokenProcess}/>
              </Grid>
            </Grid>
          </div>
        </div>
      </div>
    )
  }
}

HomeBanner.propTypes = {
  classes: PropTypes.object.isRequired,
  onTokenProcess: PropTypes.func
}



export default withStyles(styles)(HomeBanner)
