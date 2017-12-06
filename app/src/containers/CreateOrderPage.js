import React from 'react';
import { withStyles } from 'material-ui/styles';
import Typography from 'material-ui/Typography';
import {DashboardFrame, SearchBar} from '../components'
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';
import Grid from 'material-ui/Grid';
import Button from 'material-ui/Button';
import Dialog from 'material-ui/Dialog';
import FileUpload from 'material-ui-icons/FileUpload';
import HelpOutline from 'material-ui-icons/HelpOutline';
import {InputAdornment} from 'material-ui/Input';
import Paper from 'material-ui/Paper';
import TextField from 'material-ui/TextField';
import IconButton from 'material-ui/IconButton';
import Edit from 'material-ui-icons/Edit';
import MenuItem from 'material-ui/Menu/MenuItem';
import Divider from 'material-ui/Divider';
import Radio, { RadioGroup } from 'material-ui/Radio';
import { FormLabel, FormControl, FormControlLabel, FormHelperText } from 'material-ui/Form';
import Select from 'material-ui/Select';
import AddIcon from 'material-ui-icons/Add';

const theme = createMuiTheme({
  overrides: {
  }
});

const styles = theme => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    width: '100%',
    height: '100%',
    zIndex: 1,
    overflow: 'hidden',
  },
  contentRootContainer: {
    height: 'calc(100vh - 64px)',
    backgroundColor: theme.palette.background.default,
  },
  importCsvButton: {
    color: "#292c34",
    borderRadius: 25,
    cursor: 'pointer',
    backgroundColor: "#66dbf9",
    '&:hover': {
      backgroundColor: "white",
    }
  },
  subSectionRootContainer: {
    width: '100vw',
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
  subSectionButtonContainer: {

  },
  subSectionContainer: {
    padding: theme.spacing.unit * 0.5
  },
  subSectionTitle: {
    marginLeft: theme.spacing.unit * 2.5
  },
  helpButton: {
    marginLeft: theme.spacing.unit * 1,
    marginRight: theme.spacing.unit * 1,
    color: "#292c34",
    borderRadius: 25,
    cursor: 'pointer',
    backgroundColor: "white",
  },
  orderContentRootContainer: {
    margin: theme.spacing.unit * 1,
  },
  contentPaperRootContainer: {
    padding: theme.spacing.unit * 2,
  },
  progressBar: {
    backgroundColor: '#6bdbf7',
    height: 3,
    width: '100%',
  },
  inputLabelFocused: {
    color: '#0080ff',
    fontWeight: 500,
  },
  inputOrderSkuContainer: {

  },
  textField: {
    width: 200,
  },
  selectMenu: {
    width: 200,
  },
  divider: {
    marginTop: theme.spacing.unit * 2,
    marginBottom: theme.spacing.unit * 2
  },
  label: {
    fontSize: 16,
  },
  radioLabel: {
    fontSize: 13,
  },
  group: {
    margin: `${theme.spacing.unit}px 0`,
  },
  radioIconChecked: {
    color: '#0080ff',
  },
  addItemButton: {
    color: '#1683fb',
    paddingLeft: 0,
    marginLeft: 0,
  },
  itemTextField: {
    margin: 0,
    padding: 0,
  },
  itemTextFieldInputBox: {
    borderRadius: 4,
    padding: theme.spacing.unit * 0.2,
    transition: theme.transitions.create(['border-color', 'box-shadow']),
    '&:focus': {
      borderColor: '#00c9ff',
    },
    border: '2px solid #ced4da',
  }

});

const peoples = [
  {
    value: 'uuid1',
    label: 'James',
  },
  {
    value: 'uuid2',
    label: 'Rebecca',
  },
];

const currencies = [
  {
    value: 'USD',
    label: '$',
  },
  {
    value: 'EUR',
    label: '€',
  },
  {
    value: 'BTC',
    label: '฿',
  },
  {
    value: 'JPY',
    label: '¥',
  },
];

