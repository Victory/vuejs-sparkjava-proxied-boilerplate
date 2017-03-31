var express = require("express");
var app = express();
var httpProxy = require('http-proxy');
var apiProxy = httpProxy.createProxyServer();

var dyno = 'http://localhost:4567';
var vue = 'http://localhost:8080';

app.all('/api/*', function (req, res) {
    apiProxy.web(req, res, {target: dyno});
});

app.all('*', function (req, res) {
    apiProxy.web(req, res, {target: vue});
});

app.listen(3000);
apiProxy.on("error", function (err, req, res) {
    console.error(err);
    res.writeHead(500, {
        'Content-Type': 'text/plain'
    });
    var msg = "";
    if (err.port == "8080") {
        msg = "\n\nCheck that the Vue Server is running!"
    }
    res.end(err.stack + "\n" + msg);
});