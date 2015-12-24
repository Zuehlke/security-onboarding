(function() {
  'use strict';

  angular
    .module('highScoreListApp')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log) {

    $log.debug('runBlock end');
  }

})();
