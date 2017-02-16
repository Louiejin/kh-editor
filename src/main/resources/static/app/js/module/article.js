define([
    'angular',
    'angularRoute',
    'common/resource',
    'common/service'
], function (angular) {

    angular.module('ArticleModule', ['ngRoute', 'ResourceModule', 'ServiceModule'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/article/list', {
                    templateUrl: "view/article/article_list.html",
                    controller: "ArticleListController",
                    permission: "ROLE_ADMIN,ROLE_USER",
                    resolve: {
                        page: ['ArticleResource', function (ArticleResource) {
                            return ArticleResource.query({page: 0, size: 10, query: null, sort: 'updated,desc'}).$promise;
                        }]
                    }
                })
                .when('/article/editor/list', {
                    templateUrl: "view/article/article_editor_list.html",
                    controller: "ArticleListController",
                    permission: "ROLE_USER",
                    resolve: {
                        page: ['ArticleResource', function (ArticleResource) {
                            return ArticleResource.query({
                                page: 0, size: 10,
                                sort: 'updated,desc'
                            }).$promise;
                        }]
                    }
                })
                .when('/article/available/list', {
                    templateUrl: "view/article/article_unclaimed_list.html",
                    controller: "AvailableArticlesController",
                    permission: "ROLE_USER",
                    resolve: {
                        page: ['ArticleResource', function (ArticleResource) {
                            return ArticleResource.query({
                                page: 0,
                                size: 10,
                                claimed: 'unclaimed',
                                sort: 'updated,desc'
                            }).$promise;
                        }]
                    }
                })
                .when('/article/:id', {
                    templateUrl: "view/article/article.html",
                    controller: "ArticleDetailsController",
                    permission: "ROLE_ADMIN,ROLE_USER",
                    resolve: {
                        article: ['$route', 'ArticleResource', function ($route, ArticleResource) {
                            if ($route.current.params.id == 'create')
                                return {};
                            else
                                return ArticleResource.get({id: $route.current.params.id}).$promise;
                        }]
                    }
                })
                .when('/article/translate/:id', {
                    templateUrl: "view/article/article_translate.html",
                    controller: "ArticleTranslateController",
                    permission: "ROLE_ADMIN,ROLE_USER",
                    resolve: {
                        article: ['$route', 'ArticleResource', function ($route, ArticleResource) {
                            return ArticleResource.get({id: $route.current.params.id}).$promise;
                        }]
                    }
                })
        }])
        .controller('ArticleListController', ['$scope', '$timeout', 'page', 'ArticleResource', 'Pageable',
            function ($scope, $timeout, page, ArticleResource, Pageable) {

                Pageable.init($scope, 'articles', page, ArticleResource, {page: 0, size: 10, sort: 'updated,desc'});

            }])
        .controller('AvailableArticlesController', ['$scope', '$timeout', 'page', 'ArticleResource', 'Pageable', 'Growl',
            function ($scope, $timeout, page, ArticleResource, Pageable, Growl) {
                Pageable.init($scope, 'articles', page, ArticleResource, {
                    page: 0,
                    size: 10,
                    claimed: 'unclaimed',
                    sort: 'updated,desc'
                });

                $scope.claim = function (article, index) {
                    ArticleResource.claim({id: article.id}, function () {
                        $scope.articles.splice(index, 1);
                    }, function () {
                        Growl.notify("Unable to claim. Someone might have claimed it first.");
                    })
                }
            }])
        .controller('ArticleDetailsController', ['$scope', '$location', 'article', 'ArticleResource', 'Growl', 'Swal', '$timeout', '$rootScope',
            function ($scope, $location, article, ArticleResource, Growl, Swal, $timeout, $rootScope) {
                $scope.article = article;

                $timeout(function () {
                    $rootScope.$broadcast('elastic:adjust');
                }, 200);

                $scope.save = function () {
                    if ($scope.articleForm.$valid) {
                        return ArticleResource.save($scope.article, function (saved) {
                            Growl.notify("Successfully Saved");
                            $location.path('/article/list');
                        }, function (error) {
                            Growl.notify("Could not save.");
                        }).$promise;
                    }
                };

                $scope.remove = function () {
                    Swal.confirm("Are you sure you want to delete?", function () {
                        return ArticleResource.remove({id: $scope.article.id}, function () {
                            Growl.notify("Successfully Deleted");
                            $location.path('/article/list');
                        }, function (error) {
                            Growl.notify("Could not delete.");
                        }).$promise;
                    })
                };
            }])
        .controller('ArticleTranslateController', ['$scope', '$location', 'article', 'ArticleResource', 'KanjiEngineResource', 'Growl', '$timeout', '$rootScope',
            function ($scope, $location, article, ArticleResource, KanjiEngineResource, Growl, $timeout, $rootScope) {
                $scope.article = article;

                $timeout(function () {
                    $rootScope.$broadcast('elastic:adjust');
                }, 200);

                $scope.translate = function () {
                    return KanjiEngineResource.translate({data: $scope.article.body}, function (result) {
                        $scope.article.translatedBody = result.data;
                    }).$promise;
                };

                $scope.save = function () {
                    if ($scope.articleForm.$valid) {
                        return ArticleResource.save($scope.article, function (saved) {
                            Growl.notify("Successfully Saved");
                            $location.path('/article/editor/list');
                        }, function (error) {
                            Growl.notify("Could not save.");
                        }).$promise;
                    }
                };

            }])
    ;
});

