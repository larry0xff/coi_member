var memberApp = angular.module('memberApp', ['ngRoute', 'ngCookies']);

memberApp.controller('mainCtrl', ['$rootScope', '$scope', '$http','$cookieStore',  function ($rootScope, $scope, $http, $cookieStore) {
    $scope.realname = $cookieStore.get('realname');
    $scope.openLoginModal = function(){
        var $modal = $('#loginModal');
        var options = {
            height: 370
        };
        $modal.modal(options);
        $modal.modal('open');
    };
    $scope.logout = function(){
        $http.get($rootScope.contextPath + '/logout').then(function(result){
            $rootScope.isLogin = false;
        })
    };
}]);

