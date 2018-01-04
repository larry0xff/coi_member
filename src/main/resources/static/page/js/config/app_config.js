var memberApp = angular.module('memberApp');
memberApp.run(['$rootScope', function($rootScope){
    $rootScope.contextPath = 'http://localhost:8801/member';
}]);