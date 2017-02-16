define([
    'angular',
    'angularResource'
], function (angular) {

    angular.module('ResourceModule', ['ngResource'])
        .factory('UserResource', ['$resource', function ($resource) {
            return $resource('/user/:id');
        }])
        .factory('KanjiEngineResource', ['$resource', function ($resource) {
            return $resource('http://localhost:8081/translate', {}, {
                'translate': {method: 'POST', url: 'http://localhost:8081/translate'}
            });
        }])
        .factory('ArticleResource', ['$resource', function ($resource) {
            return $resource('/article/:id', {}, {
                'query': {method: 'GET', url: '/article', isArray: false},
                'claim': {method: 'GET', url: '/article/claim/:id'}
            });
        }])
    ;
});

