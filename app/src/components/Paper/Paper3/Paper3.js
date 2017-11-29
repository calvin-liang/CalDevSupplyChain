// @flow weak

import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import Paper from 'material-ui/Paper';
import Typography from 'material-ui/Typography';
import Grid from 'material-ui/Grid';

import Item from './Item';
import Color from './Color';
import Fabric from './Fabric';
import Note from './Note';
import Price from './Price';
import Quantity from './Quantity';

const styles = theme => ({
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 0.2,
  }),
});

class Paper3 extends React.Component  {
  //const { classes } = props;
    getQuantity1 = (value) => {
        console.log(value);
        this.props.getQuantity1(value)
    }

    getQuantity2 = (value) => {
        console.log(value);
        this.props.getQuantity2(value)
    }

    getQuantity3 = (value) => {
        console.log(value);
        this.props.getQuantity3(value)
    }

    getQuantity4 = (value) => {
        console.log(value);
        this.props.getQuantity4(value)
    }

    getQuantity5 = (value) => {
        console.log(value);
        this.props.getQuantity5(value)
    }

    getQuantity6 = (value) => {
        console.log(value);
        this.props.getQuantity6(value)
    }

    getItem =  (value) => {
        console.log(value);
        this.props.getItem(value)
    }

    getColor =  (value) => {
        console.log(value);
        this.props.getColor(value)
    }

    getFabric =  (value) => {
        console.log(value);
        this.props.getFabric(value)
    }

    getPrice =  (value) => {
        console.log(value);
        this.props.getPrice(value)
    }

    getNote = (value) => {
        console.log(value);
        this.props.getNote(value)
    }


    render() {
      return (
          <div>
            <Paper className={this.props.classes.root} elevation={1}>
              <Typography type="body1" component="p">
                <Grid container spacing={15}>
                  {/*<Grid item xs={2}>*/}
                    {/*<Item getItem={this.getItem}/>*/}
                  {/*</Grid>*/}
                  <Grid item xs={2}>
                    <Color getColor={this.getColor}/>
                  </Grid>
                  <Grid item xs={2}>
                    <Fabric getFabric={this.getFabric}/>
                  </Grid>
                  <Grid item xs={3}>
                    <Quantity
                        getQuantity1={this.getQuantity1}
                        getQuantity2={this.getQuantity2}
                        getQuantity3={this.getQuantity3}
                        getQuantity4={this.getQuantity4}
                        getQuantity5={this.getQuantity5}
                        getQuantity6={this.getQuantity6}
                    />
                  </Grid>
                  <Grid item xs={3}>
                    <Price getPrice={this.getPrice}/>
                  </Grid>
                  <Grid item xs={3}>
                    <Note getNote={this.getNote}/>
                  </Grid>
                </Grid>
              </Typography>
            </Paper>
          </div>
      );
  }

}

Paper3.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Paper3);