import React, { Component, PropTypes } from "react";
import { Route } from "react-router";
import { connect } from 'react-redux';
import Keycloak from "keycloak-js";
import axios from "axios";

import { login } from './App.actions';

class PrivateRoute extends Component {

    render() {

        if (!this.props.isLoggedIn) {
            this.props.redirectToLogin()
            return null
        }

        return <Route {...this.props} />

    }
}

const mapStateToProps = (state) => {
    return {
        data: state.appReducer
    }
};

PrivateRoute = connect( 
    mapStateToProps,
    {}
)(PrivateRoute);

export default PrivateRoute;

/*
// KEYCLOAK CONFIG
let kc = Keycloak(appData.config.keycloakConfigFile);
kc.init({onLoad: 'check-sso'}).success(authenticated => {
    if (authenticated) {
        store.dispatch(login(kc, DEFAULT_LANGUAGE));
        ReactDOM.render(<App store={store}/>, document.getElementById('root'));
    } else {
        kc.login();
    }
});

// AXIOS CONFIG
axios.defaults.baseURL = appData.config.axiosBaseURL;
axios.interceptors.request.use(config => {
    if(kc.isTokenExpired()) {
        kc.updateToken(1000*60*25).error(() => kc.logout());
    }

    config.headers = {...config.headers, ...{
        Accept: 'application/json',
        Authorization: 'Bearer ' + kc.token
    }};
    return config;
});
*/