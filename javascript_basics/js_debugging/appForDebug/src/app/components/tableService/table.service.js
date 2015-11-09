/**
 * Created by mabo on 10/21/2015.
 */
(function() {
    'use strict';

    angular
        .module('highScoreListApp')
        .service('tableService', tableService);

    /** @ngInject */
    function tableService($log) {
        var stepSize = 20;

        this.getNumberOfPages = getNumberOfPages;
        this.filterDataByPage = filterDataByPage;
        this.getIndexOfUser = getIndexOfUser;
        this.chnageTablePage = changeTablePage;
        this.updateTableWithNewDataSet = updateTableWithNewDataSet;

        function getNumberOfPages(dataSet) {
            var pagesNumber = parseInt(dataSet.length / stepSize);
            if((dataSet.length % stepSize) > 0) {
                pagesNumber++;
            }
            return pagesNumber;
        }

        function filterDataByPage(pageNumber, dataToFilter) {
                var startIndex = (pageNumber - 1) * stepSize;
                var endIndex = (pageNumber * stepSize);

                $log.log(startIndex, endIndex);
                return dataToFilter.slice(startIndex, endIndex);
        }

        function getIndexOfUser(arrayList, itemToFind) {
            for(var i = 0; i < arrayList.length; i++) {
                var item = arrayList[i];

                /*
                 * different instances of an object are never equal
                 * so we compare all their properties like this
                 */
                if(angular.toJson(item) === angular.toJson(itemToFind)){
                    return i;
                }
            }
            return -1;
        }

        function changeTablePage(table, pageNumber) {
            if(pageNumber !== null && pageNumber > 0 && pageNumber <= table.pagesArray.length) {
                table.currentPage = pageNumber;
                table.filteredUsers = filterDataByPage(pageNumber, table.allUsers);
            }
        }

        function transformElapsedMinutesToDate(users) {
            angular.forEach(users, function(user) {
                var minutes = parseInt(user.elapsedMinutes) % 60;
                var hours = Math.floor(user.elapsedMinutes / 60);

                if(minutes.length === 1) {
                    minutes = "0" + minutes;
                }

                var hoursAndMinutes = hours + ":" + minutes;
                user.elapsedMinutes = hoursAndMinutes;
            });
        }

        function updateTableWithNewDataSet(table, newDataSet) {
            $log.log(newDataSet);

            table.allUsers = newDataSet;
            transformElapsedMinutesToDate(table.allUsers);

            var numberOfPages = getNumberOfPages(table.allUsers);
            table.pagesArray = new Array(numberOfPages);

            if (table.allUsers.length > 0) {
                if(table.currentPage === null) {
                    changeTablePage(table, 1);
                } else {
                   /* If the last page was longer than the new pagesArray, set it to the pagesArray length*/
                    var newPage = table.currentPage > table.pagesArray.length ? table.pagesArray.length : table.currentPage;
                    changeTablePage(table, newPage);
                }

               /*If we got an empty array then reset the values*/
            } else {
                table.currentPage = null;
                table.filteredUsers = [];
            }
        }
    }

})();
