var memberApp = angular.module('memberApp');
memberApp.config(function ($routeProvider, $locationProvider) {
    //防止路由地址出现感叹号和乱码
    $locationProvider.hashPrefix('');
    $routeProvider
    .when(
        '/home',
        {
            templateUrl: 'view/home.html',
            controller: 'homeCtrl'
        }
    ).otherwise(
        {
            redirectTo: '/home'
        }
    )
});