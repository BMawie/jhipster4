(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .controller('QualificationDialogController', QualificationDialogController);

    QualificationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Qualification'];

    function QualificationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Qualification) {
        var vm = this;

        vm.qualification = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.qualification.id !== null) {
                Qualification.update(vm.qualification, onSaveSuccess, onSaveError);
            } else {
                Qualification.save(vm.qualification, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterApp:qualificationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
