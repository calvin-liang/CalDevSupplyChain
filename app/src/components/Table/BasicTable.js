// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Table, { TableBody, TableCell, TableHead, TableRow } from 'material-ui/Table';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';

import ProgressBar from '../ProgressBar/ProgressBar';

const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 0.5,
    overflowX: 'auto',
  },
  table: {
    minWidth: 700,
  },
});

let id = 0;
function createData(name, calories, fat, carbs, protein) {
  id += 1;
  return { id, name, calories, fat, carbs, protein };
}

const data = [
  createData(1, '159', '6.0', <ProgressBar />, '6', 4.0),
  createData(1, '159', '6.0', <ProgressBar />, '6', 4.0),
  createData(1, '159', '6.0', <ProgressBar />, '6', 4.0),
  createData(1, '159', '6.0', <ProgressBar />, '6', 4.0),
  createData(1, '159', '6.0', <ProgressBar />, '6', 4.0),
];

function BasicTable(props) {
  const { classes } = props;

  return (
    <div>
    <Paper className={classes.root}>
      <Typography type="body1" component="p" align="left">
          Active Orders
      </Typography>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell numeric>Order #</TableCell>
            <TableCell>Created On</TableCell>
            <TableCell>Expected Delivery</TableCell>
            <TableCell>Progress</TableCell>
            <TableCell>Agent</TableCell>
            <TableCell numeric>Total</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map(n => {
            return (
              <TableRow key={n.id}>
                <TableCell>{n.name}</TableCell>
                <TableCell numeric>{n.calories}</TableCell>
                <TableCell numeric>{n.fat}</TableCell>
                <TableCell numeric>{n.carbs}</TableCell>
                <TableCell numeric>{n.protein}</TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </Paper>

    <Paper className={classes.root}>
    <Typography type="body1" component="p" align="left">
          Processed Orders
    </Typography>
    <Table className={classes.table}>
      <TableHead>
        <TableRow>
        <TableCell numeric>Order #</TableCell>
        <TableCell>Created On</TableCell>
        <TableCell>Expected Delivery</TableCell>
        <TableCell>Progress</TableCell>
        <TableCell>Agent</TableCell>
        <TableCell numeric>Total</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {data.map(n => {
          return (
            <TableRow key={n.id}>
              <TableCell>{n.name}</TableCell>
              <TableCell numeric>{n.calories}</TableCell>
              <TableCell numeric>{n.fat}</TableCell>
              <TableCell numeric>{n.carbs}</TableCell>
              <TableCell numeric>{n.protein}</TableCell>
            </TableRow>
          );
        })}
      </TableBody>
    </Table>
    </Paper>
    </div>
  );
}

BasicTable.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(BasicTable);