'use strict';

import gulp from 'gulp';
import del from 'del';
import shell from 'gulp-shell';

gulp.task('upgrade', shell.task([
  "yarn"
]));

gulp.task('dev', shell.task([
  "yarn run dev"
]));

gulp.task('builddist', ['clean'], shell.task([
  "yarn run build"
]));

gulp.task('unit', shell.task([
  "yarn run unit"
]));

gulp.task('generate-service-worker', function(callback) {
  var path = require('path');
  var swPrecache = require('sw-precache');
  var rootDir = 'dist';

  swPrecache.write(`${rootDir}/service-worker.js`, {
    staticFileGlobs: [rootDir + '/**/*.{js,html,css,png,jpg,gif,svg,eot,ttf,woff}'],
    stripPrefix: rootDir
  }, callback);
});

gulp.task('clean', () => del(['.tmp', 'dist/*', '!dist/.git'], {dot: true}));

gulp.task('build', ['builddist']);