const orderStatuses = [
  {
    value: 'PENDING',
    label: 'PENDING',
  },
  {
    value: 'IN_PROCESS',
    label: 'IN PROCESS',
  },
  {
    value: 'DELIVERED',
    label: 'DELIVERED',
  },
  {
    value: 'FINISHED',
    label: 'FINISHED',
  },
  {
    value: 'DELETED',
    label: 'DELETED',
  },
];

class CreateOrderPage extends React.Component {
  state = {
    people: 'Agent',
    orderType: 'QUOTE',
    orderStatus: 'PENDING',
    currency: 'USD',
    items: [],
    color: '',
    description: '',
    fabric: '',
    quantity: {
      XS: 0,
      S: 0,
      M: 0,
      L: 0
    },
    price: `$ 0`,
    note: ''
  };

  handleImportCsvFileUploadButtonClick = e =>  {
    // TODO: need to import csv
  }

  handleChange = name => e => {
    this.setState({
      [name]: e.target.value,
    })
  }

  handleRadioChange = e => {
    const {name, value} = e.target
   this.setState({[name]:  value });
  };

  handleItemQuantityFieldChange = name => e => {
    this.setState({quantity: {
      ...this.state.quantity,
      [name]: e.target.value
    }})
  }

  handleAddItemButtonClick = e => {
    e.preventDefault()

    const newItem = {
      color: this.state.color,
      description: this.state.description,
      fabric: this.state.fabric,
      quantity: {
        XS: this.state.quantity['XS'],
        S: this.state.quantity['S'],
        M: this.state.quantity['M'],
        L: this.state.quantity['L']
      },
      price: this.state.price,
      note: this.state.note
    }

    this.setState(prevState => ({
      items: prevState.items.concat(newItem)
    }))

  }

