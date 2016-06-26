/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	 config.filebrowserBrowseUrl = 'resources/ckfinder/ckfinder.html',
	 config.filebrowserImageBrowseUrl = 'resources/ckfinder/ckfinder.html?type=Images',
	 config.filebrowserFlashBrowseUrl = 'resources/ckfinder/ckfinder.html?type=Flash',
	 config.filebrowserUploadUrl = 'resources/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
	 config.filebrowserImageUploadUrl = 'resources/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
	 config.filebrowserFlashUploadUrl = 'resources/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
};
