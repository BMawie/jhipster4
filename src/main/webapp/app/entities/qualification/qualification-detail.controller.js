(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .controller('QualificationDetailController', QualificationDetailController);

    QualificationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Qualification'];

    function QualificationDetailController($scope, $rootScope, $stateParams, previousState, entity, Qualification) {
        var vm = this;

        vm.qualification = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterApp:qualificationUpdate', function(event, result) {
            vm.qualification = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
