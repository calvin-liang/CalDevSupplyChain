/* eslint-disable flowtype/require-valid-file-annotation */
/* eslint-disable react/no-multi-comp */

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import AppBar from 'material-ui/AppBar';
import Tabs, { Tab } from 'material-ui/Tabs';
import EnhancedTable from './EnhancedTable';
import BackorderPaper from './BackorderPaper';

function TabContainer(props) {
  return <div style={{ padding: 8 * 3 }}>{props.children}</div>;
}

TabContainer.propTypes = {
  children: PropTypes.node.isRequired,
};

const styles = theme => ({
  root: {
    flexGrow: 1,
    marginTop: theme.spacing.unit * 3,
    backgroundColor: theme.palette.background.paper,
  },
});

class BasicTabs extends React.Component {
  state = {
    value: 0,
  };

  handleChange = (event, value) => {
    this.setState({ value });
  };

  render() {
    const { classes } = this.props;
    const { value } = this.state;

    return (
      <div className={classes.root}>
        <AppBar position="static">
          <Tabs value={value} onChange={this.handleChange}>
            <Tab label="Active" />
            <Tab label="All" />
            <Tab label="To Invoice" href="#basic-tabs" />
            <Tab label="To Ship" />
            <Tab label="To Backorder" />
            <Tab label="Add Filter" />
          </Tabs>
        </AppBar>

        {value === 0 && <TabContainer><EnhancedTable /></TabContainer>}
        {value === 1 && <TabContainer>All</TabContainer>}
        {value === 2 && <TabContainer>To Invoice</TabContainer>}
        {value === 3 && <TabContainer>To Ship</TabContainer>}
        {value === 4 && <TabContainer><BackorderPaper /></TabContainer>}
        {value === 5 && <TabContainer>To Filter</TabContainer>}
      </div>
    );
  }
}

BasicTabs.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BasicTabs);