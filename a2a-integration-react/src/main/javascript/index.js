import "babel-polyfill"

import React, { Component } from 'react'
import { Router, browserHistory } from 'react-router'
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'
import ReactDOM from 'react-dom'
import { addLocaleData } from 'react-intl'
import { intlReducer, IntlProvider, Provider} from 'react-intl-redux'
import promiseMiddleware from 'redux-promise-middleware';
import { createStore, combineReducers, applyMiddleware } from 'redux'

import { fieldsReducer, fieldsReduxMiddleware } from '~/lib/form/form-fields';
import config from './config';
import modulesInitializer from './modules'

//initialize modules
//i18n
const locales = ["en"], locale = "en";

//configure application
config.set(config.CONTEXT_PATH, window.contextPath);
config.set(config.LOCALE, locale);
config.set(config.USER, window.user);

config.set(config.ENV, window.env);

const modules = modulesInitializer();

for (let l of locales) {
	let loc = require('react-intl/locale-data/' + l);
	addLocaleData(loc);
}
const intl = {
    defaultLocale: locale,
    locale,
    messages: modules.labels
};

//redux
//reducers && async API calls
const reducers = {
	...modules.reducers,
	intl: intlReducer, 
	routing: routerReducer,
	fieldsReducer
};

const state = {
	...modules.state,
	intl
}

const store = createStore(combineReducers(reducers), state, applyMiddleware(promiseMiddleware(), fieldsReduxMiddleware));
let history = syncHistoryWithStore(browserHistory, store)

//routes
const routes = modules.createRoutes(store, store.getState);

//default styles
require("./styles/index.scss");

//dev helpers?
require("./styles/dev.scss");

//render react application
ReactDOM.render(
		<Provider store={store}>
			<IntlProvider>
				<Router history={history}>
					{routes}
				</Router>
			</IntlProvider>
		</Provider>, 
document.getElementById('root'));
