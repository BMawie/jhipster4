(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('qualification', {
            parent: 'entity',
            url: '/qualification?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterApp.qualification.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/qualification/qualifications.html',
                    controller: 'QualificationController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('qualification');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('qualification-detail', {
            parent: 'entity',
            url: '/qualification/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterApp.qualification.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/qualification/qualification-detail.html',
                    controller: 'QualificationDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('qualification');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Qualification', function($stateParams, Qualification) {
                    return Qualification.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'qualification',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('qualification-detail.edit', {
            parent: 'qualification-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/qualification/qualification-dialog.html',
                    controller: 'QualificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Qualification', function(Qualification) {
                            return Qualification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('qualification.new', {
            parent: 'qualification',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/qualification/qualification-dialog.html',
                    controller: 'QualificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                type: null,
                                year: null,
                                results: null,
                                name: null,
                                country: null,
                                city: null,
                                schoolName: null,
                                notes: null,
                                speciality: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('qualification', null, { reload: 'qualification' });
                }, function() {
                    $state.go('qualification');
                });
            }]
        })
        .state('qualification.edit', {
            parent: 'qualification',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/qualification/qualification-dialog.html',
                    controller: 'QualificationDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Qualification', function(Qualification) {
                            return Qualification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('qualification', null, { reload: 'qualification' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('qualification.delete', {
            parent: 'qualification',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/qualification/qualification-delete-dialog.html',
                    controller: 'QualificationDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Qualification', function(Qualification) {
                            return Qualification.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('qualification', null, { reload: 'qualification' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
