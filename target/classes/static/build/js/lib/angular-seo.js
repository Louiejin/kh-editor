!function(n){var o=function(o){return o.module("seo",[]).run(["$rootScope",function(o){o.htmlReady=function(){o.$evalAsync(function(){setTimeout(function(){"function"==typeof n.callPhantom&&n.callPhantom()},0)})}}])};"function"==typeof define&&define.amd?define(["angular"],o):o(angular)}(window,document);