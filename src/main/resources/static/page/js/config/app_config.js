var memberApp = angular.module('memberApp');
memberApp.run(['$rootScope', function($rootScope){
    $rootScope.contextPath = 'http://120.79.81.123:8801/member/';
}]);