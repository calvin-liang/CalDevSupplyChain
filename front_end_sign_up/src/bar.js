import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import Typography from 'material-ui/Typography';
import Button from 'material-ui/Button';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Menu';
import TextField from 'material-ui/TextField';
import FormDialog from './dialog'
import RaisedButtons from './button'
import { red, purple, blue, grey} from 'material-ui/colors';


const styles = theme => ({
    root: {
        marginTop: theme.spacing.unit * 0.5,
        width: '100%',
    },
    flex: {
        flex: 1,
        color: red[400],
    },
    toolBar: {
        backgroundColor: grey[100],
    },
    menuButton: {
        marginLeft: -12,
        marginRight: 20,
    },

});

function ButtonAppBar(props) {
    const { classes } = props;
    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar className={classes.toolBar}>
                    <IconButton className={classes.menuButton} color="primary" aria-label="Menu">
                        <MenuIcon />
                    </IconButton>
                    <Typography type="title" className={classes.flex}>
                        CalDevSupplyChain
                    </Typography>
                    <FormDialog></FormDialog>
                    {/*<Button color="contrast">Login</Button>*/}
                </Toolbar>
            </AppBar>
        </div>
    );
}

ButtonAppBar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(ButtonAppBar);