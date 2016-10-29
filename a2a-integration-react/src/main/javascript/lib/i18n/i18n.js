const defaults = {
	root: 'i18n'
};


export function loadLabels(path, name, locale, country) {
	const root = (path ? path + '/' : '') + name;
	let labels = {};
	try {
		labels = require('~/i18n/' + root + '_' + locale + '_' + country + '.js');
		return labels;
	} catch (ex) {}
	try {
		labels = require('~/i18n/' + root + '_' + locale + '.js');
		return labels;
	} catch (ex) {}
	try {
		labels = require('~/i18n/' + root + '.js');
		return labels;
	} catch (ex) {}
	
	return labels;
};

