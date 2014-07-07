'use strict';
var coincollectorApp = angular.module('coincollectorApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ngAnimate'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/info', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/empfehlung', {
        templateUrl: 'views/recommendation.html',
        controller: 'RecommendationCtrl'
      })
      .when('/account/:email', {
        templateUrl: 'views/account/account.html',
        controller: 'AccountCtrl'
      })
      .otherwise({
        redirectTo: '/info'
      });
  });




