import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import WelcomePage from './WelcomePage';
import registerServiceWorker from './registerServiceWorker';

import CreateOrder from './CreateOrder';

ReactDOM.render(<WelcomePage />, document.getElementById('root'));


registerServiceWorker();
