import React, {Component} from 'react'
import {Route} from 'react-router';
import {loadLabels} from '~/lib/i18n/i18n'


import { reducer } from './homeRedux';
import Home from './components/Home'


function createRoutes(stateFnct) {
	return (
		<Route path="/" component={Home}/>
    );
}


/**
 * Module initializer
 */
export default function(context = {}) {
	const {locale = 'en'} = context;
	const labels = loadLabels('home', 'home', locale);
	return {
		labels,
		createRoutes,
		reducer
	}
}
