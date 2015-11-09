var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {

    res.jsonp([
        {
            username: 'user 1',
            beaconCount: 1,
            elapsedMinutes: 5
        },
        {
            username: 'user 2',
            beaconCount: 2,
            elapsedMinutes: 10
        },
        {
            username: 'user 3',
            beaconCount: 3,
            elapsedMinutes: 15
        }
    ]);

    res.end();
});

module.exports = router;
