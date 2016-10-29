const webpack = require('webpack');

const path = require('path');
const root = path.join(__dirname, '/src/main/javascript');

module.exports = {
	root: root,
	entry : path.join(__dirname, '/src/main/javascript/index.js'),
	output : {
		path : path.join(__dirname, '/src/main/webapp/dist'),
		publicPath : '/dist',
		filename: 's.js'
	},
	module : {
		loaders : [ 
			{test: /\.js$/, include: root, loader : 'babel'},
			{test: /\.scss$/, include: [root, path.resolve(__dirname, "not_exist_path")], loaders: ["style", "css", "sass"]}
			/*,
			{test: /\.(png|jpg)$/, loader: 'url-loader?limit=16384'}*/
		]
	}/*,
	plugins : [ 
	    new webpack.DefinePlugin({'process.env.NODE_ENV' : JSON.stringify('production')}), 
	    new webpack.optimize.UglifyJsPlugin({compress : {warnings : false} }) 
	]
	*/
}
