import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import SwipeableViews from 'react-swipeable-views';
import AppBar from 'material-ui/AppBar';
import Tabs, { Tab } from 'material-ui/Tabs';
import EnhancedTable from './EnhancedTable';
import BackorderPaper from './BackorderPaper';

function TabContainer({ children, dir }) {
  return (
    <div dir={dir} style={{ padding: 8 * 3 }}>
      {children}
    </div>
  );
}

TabContainer.propTypes = {
  children: PropTypes.node.isRequired,
  dir: PropTypes.string.isRequired,
};

const styles = theme => ({
  root: {
    flexGrow: 1,
    marginTop: theme.spacing.unit * 3,
    backgroundColor: theme.palette.background.paper,
  },
});

class FullWidthTabs extends React.Component {
  state = {
    value: 0,
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };

  handleChangeIndex = index => {
    this.setState({ value: index });
  };

  render() {
    const { classes, theme } = this.props;

    return (
      <div className={classes.root} style={{ width: 500 }}>
        <AppBar position="static" color="default">
          <Tabs
            value={this.state.value}
            onChange={this.handleChange}
            indicatorColor="primary"
            textColor="primary"
            fullWidth
          >
            <Tab label="Active" />
            <Tab label="All" />
            <Tab label="Pending" href="#basic-tabs" />
            <Tab label="Finalized" />
            <Tab label="To Backorder" />
            <Tab label="Add Filter" />
          </Tabs>
        </AppBar>
        <SwipeableViews
          axis={theme.direction === 'rtl' ? 'x-reverse' : 'x'}
          index={this.state.value}
          onChangeIndex={this.handleChangeIndex}
        >
          <TabContainer dir={theme.direction}><EnhancedTable filter="Active"/></TabContainer>
          <TabContainer dir={theme.direction}><EnhancedTable /></TabContainer>
          <TabContainer dir={theme.direction}><EnhancedTable filter="Pending"/></TabContainer>
          <TabContainer dir={theme.direction}><EnhancedTable filter="Finalized"/></TabContainer>
          <TabContainer dir={theme.direction}><BackorderPaper /></TabContainer>
          <TabContainer dir={theme.direction}>Add Filter</TabContainer>
        </SwipeableViews>
      </div>
    );
  }
}

FullWidthTabs.propTypes = {
  classes: PropTypes.object.isRequired,
  theme: PropTypes.object.isRequired,
};

export default withStyles(styles, { withTheme: true })(FullWidthTabs);