  render() {

    const { classes, theme } = this.props;

    return (
      <MuiThemeProvider theme={theme}>
        <div className={classes.root}>
          <DashboardFrame
            appBarTitle={`Orders`}
            content={
              <div className={classes.contentRootContainer}>
                <div className={classes.subSectionRootContainer}>
                  <Grid container alignItems="center" justify="center" className={classes.subSectionContainer}>
                    <Grid item xs>
                      <Typography className={classes.subSectionTitle} align="left" type="subheading">Orders / New Order</Typography>
                    </Grid>
                    <Grid item xs={5}>
                      <div className={classes.subSectionButtonContainer}>
                        <Button
                          raised
                          className={classes.importCsvButton}
                          onClick={this.handleImportCsvFileUploadButtonClick}
                        >
                          Import via CSV
                          <FileUpload className={classes.rightIcon} />
                        </Button>
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
                </div>
                <div>
                  <Grid container direction="column" alignItems="stretch" className={classes.orderContentRootContainer}>
                    <Grid item xs={7}>
                      <div className={classes.progressBar}></div>
                      <Paper className={classes.contentPaperRootContainer} elevation={1}>
                        <Grid container direction="row" justify="center" alignItems="center">
                          <Grid item xs>
                            <TextField
                              id="select-people"
                              select
                              className={classes.textField}
                              value={this.state.people}
                              label={`Select Agents`}
                              onChange={this.handleChange('people')}
                              InputLabelProps={{
                                shrink: true,
                                FormControlClasses: {
                                  focused: classes.inputLabelFocused
                                }
                              }}
                              SelectProps={{
                                MenuProps: {
                                  className: classes.selectMenu,
                                },
                              }}
                            >
                              {peoples.map(p => (
                                <MenuItem key={p.value} value={p.value}>
                                  {p.label}
                                </MenuItem>
                              ))}
                            </TextField>
                          </Grid>
                          <Grid item xs={3}>
                              <TextField
                                id="search"
                                label="Order SKU"
                                labelClassName={classes.label}
                                type="search"
                                className={classes.inputOrderSkuContainer}
                                placeholder={`#SO001`}
                                onChange={this.handleChange('sku')}
                                InputLabelProps={{
                                  shrink: true,
                                  FormControlClasses: {
                                    focused: classes.inputLabelFocused
                                  }
                                }}
                                InputProps={{
                                  disableUnderline: true,
                                  endAdornment:
                                    <InputAdornment position="end">
                                      <IconButton
                                        aria-label="Edit"
                                        onClick={this.handleChange('sku')}
                                      >
                                        <Edit style={{fontSize: 15}}/>
                                      </IconButton>
                                    </InputAdornment>
                                }}
                              />
                          </Grid>
                        </Grid>
                        <Divider className={classes.divider} light/>
                        <Grid container>
                          <Grid item xs>
                            <TextField
                              id="select-currencies"
                              select
                              className={classes.textField}
                              value={this.state.currency}
                              label={`Select Currencies`}
                              onChange={this.handleChange('currency')}
                              InputLabelProps={{
                                shrink: true,
                                FormControlClasses: {
                                  focused: classes.inputLabelFocused
                                }
                              }}
                              SelectProps={{
                                MenuProps: {
                                  className: classes.selectMenu,
                                },
                              }}
                            >
                              {currencies.map(option => (
                                <option key={option.value} value={option.value}>
                                  {option.label}
                                </option>
                              ))}
                            </TextField>
                          </Grid>
                          <Grid item xs>
                            <TextField
                              id="select-orderStatus"
                              select
                              disabled
                              className={classes.textField}
                              value={this.state.orderStatus}
                              label={`Order Status`}
                              onChange={this.handleChange('orderStatus')}
                              InputLabelProps={{
                                shrink: true,
                                FormControlClasses: {
                                  focused: classes.inputLabelFocused
                                }
                              }}
                              SelectProps={{
                                MenuProps: {
                                  className: classes.selectMenu,
                                },
                              }}
                            >
                              {orderStatuses.map(option => (
                                <option key={option.value} value={option.value}>
                                  {option.label}
                                </option>
                              ))}
                            </TextField>
                          </Grid>
                          <Grid item xs={3}>
                            <FormControl
                              component="fieldset"
                              required
                              className={classes.formControl}
                              margin="dense"
                            >
                              <FormLabel
                                component="label"
                                classes={{
                                  root: classes.radioLabel,
                                  focused: classes.inputLabelFocused

                                }}
                              >
                                Order type
                              </FormLabel>
                              <RadioGroup
                                aria-label="order-type"
                                name="orderType"
                                className={classes.group}
                                value={this.state.orderType}
                                onChange={this.handleRadioChange}
                              >
                                <FormControlLabel className={classes.label} value="QUOTE"
                                  label={<Typography type="caption">QUOTE</Typography>}
                                  control={
                                    <Radio
                                      checked={this.state.orderType == "QUOTE" ? true : false}
                                      classes={{
                                        checked: classes.radioIconChecked
                                      }}
                                    />
                                  }
                                />
                                <FormControlLabel value="SAMPLE" label={<Typography type="caption">SAMPLE</Typography>} control={
                                  <Radio
                                    classes={{
                                      checked: classes.radioIconChecked
                                    }}
                                  />}
                                />
                                <FormControlLabel value="PRODUCTION" label={<Typography type="caption">PRODUCTION</Typography>} control={
                                  <Radio
                                    classes={{
                                      root: classes.radioRoot,
                                      checked: classes.radioIconChecked
                                    }}
                                  />}
                                />
                              </RadioGroup>
                            </FormControl>
                          </Grid>
                        </Grid>
                        <Divider className={classes.divider} light/>
                        <Grid container>
                          <Grid item xs={3}>
                            <Typography type="caption">Item Description</Typography>
                          </Grid>
                          <Grid item xs>
                            <Typography type="caption">Color</Typography>
                          </Grid>
                          <Grid item xs>
                            <Typography type="caption">Fabric</Typography>
                          </Grid>
                          <Grid item xs>
                            <Typography type="caption">Quantity</Typography>
                          </Grid>
                          <Grid item xs>
                            <Typography type="caption">Price</Typography>
                          </Grid>
                          <Grid item xs={3}>
                            <Typography type="caption">Item Note</Typography>
                          </Grid>
                        </Grid>
                        <Divider className={classes.divider} light/>
                        <Grid container justify="flex-start" alignItems="stretch">
                          <Grid item xs={3}>
                            {/* <Typography type="caption">Item Description</Typography> */}
                            <TextField
                               id="multiline-item-description"
                               multiline
                               rowsMax="4"
                               value={this.state.description}
                               onChange={this.handleChange('description')}
                               className={classes.itemTextField}
                               margin="dense"
                               InputProps={{
                                 disableUnderline: true,
                                 classes: {
                                   input: classes.itemTextFieldInputBox,
                                 },
                               }}
                            />
                          </Grid>
                          <Grid item xs>
                            <TextField
                               id="multiline-item-color"
                               multiline
                               rowsMax="4"
                               value={this.state.color}
                               onChange={this.handleChange('color')}
                               className={classes.itemTextField}
                               margin="dense"
                               InputProps={{
                                 disableUnderline: true,
                                 classes: {
                                   input: classes.itemTextFieldInputBox,
                                 },
                               }}
                            />
                          </Grid>
                          <Grid item xs>
                            <TextField
                               id="multiline-item-fabric"
                               multiline
                               rowsMax="4"
                               value={this.state.fabric}
                               onChange={this.handleChange('fabric')}
                               className={classes.itemTextField}
                               margin="dense"
                               InputProps={{
                                 disableUnderline: true,
                                 classes: {
                                   input: classes.itemTextFieldInputBox,
                                 },
                               }}
                            />
                          </Grid>
                          <Grid item xs>
                            <TextField
                               id="multiline-item-quantity"
                               multiline
                               rowsMax="4"
                               value={''}
                               onChange={this.handleChange('quantity')}
                               className={classes.itemTextField}
                               margin="dense"
                               InputProps={{
                                 disableUnderline: true,
                                 classes: {
                                   input: classes.itemTextFieldInputBox,
                                 },
                               }}
                            />
                          </Grid>
                          <Grid item xs>
                            <TextField
                               id="multiline-item-quantity"
                               multiline
                               disabled
                               rowsMax="4"
                               value={this.state.price}
                               onChange={this.handleChange('price')}
                               className={classes.itemTextField}
                               margin="dense"
                               placeholder={`$`}
                               InputProps={{
                                 disableUnderline: true,
                                 classes: {
                                   input: classes.itemTextFieldInputBox,
                                 },

                               }}
                            />
                          </Grid>
                          <Grid item xs={3}>
                            <Grid item xs>
                              <TextField
                                 id="multiline-item-quantity"
                                 multiline
                                 rowsMax="4"
                                 value={this.state.note}
                                 onChange={this.handleChange('note')}
                                 className={classes.itemTextField}
                                 margin="dense"
                                 InputProps={{
                                   disableUnderline: true,
                                   classes: {
                                     input: classes.itemTextFieldInputBox,
                                   },
                                 }}
                              />
                            </Grid>
                          </Grid>
                        </Grid>
                        <Divider className={classes.divider} light/>
                        {/* <ItemList
                          classes={classes}
                          items={this.state.items}
                          onChangeItemField = {this.handleChange}
                          onChangeItemQuantityField = {this.handleItemQuantityFieldChange}
                        >
                        </ItemList> */}
                        <Button color="primary" aria-label="add" className={classes.addItemButton}>
                          <AddIcon />
                          Add item
                        </Button>
                      </Paper>
                    </Grid>
                  </Grid>
                </div>
              </div>
            }
          />
        </div>
      </MuiThemeProvider>
    )
  }
}

class ItemList extends React.Component {
  render() {

    const {classes} = this.props

    return (
      <Grid container>
        {this.props.items.map((item, i)  => (
          <div key={i}>
            <Grid key={i} item xs={(i == 0 || i == this.props.items.length-1) ? 3 : ''}>
              <Typography type="caption">Black black jacket</Typography>
            </Grid>
            <Divider className={classes.divider} light/>
          </div>
        ))}
      </Grid>
    );
  }
}

export default withStyles(styles, { withTheme: true })(CreateOrderPage);
