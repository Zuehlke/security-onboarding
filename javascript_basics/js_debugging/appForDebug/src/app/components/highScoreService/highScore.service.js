(function() {
    'use strict';

    angular
        .module('highScoreListApp')
        .service('highScoreService', highScoreService);

    /** @ngInject */
    function highScoreService($http, $q) {

        this.getHighScores = getHighScores;

        function getHighScores() {
            var url = "http://localhost:3000/users?callback=JSON_CALLBACK";

            var deferred = $q.defer();
            $http.get(url)
                .success(function(data) {
                    deferred.resolve(data);
                }).error(function(response) {
                    deferred.reject(response);
                });

            return deferred.promise;
        }
    }

})();
