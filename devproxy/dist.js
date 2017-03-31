var express = require("express");
var httpProxy = require('http-proxy');
var apiProxy = httpProxy.createProxyServer();
var app = express();

var dyno = 'http://localhost:4567';

app.all('/browse*', function (req, res) {
    apiProxy.web(req, res, {target: dyno});
});

app.use(express.static('../frontvue/dist'))

app.listen(3001);
