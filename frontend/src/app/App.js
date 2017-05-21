import React, { PropTypes } from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';
import { Provider } from 'react-redux';

import Intl from '../components/Intl';
import SimpleTop from '../containers/SimpleTop';

import Inicio from '../modules/Inicio';

const App = ({store}) => (
    <Provider store={store}>

        <Router history={browserHistory}>

            <Route component={SimpleTop} path="/" name={<Intl str='inicio'></Intl>}>
                <IndexRoute component={Inicio} />
            </Route>

        </Router>

    </Provider>
);


App.propTypes = {
    store: PropTypes.object.isRequired,
};

export default App;
