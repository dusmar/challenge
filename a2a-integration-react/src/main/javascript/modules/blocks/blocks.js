import React, {Component} from 'react'
import {connect} from 'react-redux'
import {Route} from 'react-router';
import {loadLabels} from '~/lib/i18n/i18n'

import BlocksList from './components/BlocksList'


function createRoutes(rootPath) {
	return (
		<Route path={rootPath} component={BlocksList}/>
    );
};

const reducer = null;

/**
 * Module initializer
 */
export default function(context = {}) {
	const {locale = 'en'} = context;
	const labels = loadLabels('blocks', 'blocks', locale);
	return {
		labels,
		reducer,
		createRoutes
	}
}