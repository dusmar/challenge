//private reference
const data = {};
const config = {};

config.set = function(key, value) {
	if (data[key]) {
		throw new Error("Value already set");
	}
	data[key] = value;
}

config.get = function(key) {
	return data[key];
}

//helper functions
config.path = function(path) {
	return path;
}


/* *** Constants *** */
config.CONTEXT_PATH = 'CONTEXT_PATH';
config.LOCALE = 'LOCALE';
config.USER = 'USER';
config.ENV = 'ENV';

export default config;