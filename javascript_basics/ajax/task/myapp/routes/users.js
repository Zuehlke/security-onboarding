var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
    function jsonCallback() {
        return {
            users:
            [
                {
                    userName: 'user 1'
                },
                {
                    userName: 'user 2'
                },
                {
                    userName: 'user 3'
                }
            ]
        }
    }

    //res.send(jsonCallback());
    res.jsonp([
        {
            userName: 'user 1'
        },
        {
            userName: 'user 2'
        },
        {
            userName: 'user 3'
        }
    ]);

    res.end();
});

module.exports = router;
