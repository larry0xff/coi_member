var memberApp = angular.module('memberApp', ['ngRoute', 'ngCookies']);

memberApp.controller('mainCtrl', ['$rootScope', '$scope', '$http','$cookieStore',  function ($rootScope, $scope, $http, $cookieStore) {
    $rootScope.realname = $cookieStore.get('realname');
    $rootScope.openLoginModal = function(){
        var $modal = $('#loginModal');
        var options = {
            height: 370
        };
        $modal.modal(options);
        $modal.modal('open');
    };
    $scope.logout = function(){
        $http.get($rootScope.contextPath + '/logout').then(function(result){
            delete $rootScope.realname;
            $cookieStore.remove('realname');
            $cookieStore.remove('memberId');
        });
    };
}]);

