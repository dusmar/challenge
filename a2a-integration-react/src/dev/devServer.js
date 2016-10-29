/* **************************** */
/*                              */
/* **************************** */
const path = require('path');  

const express = require('express');
const app = express();  

//static routes - frontend
const webpack = require('webpack');  
const webpackMiddleware = require('webpack-dev-middleware');  
const webpackCfg = require('../../webpack.config.js');
const compiler = webpack(webpackCfg);

app.use(webpackMiddleware(compiler, {headers: { "X-Custom-Header": "yes" }, publicPath: '/dist'}));  
app.use(express.static(path.join(__dirname, '../main/webapp')));

//mocked backend?
app.get('/api/messages', function(req, res) {
	const page = (req.headers["x-paging-page"] * 1) || 0;
	const data = [
		{"id":6697644,"parentId":6697641,"status":"failed","attemptNo":3,"lastAttempt":"2016-09-26T07:35:10.000Z","created":"2016-09-26T07:32:00.000Z","createdBy":"CVNotifyShippingEventForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-09-26T07:35:10.000Z","updatedBy":"CVNotifyShippingEventOrderStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8377,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":32130},{"id":6697927,"parentId":6697925,"status":"not_processed","attemptNo":0,"created":"2016-09-29T05:21:04.000Z","createdBy":"CVNotifyShippingEventOrderStatusForkController","creatorType":"fork","creatorInstance":"one","queueId":8377,"locked":false,"type":"UNKNOWN","isSeq":false},{"id":6699021,"parentId":6699019,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-10T23:56:33.000Z","created":"2016-10-10T08:27:33.000Z","createdBy":"CVNotifyShippingOrderPackedStatusForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-10-10T23:56:33.000Z","updatedBy":"CVNotifyShippingOrderPackedStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8383,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":15400039},{"id":6698279,"parentId":6698277,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-05T21:54:32.000Z","created":"2016-10-05T07:18:28.000Z","createdBy":"CVNotifyShipmentOrderStatusForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-10-05T21:54:32.000Z","updatedBy":"CVNotifyShipmentStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8374,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":15400086},{"id":6698377,"parentId":6698377,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-06T00:05:02.000Z","header":"NO HEADER","created":"2016-10-06T00:03:28.000Z","createdBy":"notifyOrderLoader1","creatorType":"loader","creatorInstance":"one","updated":"2016-10-06T00:05:01.000Z","updatedBy":"CVNotifyShipmentEnrichController","updatorType":"translator","updatorInstance":"one","queueId":1077,"locked":false,"type":"UNKNOWN","isSeq":false,"log":"Message id 6698377 failed.  java.lang.NumberFormatException ( com.a2a.activity.enrich.cv.CVNotifyShipmentEnrich, line 35; com.a2a.activity.enrich.cv.CVNotifyShipmentEnrich, line 278; java.math.BigDecimal, line 806; java.math.BigDecimal, line 383; java.math.BigDecimal, line 494)","duration":11},{"id":6699073,"parentId":6699073,"status":"not_processed","attemptNo":0,"header":"NO HEADER","created":"2016-10-10T09:59:14.000Z","createdBy":"notifyOrderLoader1","creatorType":"loader","creatorInstance":"one","queueId":-666,"locked":false,"type":"UNKNOWN","isSeq":false},{"id":6698473,"parentId":6698471,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-06T17:29:45.000Z","created":"2016-10-06T05:07:04.000Z","createdBy":"CVNotifyShippingEventOrderStatusForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-10-06T17:29:45.000Z","updatedBy":"CVNotifyShippingEventOrderStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8377,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":15400799},
		{"id":"669836x","parentId":6698358,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-06T11:42:31.000Z","created":"2016-10-05T23:51:18.000Z","createdBy":"CVNotifyShippingEventForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-10-06T11:42:31.000Z","updatedBy":"CVNotifyShippingEventOrderStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8377,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":15400113},
		{"id":"669836y","parentId":6698358,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-06T11:42:31.000Z","created":"2016-10-05T23:51:18.000Z","createdBy":"CVNotifyShippingEventForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-10-06T11:42:31.000Z","updatedBy":"CVNotifyShippingEventOrderStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8377,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":15400113},
		{"id":"669836z","parentId":6698358,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-06T11:42:31.000Z","created":"2016-10-05T23:51:18.000Z","createdBy":"CVNotifyShippingEventForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-10-06T11:42:31.000Z","updatedBy":"CVNotifyShippingEventOrderStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8377,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":15400113}
		];

	res.setHeader("X-Paging", true);
	res.setHeader("X-Paging-Page", page);
	res.setHeader("X-Paging-PagesCount", 2);
	res.status(200).send(data);
});

app.get('/api/messages/:id', function(req, res) {
	const data = 
		{"id":"669836z","parentId":6698358,"status":"failed","attemptNo":3,"lastAttempt":"2016-10-06T11:42:31.000Z","created":"2016-10-05T23:51:18.000Z","createdBy":"CVNotifyShippingEventForkController","creatorType":"fork","creatorInstance":"one","updated":"2016-10-06T11:42:31.000Z","updatedBy":"CVNotifyShippingEventOrderStatusAlertSenderController","updatorType":"sender","updatorInstance":"one","queueId":8377,"locked":false,"type":"UNKNOWN","isSeq":false,"duration":15400113}
		;
	res.status(200).send(data);
});

app.get('/api/home/history', function(req, res) {
	res.status(200).send([{"processed":0,"failed":10,"rejected":0,"other":0,"statDate":"2016-10-07"},{"processed":0,"failed":7,"rejected":0,"other":25,"statDate":"2016-10-10"},{"processed":0,"failed":53,"rejected":0,"other":0,"statDate":"2016-10-11"},{"processed":0,"failed":136,"rejected":0,"other":6,"statDate":"2016-10-12"},{"processed":0,"failed":59,"rejected":0,"other":0,"statDate":"2016-10-13"},{"processed":0,"failed":993,"rejected":0,"other":3,"statDate":"2016-10-14"}]);
});

app.get('/api/home/summary', function(req, res) {
	res.status(200).send({"processed":0,"failed":1258,"rejected":0,"other":34});
})

app.get('/api/*', function(req, res) {
	setTimeout(function() {
		res.status(500).send();
	}, 1250);
});

//index file
const fs = require("fs");
const indexMiddleware = function(indexFilePath) {
	let cached = fs.readFileSync(path.join(__dirname, '../main/java/com/a2a/web/index.html'), 'utf8');
	while (cached.indexOf('<%ROOT%>') > -1) {
		cached = cached.replace('<%ROOT%>', '');
	}
	cached = cached.replace('<%VERSION%>', 'dev');
	return function(req, res, next) {
    	res.status(200).send(cached);
	};
};
app.get('*', indexMiddleware(), function (req, res) {
	res.status(200).send();
});


//start server
const port = process.env.PORT || 3000;
app.listen(port, function() {
	console.log("Server started on port " + port);	
});  

