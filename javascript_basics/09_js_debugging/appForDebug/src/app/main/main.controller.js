(function() {
  'use strict';

  angular
    .module('highScoreListApp')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($interval, $log, highScoreService, tableService) {
      var vm = this;
      vm.filteredUsers = [];
      vm.allUsers = [];

      vm.table = {
          allUsers: [],
          filteredUsers: [],
          currentPage : null,
          pagesArray : null
      };

      vm.chnageTablePage = function(pageNumber) {
          tableService.chnageTablePage(vm.table, pageNumber);
      };

      vm.getIndexOfUser = function(arrayList, itemToFind) {
          return tableService.getIndexOfUser(arrayList, itemToFind);
      };

      $interval(function() {
          highScoreService.getHighScores().then(function (data) {
              tableService.updateTableWithNewDataSets(vm.table);
          }, function (reason) {
              $log.error('Failed: ' + reason);
          });
      }, 2000);
  }
})();
