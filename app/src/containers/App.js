import React, {Component} from 'react'
import {Route, Router} from 'react-router-dom'
import * as AccountAPI from '../api/AccountAPI'
import ReactLoading from 'react-loading';
import HomePage from './HomePage';
import {NotificationDialog} from '../components/'
import {withStyles} from 'material-ui/styles';
import {history} from '../util';
import {compose} from 'recompose';
import {connect} from 'react-redux';

const styles = theme => ({});

class App extends Component {

  render() {

    const {classes, alert, loader, notification} = this.props

    return (
      <div className="container">
        {loader.isLoading && <ReactLoading className="loader-container" type="bars" color="#66dbf9"/>}
        {notification.send && <NotificationDialog/>}
        <Router history={history}>
          <Route exact path="/" component={HomePage}/>
        </Router>
      </div>)
  }
}

function mapStateToProps(state) {
  const {alert, loader, notification } = state
  return {alert, loader, notification }
}

export default compose(withStyles(styles), connect(mapStateToProps))(App)
