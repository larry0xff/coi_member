var memberApp = angular.module('memberApp', ['ngRoute']);

memberApp.controller('mainCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.openLoginModal = function(){
        var $modal = $('#loginModal');
        var options = {
            height: 300
        };
        $modal.modal(options);
        $modal.modal('open');
    }
}]);

