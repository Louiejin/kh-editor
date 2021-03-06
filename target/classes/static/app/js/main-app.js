(function () {

    require.config({
        baseUrl: '/app/js',
        paths: {
            jquery: 'lib/jquery-2.1.3',
            angular: 'lib/angular',
            angularResource: 'lib/angular-resource',
            angularRoute: 'lib/angular-route',
            angularUI: 'lib/ui-bootstrap-tpls',
            angularUISelect: 'lib/select',
            angularMessages: 'lib/angular-messages',
            angularSanitize: 'lib/textAngular-sanitize',
            angularAnimate: 'lib/angular-animate',
            angularAutoDisable: 'lib/angular-autodisable',
            angularUpload: 'lib/ng-file-upload',
            elastic: 'lib/elastic',
            checklistModel: 'lib/checklist-model',
            dateDropDowns: 'lib/date-drop-downs',
            growl: 'lib/bootstrap-growl',
            swal: 'lib/sweet-alert',
            niceScroll: 'lib/jquery.nicescroll',
            chosenJquery: 'lib/chosen.jquery',
            chosen: 'lib/chosen'
        },
        shim: {
            angular: {deps: ['jquery'], exports: 'angular'},
            angularResource: {deps: ['angular']},
            angularAnimate: {deps: ['angular']},
            angularRoute: {deps: ['angular']},
            angularMessages: {deps: ['angular']},
            angularUI: {deps: ['angular']},
            angularUISelect: {deps: ['angular']},
            angularSanitize: {deps: ['angular']},
            angularAutoDisable: {deps: ['angular']},
            dateDropDowns: {deps: ['angular']},
            elastic: {deps: ['angular']},
            angularUpload: {deps: ['angular']},
            checklistModel: {deps: ['angular']},
            growl: {deps: ['jquery']},
            swal: {deps: ['jquery']},
            niceScroll: {deps: ['jquery'], exports: 'niceScroll'},
            chosenJquery: {deps: ['angular']},
            chosen: {deps: ['chosenJquery']}
        }
    });

    require(['app'], function () {
        console.log('Done Loading');
    });

})();